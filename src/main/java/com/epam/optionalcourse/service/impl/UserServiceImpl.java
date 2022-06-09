package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.service.UserService;

public final class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    public AuthorizedUser register(User user){

        return null;
    }

    @Override
    public AuthorizedUser signIn(String email, String password) {

        return null;
    }
}
