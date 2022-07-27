package com.epam.optionalcourse.controller;

import java.util.Arrays;
import java.util.Optional;

public enum CommandName {

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
    CHANGE_LOCAl,
    DROP_COURSE_FROM_STUDENT,
    GET_TEACHER_COURSES_PAGE,
    GET_COURSE_EDIT_PAGE,
    EDIT_COURSE,
    GET_CREATE_COURSE_RUN_PAGE,
    CREATE_COURSE_RUN,
    GET_LIST_OF_COURSE_STUDENTS_PAGE,
    GET_FEEDBACK_PAGE,
    WRONG_REQUEST;


    public static Optional<CommandName> find(String name) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(name))
                .findFirst();
    }

}
