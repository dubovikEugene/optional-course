package com.epam.optionalcourse.service.impl;

import com.epam.optionalcourse.bean.TeacherFeedback;
import com.epam.optionalcourse.controller.command.impl.AddFeedback;
import com.epam.optionalcourse.dao.exception.DaoException;
import com.epam.optionalcourse.dao.factory.DaoFactory;
import com.epam.optionalcourse.service.TeacherFeedbackService;
import com.epam.optionalcourse.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TeacherFeedbackServiceImpl implements TeacherFeedbackService {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void addTeacherFeedback(TeacherFeedback teacherFeedback) throws ServiceException {

        try {
            daoFactory.getTeacherFeedbackDao().addTeacherFeedback(teacherFeedback);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
