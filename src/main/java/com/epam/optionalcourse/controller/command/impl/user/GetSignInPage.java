package com.epam.optionalcourse.controller.command.impl.user;

import com.epam.optionalcourse.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class GetSignInPage implements Command {

    private static final String SIGN_IN_PAGE_PATH = "WEB-INF/jsp/signIn.jsp";
    private static final String REFERER = "Referer";
    private static final Pattern PATTERN = Pattern.compile("controller[\\w\\d\\?=&]+");
    private static final String PREV_PAGE = "prevPage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var prevPage = request.getHeader(REFERER);
        var matcher = PATTERN.matcher(prevPage);
        if (matcher.find()) {
            var group = matcher.group();
            request.getSession().setAttribute(PREV_PAGE, group);
        }
        request.getRequestDispatcher(SIGN_IN_PAGE_PATH).forward(request, response);
    }
}
