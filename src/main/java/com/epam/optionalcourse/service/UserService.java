package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.CreateUser;
import com.epam.optionalcourse.service.exception.ServiceException;

public interface UserService {

    AuthorizedUser register(CreateUser user) throws ServiceException;
    AuthorizedUser signIn(String email, String password) throws ServiceException;
}
