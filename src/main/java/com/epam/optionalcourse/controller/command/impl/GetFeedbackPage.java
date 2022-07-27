package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetFeedbackPage implements Command {

    private static final String FEEDBACK_PAGE = "WEB-INF/jsp/feedbackPage.jsp";
    private static final String USER_ID_PARAM = "userId";
    private static final String COURSE_RUN_ID_PARAM = "courseRunId";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var userId = request.getParameter(USER_ID_PARAM);
        var courseRunId = request.getParameter(COURSE_RUN_ID_PARAM);
        request.setAttribute(USER_ID_PARAM, userId);
        request.setAttribute(COURSE_RUN_ID_PARAM, courseRunId);
        request.getRequestDispatcher(FEEDBACK_PAGE).forward(request,response);
    }
}
