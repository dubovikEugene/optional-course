package com.epam.optionalcourse.controller;

import com.epam.optionalcourse.controller.command.Command;
import com.epam.optionalcourse.controller.command.impl.Registration;
import com.epam.optionalcourse.controller.command.impl.SignIn;
import com.epam.optionalcourse.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider INSTANCE = new CommandProvider();

    private CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {

        var OptionalCommandName = CommandName.find(name);

        if (OptionalCommandName.isPresent()) {
            return repository.get(OptionalCommandName.get());
        }
        return repository.get(CommandName.WRONG_REQUEST);
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }


}
