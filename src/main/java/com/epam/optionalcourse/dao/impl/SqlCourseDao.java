package com.epam.optionalcourse.dao.impl;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.dao.CourseDao;

public class SqlCourseDao implements CourseDao {

    private static final SqlCourseDao INSTANCE = new SqlCourseDao();

    private SqlCourseDao() {
    }

    public static SqlCourseDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void createCourse(Course course) {

    }

    @Override
    public void updateCourse(Course course) {

    }
}
