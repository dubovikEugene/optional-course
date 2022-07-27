package com.epam.optionalcourse.controller.command.impl.user;

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

public class GetListOfCourseStudents implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String LIST_OF_COURSE_STUDENTS_PAGE = "WEB-INF/jsp/listOfCourseStudentsPage.jsp";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSE_ID_PARAM = "course_id";
    private static final String USERS_LIST_PARAM = "users";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var courseRunId = Integer.parseInt(request.getParameter(COURSE_ID_PARAM));
        try {
            var studentsByCourseRunId = serviceFactory.getUserService().getAllStudentsByCourseRunId(courseRunId);
            request.setAttribute(USERS_LIST_PARAM, studentsByCourseRunId);
            request.setAttribute(COURSE_ID_PARAM, courseRunId);
            request.getRequestDispatcher(LIST_OF_COURSE_STUDENTS_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }

    }
}
