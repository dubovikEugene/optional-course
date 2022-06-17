package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetMainPage implements Command {

    private static final String MAIN_PAGE_PATH = "index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
    }
}
