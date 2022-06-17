package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetCoursesPage implements Command {

    private static final String COURSES_PAGE = "WEB-INF/jsp/courses.jsp";
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var allCourses = serviceFactory.getCourseService().findAll();
            request.setAttribute("courses", allCourses);
        } catch (ServiceException e) {
            // TODO: 6/15/2022 logger
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher(COURSES_PAGE).forward(request, response);
    }
}
