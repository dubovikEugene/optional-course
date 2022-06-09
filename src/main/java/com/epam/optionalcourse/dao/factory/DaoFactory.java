package com.epam.optionalcourse.dao.factory;

import com.epam.optionalcourse.dao.CourseDao;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.impl.SqlCourseDao;
import com.epam.optionalcourse.dao.impl.SqlUserDao;

public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final UserDao sqlUserDao = SqlUserDao.getInstance();
    private final CourseDao sqlCourseDao = SqlCourseDao.getInstance();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public UserDao getSqlUserDao() {
        return sqlUserDao;
    }

    public CourseDao getSqlCourseDao() {
        return sqlCourseDao;
    }
}
