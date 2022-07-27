package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SignIn implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String REQUEST_EMAIL = "email";
    private static final String REQUEST_PASSWORD = "password";
    private static final String USER_SESSION_ATTRIBUTE = "user";
    private static final String SUCCESS_COMMAND = "controller?command=get_profile_page";
    private static final String FAILURE_COMMAND = "controller?command=get_sign_in_page&error=sign_in_error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            var authorizedUser = serviceFactory.getUserService()
                    .signIn(
                            request.getParameter(REQUEST_EMAIL),
                            request.getParameter(REQUEST_PASSWORD)
                    );

            onSuccessLogin(authorizedUser, request, response);

        } catch (ServiceException e) {
            logger.error(e);
            onFailureLogin(request, response);
        }
    }

    private void onFailureLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(FAILURE_COMMAND).forward(request, response);
    }

    private void onSuccessLogin(AuthorizedUser authorizedUser, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, authorizedUser);
        request.getRequestDispatcher(SUCCESS_COMMAND).forward(request, response);
    }
}
