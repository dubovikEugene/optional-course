package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetSignInPage implements Command {

    private static final String SIGN_IN_PAGE_PATH = "WEB-INF/jsp/signIn.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(SIGN_IN_PAGE_PATH).forward(request, response);
    }
}
