package com.epam.optionalcourse.controller.command.impl;

import com.epam.optionalcourse.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class ChangeLocal implements Command {

    private static final String LOCAL = "local";
    private static final String REFERER = "Referer";
    private static final Pattern PREV_COMMAND_PATTERN = Pattern.compile("controller[\\w\\d\\?=&]+");
    private static final Pattern LOCAL_COMMAND_PATTERN = Pattern.compile("command=change_local$");
    private static final String HOME_PAGE = "controller?command=get_main_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));
        var prevPage = request.getHeader(REFERER);
        var matcherPrevCommand = PREV_COMMAND_PATTERN.matcher(prevPage);
        var matcherLocalCommand = LOCAL_COMMAND_PATTERN.matcher(prevPage);
        if (matcherPrevCommand.find() && !matcherLocalCommand.find()) {
            var group = matcherPrevCommand.group();
            request.getRequestDispatcher(group).forward(request, response);
        }
        request.getRequestDispatcher(HOME_PAGE).forward(request, response);
    }
}
