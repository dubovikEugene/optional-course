package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GetCoursesPage implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final String COURSES_PAGE = "WEB-INF/jsp/courses.jsp";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSES_PARAM = "courses";
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var allCourses = serviceFactory.getCourseRunService().findAllCoursesRuns();
            request.setAttribute(COURSES_PARAM, allCourses);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }
        request.getRequestDispatcher(COURSES_PAGE).forward(request, response);
    }
}
