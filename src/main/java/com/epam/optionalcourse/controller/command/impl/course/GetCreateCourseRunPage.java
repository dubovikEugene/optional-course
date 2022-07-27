package com.epam.optionalcourse.controller.command.impl.course;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.controller.command.impl.feedback.AddFeedback;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GetCreateCourseRunPage implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final String CREATE_COURSE_RUN_PAGE = "WEB-INF/jsp/createCourseRunPage.jsp";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSES_ATTR = "courses";
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var allCourses = serviceFactory.getCourseService().findAll();
            request.setAttribute(COURSES_ATTR, allCourses);
            request.getRequestDispatcher(CREATE_COURSE_RUN_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }

    }
}
