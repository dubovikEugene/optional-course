package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.bean.CreateUser;
import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.service.exception.ServiceException;
import com.epam.optionalcourse.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Registration implements Command {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static final String REQUEST_PARAMETER_FIRST_NAME = "firstName";
    private static final String REQUEST_PARAMETER_LAST_NAME = "lastName";
    private static final String REQUEST_PARAMETER_BIRTHDAY = "birthday";
    private static final String REQUEST_PARAMETER_EMAIL = "email";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REQUEST_PARAMETER_GENDER = "gender";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        var createUser = buildCreateUser(request);
        try {
            var authorizedUser = serviceFactory.getUserService().register(createUser);
            request.setAttribute("user", authorizedUser);
            // TODO: 6/10/2022 logic
        } catch (ServiceException e) {
            throw new RuntimeException(e);
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
