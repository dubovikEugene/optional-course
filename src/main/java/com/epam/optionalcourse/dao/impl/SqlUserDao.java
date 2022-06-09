package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class SqlUserDao implements UserDao {

    private static final SqlUserDao INSTANCE = new SqlUserDao();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SAVE_SQL = """
            INSERT INTO user (first_name, last_name, birthday, email, 
            password, role, gender, created_at, updated_at) 
            VALUES (?, ?, ? , ?, ?, ?, ?, ?, ?)
                        
            """;

    private SqlUserDao() {
    }

    public static SqlUserDao getInstance() {
        return INSTANCE;
    }




    @Override
    public AuthorizedUser signIn(String login, String password) throws DaoException {
    return null;
    }

    @Override
    public AuthorizedUser registration(User user) throws DaoException {
        try (var connection = pool.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            setUserColumns(preparedStatement, user);

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id", Integer.class));

            return createAuthorizedUser(user);

        } catch (SQLException | ConnectionPoolException exception) {
            throw new DaoException(exception);
            // TODO: 6/7/2022 logger
        }
    }

    private AuthorizedUser createAuthorizedUser(User user) {
        return new AuthorizedUser(
                user.getId(),
                user.getFirstName(),
                user.getEmail(),
                user.getRole());
    }

    private void setUserColumns(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setObject(1, entity.getFirstName());
        preparedStatement.setObject(2, entity.getLastName());
        preparedStatement.setDate(3, Date.valueOf(entity.getBirthday()));
        preparedStatement.setObject(4, entity.getEmail());
        preparedStatement.setObject(5, entity.getPassword());
        preparedStatement.setObject(6, entity.getRole());
        preparedStatement.setObject(7, entity.getGender());
        preparedStatement.setObject(8, Timestamp.valueOf(entity.getCreatedAt()));
        preparedStatement.setObject(9, Timestamp.valueOf(entity.getUpdatedAt()));
    }
}
