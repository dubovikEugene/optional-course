package com.epam.optionalcourse.service;

import com.epam.optionalcourse.bean.course.Course;
import com.epam.optionalcourse.service.exception.ServiceException;

import java.util.List;

public interface CourseService {

    public List<Course> findAll() throws ServiceException;
}
