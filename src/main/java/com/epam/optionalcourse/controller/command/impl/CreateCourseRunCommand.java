package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.bean.CreateCourseRun;
import com.epam.optionalcourse.bean.ReadCourseRun;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CreateCourseRunCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSE_PAGE_PATH = "WEB-INF/jsp/coursePage.jsp";
    private static final String USER_PARAM = "user";
    private static final String COURSE_ID_PARAM = "courseId";
    private static final String COURSE_PARAM = "course";
    private static final String START_DATE_PARAM = "startDate";
    private static final String END_DATE_PARAM = "endDate";
    private static final String COURSE_PROGRAM_PARAM = "courseProgram";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var authorizedUser = (AuthorizedUser) request.getSession().getAttribute(USER_PARAM);
        var createCourseRun = buildCreateCourseRun(request, authorizedUser.getId());

        try {
            var readCourseRun = serviceFactory.getCourseRunService().createCourseRun(createCourseRun);
            request.setAttribute(COURSE_PARAM, readCourseRun);
            request.getRequestDispatcher(COURSE_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }

    }

    private CreateCourseRun buildCreateCourseRun(HttpServletRequest request, Integer teacherID) {
        return new CreateCourseRun(
                request.getParameter(START_DATE_PARAM),
                request.getParameter(END_DATE_PARAM),
                request.getParameter(COURSE_PROGRAM_PARAM),
                request.getParameter(COURSE_ID_PARAM),
                teacherID

        );
    }
}
