package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GetCourseEditPage implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String USER_PARAM = "user";
    private static final String COURSE_ID_PARAM = "course_id";
    private static final String COURSE_PARAM = "courseRun";
    private static final String USER_TEACHER_ROLE = "teacher";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSES_EDIT_PAGE = "WEB-INF/jsp/courseEditPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var courseId = Integer.parseInt(request.getParameter(COURSE_ID_PARAM));
        var user = (AuthorizedUser) request.getSession().getAttribute(USER_PARAM);
        if (user.getRole().equals(USER_TEACHER_ROLE)) {
            try {
                var courseRun = serviceFactory.getCourseRunService().findCourseRunById(courseId);
                request.setAttribute(COURSE_PARAM, courseRun);
                request.getRequestDispatcher(COURSES_EDIT_PAGE).forward(request, response);
            } catch (ServiceException e) {
                logger.error(e);
                request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
            }

        } else {
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }

    }
}
