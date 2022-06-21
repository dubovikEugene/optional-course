package com.epam.optionalcourse.controller;

import java.util.Arrays;
import java.util.Optional;

public enum CommandName{

    SIGN_IN,
    GET_REGISTRATION_PAGE,
    GET_SIGN_IN_PAGE,
    GET_MAIN_PAGE,
    REGISTRATION,
    SIGN_OUT,
    GET_COURSES_PAGE,
    GET_COURSE_PAGE,
    GET_PROFILE_PAGE,
    CREATE_COURSE,
    UPDATE_COURSE,
    GET_COURSE,
    GET_ALL_COURSE,
    ADD_FEEDBACK,
    REGISTER_FOR_A_COURSE,
    DELETE_COURSE_FROM_STUDENT,
    WRONG_REQUEST;


    public static Optional<CommandName> find(String name){
        return Arrays.stream(values())
                .filter(it-> it.name().equalsIgnoreCase(name))
                .findFirst();
    }

}
