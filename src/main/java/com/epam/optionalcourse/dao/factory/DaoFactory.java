package com.epam.optionalcourse.dao.factory;

import com.epam.optionalcourse.dao.CourseDao;
import com.epam.optionalcourse.dao.CourseRunDao;
import com.epam.optionalcourse.dao.TeacherFeedbackDao;
import com.epam.optionalcourse.dao.UserDao;
import com.epam.optionalcourse.dao.impl.CourseDaoImpl;
import com.epam.optionalcourse.dao.impl.CourseRunDaoImpl;
import com.epam.optionalcourse.dao.impl.TeacherFeedbackDaoImpl;
import com.epam.optionalcourse.dao.impl.UserDaoImpl;

public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final CourseRunDao courseRunDao = new CourseRunDaoImpl();
    private final CourseDaoImpl courseDao = new CourseDaoImpl();
    private final TeacherFeedbackDao teacherFeedbackDao = new TeacherFeedbackDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CourseRunDao getCourseRunDao() {
        return courseRunDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public TeacherFeedbackDao getTeacherFeedbackDao() {
        return teacherFeedbackDao;
    }


}
