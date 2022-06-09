package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.Course;

public interface CourseDao {

    void createCourse(Course course);
    void updateCourse(Course course);

}
