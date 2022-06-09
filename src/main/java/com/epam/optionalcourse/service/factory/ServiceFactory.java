package com.epam.optionalcourse.service.factory;

import com.epam.optionalcourse.service.UserService;
import com.epam.optionalcourse.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService(){
        return userService;
    }
}
