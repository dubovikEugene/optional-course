package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetProfilePage implements Command {

    private static final String STUDENT_PROFILE_PATH = "WEB-INF/jsp/studentProfile.jsp";
    private static final String TEACHER_PROFILE_PATH = "WEB-INF/jsp/teacherProfile.jsp";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String ROLE_STUDENT = "student";
    private static final String ROLE_TEACHER = "teacher";
    private static final String USER_PARAM = "user";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var user = (AuthorizedUser) request.getSession(false).getAttribute(USER_PARAM);
        // TODO: 6/20/2022 Map
        switch (user.getRole().toLowerCase()) {
            case ROLE_STUDENT -> request.getRequestDispatcher(STUDENT_PROFILE_PATH).forward(request, response);
            case ROLE_TEACHER -> request.getRequestDispatcher(TEACHER_PROFILE_PATH).forward(request, response);
            default -> request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }
    }
}
