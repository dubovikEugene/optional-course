package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.CourseRun;
import com.epam.optionalcourse.bean.ReadCourseRun;
import com.epam.optionalcourse.dao.CourseRunDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRunDaoImpl implements CourseRunDao {

    private static final Logger logger = LogManager.getLogger(CourseRunDaoImpl.class);
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String FIND_ALL_SQL = """
            SELECT cr.id, c.course_name, cr.start_date, cr.end_date, c.description, cr.course_program, u.first_name, u.last_name
            FROM courses_runs AS cr
                JOIN courses c
            on cr.courses_id = c.id
                JOIN users u
            on u.id = cr.teacher_id
            WHERE is_ended IS NULL
              AND c.flag IS NULL
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
             AND cr.id = ?
            """;
    private static final String FIND_ALL_COURSE_RUN_BY_USER_ID_SQL = """
            SELECT cr.id, c.course_name, cr.start_date, cr.end_date, c.description, cr.course_program, u.first_name, u.last_name
            FROM courses_runs AS cr
            LEFT JOIN courses c
                    on cr.courses_id = c.id
            LEFT JOIN users u
                    on u.id = cr.teacher_id
            LEFT JOIN teachers_feedbacks tf on cr.id = tf.course_runs_id
            WHERE c.flag IS NULL
                    AND tf.student_id = ?
                    OR cr.teacher_id = ?
            """;
    private static final String UPDATE_COURSE_RUN_BY_ID = """
            UPDATE courses_runs
            SET start_date = ?, end_date = ?, course_program = ?
            WHERE id = ?
            """;
    private static final String CREATE_COURSE_RUN_SQL = """
            INSERT INTO courses_runs(start_date, end_date, course_program, courses_id, teacher_id)
            VALUES (?, ?, ?, ?, ?)
            """;

    public CourseRunDaoImpl() {
    }

    @Override
    public List<ReadCourseRun> findAllCourses() throws DaoException {

        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<ReadCourseRun> courses = new ArrayList<>();
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(buildReadCourseRun(resultSet));
            }
            return courses;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }


    }

    @Override
    public List<ReadCourseRun> findAllCourseRunsByUserId(Integer userId) throws DaoException {

        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_COURSE_RUN_BY_USER_ID_SQL)) {
            List<ReadCourseRun> courses = new ArrayList<>();
            preparedStatement.setObject(1, userId);
            preparedStatement.setObject(2, userId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(buildReadCourseRun(resultSet));
            }
            return courses;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<ReadCourseRun> findById(Integer courseId) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, courseId);

            var resultSet = preparedStatement.executeQuery();
            ReadCourseRun readCourse = null;
            if (resultSet.next()) {
                readCourse = buildReadCourseRun(resultSet);
            }

            return Optional.ofNullable(readCourse);

        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }


    @Override
    public Optional<ReadCourseRun> createCourseRun(CourseRun courseRun) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(CREATE_COURSE_RUN_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, Date.valueOf(courseRun.getStartDate()));
            preparedStatement.setObject(2, Date.valueOf(courseRun.getEndDate()));
            preparedStatement.setObject(3, courseRun.getCourseProgram());
            preparedStatement.setObject(4, courseRun.getCourseId());
            preparedStatement.setObject(5, courseRun.getTeacherId());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();

            return findById(generatedKeys.getInt(1));

        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }


    }

    @Override
    public Optional<ReadCourseRun> updateCourseRun(CourseRun courseRun) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(UPDATE_COURSE_RUN_BY_ID)) {
            preparedStatement.setObject(1, Date.valueOf(courseRun.getStartDate()));
            preparedStatement.setObject(2, Date.valueOf(courseRun.getEndDate()));
            preparedStatement.setObject(3, courseRun.getCourseProgram());
            preparedStatement.setObject(4, courseRun.getId());

            preparedStatement.executeUpdate();
            return findById(courseRun.getId());

        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }


    private ReadCourseRun buildReadCourseRun(ResultSet resultSet) throws SQLException {
        return new ReadCourseRun(
                resultSet.getInt("id"),
                resultSet.getString("course_name"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                resultSet.getString("description"),
                resultSet.getString("course_program"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    }
}
