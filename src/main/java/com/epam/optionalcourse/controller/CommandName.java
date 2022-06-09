package com.epam.optionalcourse.controller;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public enum CommandName{

    SIGN_IN,
    REGISTRATION,
    SIGN_OUT,
    CREATE_COURSE,
    UPDATE_COURSE,
    ADD_FEEDBACK,
    ADD_COURSE_TO_STUDENT,
    DELETE_COURSE_FROM_STUDENT,
    WRONG_REQUEST;


    public static Optional<CommandName> find(String name){
        return Arrays.stream(values())
                .filter(it-> it.name().equalsIgnoreCase(name))
                .findFirst();
    }

}
