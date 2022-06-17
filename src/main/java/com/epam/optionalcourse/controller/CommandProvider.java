package com.epam.optionalcourse.controller;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.controller.command.impl.GetCoursesPage;
import com.epam.optionalcourse.controller.command.impl.GetMainPage;
import com.epam.optionalcourse.controller.command.impl.GetRegistrationPage;
import com.epam.optionalcourse.controller.command.impl.GetSignInPage;
import com.epam.optionalcourse.controller.command.impl.Registration;
import com.epam.optionalcourse.controller.command.impl.SignIn;
import com.epam.optionalcourse.controller.command.impl.SignOut;
import com.epam.optionalcourse.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider INSTANCE = new CommandProvider();

    private CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.GET_REGISTRATION_PAGE, new GetRegistrationPage());
        repository.put(CommandName.GET_MAIN_PAGE, new GetMainPage());
        repository.put(CommandName.GET_SIGN_IN_PAGE, new GetSignInPage());
        repository.put(CommandName.GET_COURSES_PAGE, new GetCoursesPage());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {

        var optionalCommandName = CommandName.find(name);

        if (optionalCommandName.isPresent()) {
            return repository.get(optionalCommandName.get());
        }
        return repository.get(CommandName.WRONG_REQUEST);
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }


}
