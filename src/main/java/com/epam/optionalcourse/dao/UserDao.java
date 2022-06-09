package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.dao.exception.DaoException;

public interface UserDao {

    AuthorizedUser signIn (String login, String password) throws DaoException;
    AuthorizedUser registration (User user) throws DaoException;
}
