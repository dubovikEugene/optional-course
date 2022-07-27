package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.course.CreateCourseRun;
import com.epam.optionalcourse.bean.course.ReadCourseRun;
import com.epam.optionalcourse.bean.course.UpdateCourseRun;
import com.epam.optionalcourse.service.exception.ServiceException;

import java.util.List;

public interface CourseRunService {

    List<ReadCourseRun> findAllCoursesRuns() throws ServiceException;

    ReadCourseRun findCourseRunById(Integer courseId) throws ServiceException;

    List<ReadCourseRun> findAllCourseRunsByUserId(Integer id) throws ServiceException;

    ReadCourseRun updateCourseRunById(UpdateCourseRun updateCourseRun) throws ServiceException;

    ReadCourseRun createCourseRun(CreateCourseRun createCourseRun) throws ServiceException;
}
