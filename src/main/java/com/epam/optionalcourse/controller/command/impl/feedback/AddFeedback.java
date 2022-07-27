package com.epam.optionalcourse.controller.command.impl.feedback;

import com.epam.optionalcourse.bean.feedback.TeacherFeedback;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AddFeedback implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String WRONG_REQUEST_PATH = "WEB-INF/jsp/wrongRequest.jsp";
    private static final String COURSE_RUN_ID_PARAM = "courseRunId";
    private static final String STUDENT_ID_PARAM = "studentId";
    private static final String MARK_PARAM = "mark";
    private static final String FEEDBACK_PARAM = "feedback";
    private static final String SUCCESS_REDIRECT_COMMAND = "controller?command=get_list_of_course_students_page&course_id=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            var teacherFeedback = buildTeacherFeedback(request);
            serviceFactory.getTeacherFeedbackService().addTeacherFeedback(teacherFeedback);

            response.sendRedirect(String.format(SUCCESS_REDIRECT_COMMAND, teacherFeedback.getCourseRunId()));

        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(WRONG_REQUEST_PATH).forward(request, response);
        }

        System.out.println();
    }

    private TeacherFeedback buildTeacherFeedback(HttpServletRequest request) {
        var courseRunId = Integer.parseInt(request.getParameter(COURSE_RUN_ID_PARAM));
        var studentId = Integer.parseInt(request.getParameter(STUDENT_ID_PARAM));
        var mark = Integer.parseInt(request.getParameter(MARK_PARAM));
        var feedback = request.getParameter(FEEDBACK_PARAM);
        return new TeacherFeedback(
                courseRunId,
                studentId,
                mark,
                feedback
        );
    }
}
