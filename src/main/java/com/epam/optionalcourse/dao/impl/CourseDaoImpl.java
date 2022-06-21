package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.bean.ReadCourseRun;
import com.epam.optionalcourse.dao.CourseDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {
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

    public CourseDaoImpl() {
    }

    @Override
    public List<ReadCourseRun> getAllCourses() throws DaoException {

        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<ReadCourseRun> courses = new ArrayList<>();
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(buildReadCourse(resultSet));
            }
            return courses;
        } catch (SQLException | ConnectionPoolException e) {
            // TODO: 6/15/2022 logger
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
                readCourse = buildReadCourse(resultSet);
            }

            return Optional.ofNullable(readCourse);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createCourse(Course course) {

    }

    @Override
    public void updateCourse(Course course) {

    }

    private ReadCourseRun buildReadCourse(ResultSet resultSet) throws SQLException {
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
