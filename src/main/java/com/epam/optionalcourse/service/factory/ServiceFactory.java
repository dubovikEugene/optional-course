package com.epam.optionalcourse.service.factory;

import com.epam.optionalcourse.service.CourseService;
import com.epam.optionalcourse.service.UserService;
import com.epam.optionalcourse.service.impl.CourseServiceImpl;
import com.epam.optionalcourse.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserServiceImpl userService = new UserServiceImpl();
    private final CourseServiceImpl courseService = new CourseServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService(){
        return userService;
    }

    public CourseService getCourseService() {
        return courseService;
    }
}
