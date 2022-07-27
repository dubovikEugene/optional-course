package com.epam.optionalcourse.service.factory;

import com.epam.optionalcourse.service.CourseRunService;
import com.epam.optionalcourse.service.UserService;
import com.epam.optionalcourse.service.impl.CourseRunServiceImpl;
import com.epam.optionalcourse.service.impl.CourseServiceImpl;
import com.epam.optionalcourse.service.impl.TeacherFeedbackServiceImpl;
import com.epam.optionalcourse.service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserServiceImpl userService = new UserServiceImpl();
    private final CourseRunServiceImpl courseRunService = new CourseRunServiceImpl();
    private final CourseServiceImpl courseService = new CourseServiceImpl();
    private final TeacherFeedbackServiceImpl teacherFeedbackService = new TeacherFeedbackServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public CourseRunService getCourseRunService() {
        return courseRunService;
    }

    public CourseServiceImpl getCourseService() {
        return courseService;
    }

    public TeacherFeedbackServiceImpl getTeacherFeedbackService() {
        return teacherFeedbackService;
    }
}
