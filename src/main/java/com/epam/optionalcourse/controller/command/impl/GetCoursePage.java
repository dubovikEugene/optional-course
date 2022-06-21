package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetCoursePage implements Command {
    private static final String REQUEST_COURSE_ID_PARAM = "course_id";
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String COURSE_PAGE_PATH = "WEB-INF/jsp/coursePage.jsp";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var courseIdStr = request.getParameter(REQUEST_COURSE_ID_PARAM);
        Integer courseId = null;
        if (courseIdStr != null) {
            courseId = Integer.valueOf(courseIdStr);
        } else {
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request,response);
        }

        try {
            var course = serviceFactory.getCourseService().findById(courseId);
            request.setAttribute("course", course);
        } catch (ServiceException e) {
            // TODO: 6/17/2022 logger
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request,response);
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher(COURSE_PAGE_PATH).forward(request, response);
    }
}
