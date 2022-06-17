package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.AuthorizedUser;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignIn implements Command {

    private static final String REQUEST_EMAIL = "email";
    private static final String REQUEST_PASSWORD = "password";
    private static final String USER_SESSION_ATTRIBUTE = "user";
    private static final String SUCCESS_REDIRECT_COMMAND = "controller?command=get_main_page";
    private static final String FAILURE_REDIRECT_COMMAND = "controller?command=get_sign_in_page&error=sign_in_error";
    ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            var authorizedUser = serviceFactory.getUserService()
                    .signIn(request.getParameter(REQUEST_EMAIL), request.getParameter(REQUEST_PASSWORD));
            onSuccessLogin(authorizedUser, request, response);
        } catch (ServiceException e) {
            // TODO: 6/13/2022 logger
            onFailureLogin(request, response);
            throw new RuntimeException(e);
        }
    }

    private void onFailureLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(FAILURE_REDIRECT_COMMAND);
    }

    private void onSuccessLogin(AuthorizedUser authorizedUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, authorizedUser);
        response.sendRedirect(SUCCESS_REDIRECT_COMMAND);
    }
}
