package com.epam.optionalcourse.dao;

import com.epam.optionalcourse.bean.course.CourseRun;
import com.epam.optionalcourse.bean.course.ReadCourseRun;
import com.epam.optionalcourse.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CourseRunDao {

    List<ReadCourseRun> findAllCourses() throws DaoException;

    List<ReadCourseRun> findAllCourseRunsByUserId(Integer studentId) throws DaoException;

    Optional<ReadCourseRun> createCourseRun(CourseRun courseRun) throws DaoException;

    Optional<ReadCourseRun> updateCourseRun(CourseRun courseRun) throws DaoException;

    Optional<ReadCourseRun> findById(Integer courseId) throws DaoException;

}
