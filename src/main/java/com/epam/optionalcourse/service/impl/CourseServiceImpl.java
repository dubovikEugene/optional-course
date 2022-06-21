package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.ReadCourseRun;
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
    public List<ReadCourseRun> findAll() throws ServiceException {
        try {
            var courseDao = daoFactory.getCourseDao();
            return courseDao.getAllCourses();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ReadCourseRun findById(Integer courseId) throws ServiceException {
        try {
            var course = daoFactory.getCourseDao().findById(courseId);
            if (course.isPresent()) {
                return course.get();
            } else {
                throw new ServiceException("Can`t find course with this id");
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
