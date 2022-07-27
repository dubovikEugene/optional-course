package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.user.AuthorizedUser;
import com.epam.optionalcourse.bean.user.ReadUser;
import com.epam.optionalcourse.bean.user.User;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final CourseRunDaoImpl courseDao = new CourseRunDaoImpl();
    private static final TeacherFeedbackDaoImpl teacherFeedbackDao = new TeacherFeedbackDaoImpl();
    private static final String REGISTRATION_SQL = """
            INSERT INTO users (first_name, last_name, birthday, email, 
            password,role, gender, created_at) 
            VALUES (?, ?, ? , ?, ?,(SELECT id FROM roles WHERE LOWER(role_name) LIKE LOWER(?)), ?, now())
            """;
    private static final String SIGN_IN_SQL = """
            SELECT u.id, u.first_name, u.email, r.role_name
            FROM users AS u
                     JOIN roles r ON r.id = u.role
            WHERE LOWER(email) LIKE LOWER(?)
              AND password LIKE ?
            """;
    private static final String REGISTER_USER_FOR_A_COURSE_SQL = """
            INSERT INTO teachers_feedbacks (student_id, course_runs_id)
            VALUES (?, ?)
            """;
    private static final String DROP_COURSE_FROM_STUDENT_SQL = """
            DELETE FROM teachers_feedbacks
            WHERE student_id = ?
            AND course_runs_id = ?
            """;
    private static final String GET_USER_INFO_SQL = """
            SELECT u.id, u.first_name, u.email, r.role_name
            FROM users AS u
                     JOIN roles r ON r.id = u.role
            WHERE id = ?
            """;
    private static final String FIND_ALL_BY_COURSE_RUN_ID = """
            SELECT id,first_name, last_name, email, tf.is_get_feedback
            FROM users
                JOIN teachers_feedbacks tf on users.id = tf.student_id
            WHERE tf.course_runs_id = ?
            """;

    public UserDaoImpl() {
    }

    @Override
    public Optional<AuthorizedUser> signIn(String email, String password) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(SIGN_IN_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            var resultSet = preparedStatement.executeQuery();

            AuthorizedUser authorizedUser = null;
            if (resultSet.next()) {
                authorizedUser = buildAuthorizedUser(resultSet);
            }
            return Optional.ofNullable(authorizedUser);
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public AuthorizedUser registration(User user) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(REGISTRATION_SQL, RETURN_GENERATED_KEYS)) {
            setUserColumns(preparedStatement, user);

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));

            return buildAuthorizedUser(user);

        } catch (SQLException | ConnectionPoolException exception) {
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    @Override
    public AuthorizedUser registerForACourse(AuthorizedUser user, Integer courseRunId) throws DaoException {
        return getAuthorizedUserAfterUpdateListParticipatesCourses(user, courseRunId, REGISTER_USER_FOR_A_COURSE_SQL);

    }

    @Override
    public AuthorizedUser dropCourseFromStudent(AuthorizedUser authorizedUser, int courseId) throws DaoException {
        return getAuthorizedUserAfterUpdateListParticipatesCourses(authorizedUser, courseId, DROP_COURSE_FROM_STUDENT_SQL);

    }

    @Override
    public List<ReadUser> findAllStudentsByCourseRunId(int courseRunId) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_COURSE_RUN_ID)) {
            preparedStatement.setObject(1, courseRunId);
            var resultSet = preparedStatement.executeQuery();
            List<ReadUser> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildReadUser(resultSet));
            }
            return users;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }

    }

    private AuthorizedUser getAuthorizedUserAfterUpdateListParticipatesCourses(AuthorizedUser authorizedUser, int courseId, String dropCourseFromStudentSql) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(dropCourseFromStudentSql)) {

            preparedStatement.setObject(1, authorizedUser.getId());
            preparedStatement.setObject(2, courseId);

            preparedStatement.executeUpdate();

            var allCourseRunsByStudentId = courseDao.findAllCourseRunsByUserId(authorizedUser.getId());
            authorizedUser.setCourses(allCourseRunsByStudentId);

            return authorizedUser;

        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    private ReadUser buildReadUser(ResultSet resultSet) throws SQLException {
        boolean isGetFeedback = resultSet.getObject("is_get_feedback", Integer.class) != null;
        return new ReadUser(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),

                resultSet.getObject("last_name", String.class),
                resultSet.getObject("email", String.class),
                isGetFeedback
        );
    }


    private AuthorizedUser buildAuthorizedUser(User user) {
        return new AuthorizedUser(
                user.getId(),
                user.getFirstName(),
                user.getEmail(),
                user.getRole());
    }

    private AuthorizedUser buildAuthorizedUser(ResultSet resultSet) throws SQLException, DaoException {
        var authorizedUser = new AuthorizedUser();
        authorizedUser.setId(resultSet.getInt("id"));
        authorizedUser.setFirstName(resultSet.getString("first_name"));
        authorizedUser.setEmail(resultSet.getString("email"));
        authorizedUser.setRole(resultSet.getString("role_name"));

        var allCourseRunsByStudentId = courseDao.findAllCourseRunsByUserId(authorizedUser.getId());
        var allFeedbackByStudentId = teacherFeedbackDao.getAllFeedbackByStudentId(authorizedUser.getId());
        authorizedUser.setCourses(allCourseRunsByStudentId);
        authorizedUser.setFeedbacks(allFeedbackByStudentId);
        return authorizedUser;
    }

    private void setUserColumns(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setObject(1, entity.getFirstName());
        preparedStatement.setObject(2, entity.getLastName());
        preparedStatement.setDate(3, Date.valueOf(entity.getBirthday()));
        preparedStatement.setObject(4, entity.getEmail());
        preparedStatement.setObject(5, entity.getPassword());
        preparedStatement.setObject(6, entity.getRole());
        preparedStatement.setObject(7, entity.getGender().name());
    }
}
