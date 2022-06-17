package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<AuthorizedUser> signIn (String email, String password) throws DaoException;
    AuthorizedUser registration (User user) throws DaoException;
}
