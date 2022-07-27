package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.Course;
import com.epam.optionalcourse.controller.command.impl.AddFeedback;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import com.epam.optionalcourse.service.CourseService;
import com.epam.optionalcourse.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public List<Course> findAll() throws ServiceException {
        try {
            return daoFactory.getCourseDao().findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
