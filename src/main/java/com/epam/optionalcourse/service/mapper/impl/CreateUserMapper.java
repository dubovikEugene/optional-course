package com.epam.optionalcourse.service.mapper.impl;

import com.epam.optionalcourse.bean.CreateUser;
import com.epam.optionalcourse.bean.Gender;
import com.epam.optionalcourse.bean.User;
import com.epam.optionalcourse.service.mapper.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class CreateUserMapper implements Mapper<CreateUser, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String DEFAULT_ROLE = "student";
    private static final String DATE_FORMATTER_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN);


    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    private CreateUserMapper() {
    }

    @Override
    public User mapFrom(CreateUser object) {
        User user = new User();
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setBirthday(formatToLocalDate(object.getBirthday()));
        user.setEmail(object.getEmail());
        user.setPassword(object.getPassword());
        user.setRole(DEFAULT_ROLE);
        user.setGender(Gender.find(object.getGender()).orElse(null));
        return user;
    }

    private LocalDate formatToLocalDate(String birthday) {
        return LocalDate.parse(birthday, FORMATTER);
    }
}
