package com.epam.optionalcourse.dao.factory;

import com.epam.optionalcourse.dao.CourseDao;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.impl.CourseDaoImpl;
import com.epam.optionalcourse.dao.impl.UserDaoImpl;

public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final CourseDao courseDao = new CourseDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }
}
