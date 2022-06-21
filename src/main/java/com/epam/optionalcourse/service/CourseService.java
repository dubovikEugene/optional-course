package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.ReadCourseRun;
import com.epam.optionalcourse.service.exception.ServiceException;

import java.util.List;

public interface CourseService {

    List<ReadCourseRun> findAll() throws ServiceException;

    ReadCourseRun findById(Integer courseId) throws ServiceException;
}
