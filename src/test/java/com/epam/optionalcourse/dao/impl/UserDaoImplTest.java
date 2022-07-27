package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.ReadUser;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    private static ConnectionPool pool;
    private static final UserDao userDao = DaoFactory.getInstance().getUserDao();
    private static final String VALID_EMAIL = "email@mail.com";
    private static final String INVALID_EMAIL = "emasdaasdsdasil@mail.com";
    private static final String VALID_PASSWORD = "123";
    private static final String INVALID_PASSWORD = "21dsahgiheb1hdbsna";
    private static final Integer VALID_COURSE_RUN_ID = 1;

    @BeforeAll
    static void beforeAll() throws ConnectionPoolException {
        pool = ConnectionPool.getInstance();
        pool.initConnectionPool();
    }
    @AfterAll
    static void afterAll() throws ConnectionPoolException {
        pool.closePool();
    }


    @Test
    void signIn_AuthorizedUserExist() throws DaoException {
        var authorizedUser = userDao.signIn(VALID_EMAIL, VALID_PASSWORD);
        assertTrue(authorizedUser.isPresent());

    }@Test
    void signIn_invalid_email() throws DaoException {
        var authorizedUser = userDao.signIn(INVALID_EMAIL, VALID_PASSWORD);
        assertTrue(authorizedUser.isEmpty());

    }@Test
    void signIn_invalid_password() throws DaoException {
        var authorizedUser = userDao.signIn(VALID_EMAIL, INVALID_PASSWORD);
        assertTrue(authorizedUser.isEmpty());
    }

    @Test
    void findAllStudentsByCourseRunId() throws DaoException {
        var studentsByCourseRunId = userDao.findAllStudentsByCourseRunId(VALID_COURSE_RUN_ID);
        assertFalse(studentsByCourseRunId.isEmpty());
    }

}
