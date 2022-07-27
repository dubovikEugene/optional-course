package com.epam.optionalcourse.controller.command.impl.user;

import com.epam.optionalcourse.bean.user.AuthorizedUser;
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

public class GetTeacherCoursesPage implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String USER_PARAM = "user";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String TEACHER_COURSES_PAGE = "WEB-INF/jsp/teacherCoursesPage.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var session = request.getSession();
        var authorizedUser = (AuthorizedUser) session.getAttribute(USER_PARAM);

        try {
            var courseRuns = serviceFactory.getCourseRunService().findAllCourseRunsByUserId(authorizedUser.getId());
            authorizedUser.setCourses(courseRuns);
            session.setAttribute(USER_PARAM, authorizedUser);
            request.getRequestDispatcher(TEACHER_COURSES_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }


    }
}
