package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.ReadCourse;
import com.epam.optionalcourse.service.exception.ServiceException;

import java.util.List;

public interface CourseService {

    List<ReadCourse> findAll() throws ServiceException;
}
