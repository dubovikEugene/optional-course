package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.dao.CourseDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private static final Logger logger = LogManager.getLogger(CourseDaoImpl.class);
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String FIND_ALL_SQL = """
            SELECT id, course_name, description
            FROM courses
            """;

    @Override
    public List<Course> findAll() throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(buildCourse(resultSet));
            }

            return courses;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    private Course buildCourse(ResultSet resultSet) throws SQLException {
        return new Course(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("course_name", String.class),
                resultSet.getObject("description", String.class)
        );
    }
}
