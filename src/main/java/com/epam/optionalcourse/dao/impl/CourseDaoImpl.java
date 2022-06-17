package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.bean.ReadCourse;
import com.epam.optionalcourse.dao.CourseDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String FIND_ALL_SQL = """
            SELECT c.id, c.course_name, c.start_date, c.end_date, c.description, u.first_name, u.last_name
            FROM courses AS c JOIN users u on u.id = c.teacher_id
            WHERE is_ended IS NULL
              AND flag IS NULL
            """;

    public CourseDaoImpl() {
    }

    @Override
    public List<ReadCourse> getAllCourses() throws DaoException {

        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<ReadCourse> courses = new ArrayList<>();
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

    private ReadCourse buildReadCourse(ResultSet resultSet) throws SQLException {
        return new ReadCourse(
                resultSet.getInt("id"),
                resultSet.getString("course_name"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                resultSet.getString("description"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    }

    @Override
    public void createCourse(Course course) {

    }

    @Override
    public void updateCourse(Course course) {

    }
}
