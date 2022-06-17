package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDaoImpl implements UserDao {

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String REGISTRATION_SQL = """
            INSERT INTO users (first_name, last_name, birthday, email, 
            password,role, gender, created_at) 
            VALUES (?, ?, ? , ?, ?,(SELECT id FROM roles WHERE LOWER(role_name) LIKE LOWER('student')), ?, now())
            """;
    private static final String SIGN_IN_SQL = """
            SELECT u.id, u.first_name, u.email, r.role_name
            FROM users AS u
                     JOIN roles r ON r.id = u.role
            WHERE LOWER(email) LIKE LOWER(?)
              AND password LIKE ?
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
            // TODO: 6/13/2022 logger
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
            // TODO: 6/7/2022 logger
            throw new DaoException(exception);
        }
    }

    private AuthorizedUser buildAuthorizedUser(User user) {
        return new AuthorizedUser(
                user.getId(),
                user.getFirstName(),
                user.getEmail(),
                user.getRole());
    }

    private AuthorizedUser buildAuthorizedUser(ResultSet resultSet) throws SQLException {
        return new AuthorizedUser(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("email"),
                resultSet.getString("role_name"));
    }

    private void setUserColumns(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setObject(1, entity.getFirstName());
        preparedStatement.setObject(2, entity.getLastName());
        preparedStatement.setDate(3, Date.valueOf(entity.getBirthday()));
        preparedStatement.setObject(4, entity.getEmail());
        preparedStatement.setObject(5, entity.getPassword());
        preparedStatement.setObject(6, entity.getGender().name());
    }
}
