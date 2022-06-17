package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.bean.ReadCourse;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.util.List;

public interface CourseDao {

    List<ReadCourse> getAllCourses() throws DaoException;
    void createCourse(Course course);
    void updateCourse(Course course);

}
