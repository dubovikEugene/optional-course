package com.epam.optionalcourse.controller.command.impl.course;

import com.epam.optionalcourse.bean.course.UpdateCourseRun;
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

public class EditCourse implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String USER_PARAM = "user";
    private static final String COURSE_ID_PARAM = "course_id";
    private static final String COURSE_START_DATE_PARAM = "startDate";
    private static final String COURSE_END_DATE_PARAM = "endDate";
    private static final String COURSE_PROGRAM_PARAM = "courseProgram";
    private static final String COURSE_PARAM = "course";
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSE_PAGE = "WEB-INF/jsp/coursePage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var updateCourseRun = buildUpdateCourseRun(request);

        try {
            var updatedReadCourseRun = serviceFactory.getCourseRunService().updateCourseRunById(updateCourseRun);
            request.setAttribute(COURSE_PARAM, updatedReadCourseRun);
            request.getRequestDispatcher(COURSE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }

    }

    private UpdateCourseRun buildUpdateCourseRun(HttpServletRequest request) {
        return new UpdateCourseRun(
                request.getParameter(COURSE_ID_PARAM),
                request.getParameter(COURSE_START_DATE_PARAM),
                request.getParameter(COURSE_END_DATE_PARAM),
                request.getParameter(COURSE_PROGRAM_PARAM)
        );
    }
}
