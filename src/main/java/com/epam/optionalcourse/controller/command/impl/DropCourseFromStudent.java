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

public class DropCourseFromStudent implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String USER_PARAM = "user";
    private static final String REQUEST_COURSE_ID_PARAM = "course_id";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String PROFILE_REQUEST_PATH = "WEB-INF/jsp/studentProfile.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var session = request.getSession();
            var authorizedUser = (AuthorizedUser) session.getAttribute(USER_PARAM);
            var courseId = Integer.parseInt(request.getParameter(REQUEST_COURSE_ID_PARAM));

            var updatedAuthorizedUser = serviceFactory.getUserService()
                    .dropCourseFromStudent(authorizedUser, courseId);

            session.setAttribute(USER_PARAM, updatedAuthorizedUser);

            request.getRequestDispatcher(PROFILE_REQUEST_PATH).forward(request, response);

        } catch (ServiceException exception) {
            logger.error(exception);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }
    }

}
