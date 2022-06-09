package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.User;

public interface UserService {

    AuthorizedUser register(User user);
    AuthorizedUser signIn(String email, String password);
}
