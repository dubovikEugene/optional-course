package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.CreateUser;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Registration implements Command {

    private static final Logger logger = LogManager.getLogger(AddFeedback.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String REQUEST_PARAMETER_FIRST_NAME = "firstName";
    private static final String REQUEST_PARAMETER_LAST_NAME = "lastName";
    private static final String REQUEST_PARAMETER_BIRTHDAY = "birthday";
    private static final String REQUEST_PARAMETER_EMAIL = "email";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REQUEST_PARAMETER_GENDER = "gender";
    private static final String USER_SESSION_ATTRIBUTE = "user";
    private static final String FAILURE_REDIRECT_COMMAND = "controller?command=get_register_page&error=%s";
    private static final String SUCCESS_REDIRECT_COMMAND = "controller?command=get_profile_page";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var createUser = buildCreateUser(request);
        try {
            var authorizedUser = serviceFactory.getUserService().register(createUser);
            request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, authorizedUser);
            response.sendRedirect(SUCCESS_REDIRECT_COMMAND);
            // TODO: 6/20/2022 authorization error logic
        } catch (ServiceException e) {
            response.sendRedirect(FAILURE_REDIRECT_COMMAND);
            logger.error(e);
        }

    }

    private CreateUser buildCreateUser(HttpServletRequest request) {
        return new CreateUser(
                request.getParameter(REQUEST_PARAMETER_FIRST_NAME),
                request.getParameter(REQUEST_PARAMETER_LAST_NAME),
                request.getParameter(REQUEST_PARAMETER_BIRTHDAY),
                request.getParameter(REQUEST_PARAMETER_EMAIL),
                request.getParameter(REQUEST_PARAMETER_PASSWORD),
                request.getParameter(REQUEST_PARAMETER_GENDER));
    }

}
