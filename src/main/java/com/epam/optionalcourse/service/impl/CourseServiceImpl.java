package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.ReadCourse;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import com.epam.optionalcourse.service.CourseService;
import com.epam.optionalcourse.service.exception.ServiceException;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    public CourseServiceImpl() {
    }

    @Override
    public List<ReadCourse> findAll() throws ServiceException {
        try {
            var courseDao = daoFactory.getCourseDao();
            return courseDao.getAllCourses();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
