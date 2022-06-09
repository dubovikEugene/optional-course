package com.epam.optionalcourse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String REQUEST_COMMAND = "command";
    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    public void destroy() {
//        ConnectionPool.closePool();
        super.destroy();
    }

    private void executeRequest(HttpServletRequest req, HttpServletResponse resp) {
        var commandName = req.getParameter(REQUEST_COMMAND);
        var command = commandProvider.getCommand(commandName);
        command.execute(req, resp);
    }
}
