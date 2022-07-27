package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.course.CourseRun;
import com.epam.optionalcourse.bean.course.CreateCourseRun;
import com.epam.optionalcourse.bean.course.ReadCourseRun;
import com.epam.optionalcourse.bean.course.UpdateCourseRun;
import com.epam.optionalcourse.controller.command.impl.feedback.AddFeedback;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import com.epam.optionalcourse.service.CourseRunService;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.mapper.Mapper;
import com.epam.optionalcourse.service.mapper.impl.CreateCourseRunMapper;
import com.epam.optionalcourse.service.mapper.impl.UpdateCourseRunMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseRunServiceImpl implements CourseRunService {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Mapper<UpdateCourseRun, CourseRun> updateCourseMapper = UpdateCourseRunMapper.getInstance();
    private static final Mapper<CreateCourseRun, CourseRun> createCourseRunMapper = CreateCourseRunMapper.getInstance();

    public CourseRunServiceImpl() {
    }

    @Override
    public List<ReadCourseRun> findAllCoursesRuns() throws ServiceException {
        try {
            var courseDao = daoFactory.getCourseRunDao();
            return courseDao.findAllCourses();

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ReadCourseRun findCourseRunById(Integer courseId) throws ServiceException {
        try {
            var course = daoFactory.getCourseRunDao().findById(courseId);
            if (course.isPresent()) {
                return course.get();
            } else {
                throw new ServiceException("Can`t find course with this id");
            }

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReadCourseRun> findAllCourseRunsByUserId(Integer id) throws ServiceException {
        try {
            return daoFactory.getCourseRunDao().findAllCourseRunsByUserId(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    @Override
    public ReadCourseRun updateCourseRunById(UpdateCourseRun updateCourseRun) throws ServiceException {

        try {
            var courseRun = updateCourseMapper.mapFrom(updateCourseRun);
            var updatedReadCourseRun = daoFactory.getCourseRunDao().updateCourseRun(courseRun);
            if (updatedReadCourseRun.isEmpty()) {
                throw new ServiceException("Don't find updated course");
            }
            return updatedReadCourseRun.get();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ReadCourseRun createCourseRun(CreateCourseRun createCourseRun) throws ServiceException {
        var courseRun = createCourseRunMapper.mapFrom(createCourseRun);

        try {
            var readCourseRun = daoFactory.getCourseRunDao().createCourseRun(courseRun);
            if (readCourseRun.isEmpty()) {
                throw new ServiceException("is empty");
            }

            return readCourseRun.get();

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }
}
