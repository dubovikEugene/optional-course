package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.bean.ReadCourseRun;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    List<ReadCourseRun> getAllCourses() throws DaoException;
    void createCourse(Course course);
    void updateCourse(Course course);

    Optional<ReadCourseRun> findById(Integer courseId) throws DaoException;
}
