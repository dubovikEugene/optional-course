package com.epam.optionalcourse.controller;

import com.epam.optionalcourse.dao.connectionpool.ConnectionPool;
import com.epam.optionalcourse.dao.exception.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/controller")
public class Controller extends HttpServlet {


    private static final Logger logger = LogManager.getLogger(Controller.class);
    @Serial
    private static final long serialVersionUID = -5111427906188857300L;
    private static final String REQUEST_COMMAND = "command";
    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().initConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

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
        try {
            ConnectionPool.getInstance().closePool();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
        super.destroy();
    }

    private void executeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var commandName = req.getParameter(REQUEST_COMMAND);
        var command = commandProvider.getCommand(commandName);
        command.execute(req, resp);
    }
}
