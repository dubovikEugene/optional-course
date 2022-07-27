package com.epam.optionalcourse.service.validator.impl;

import com.epam.optionalcourse.bean.user.CreateUser;
import com.epam.optionalcourse.bean.user.Gender;
import com.epam.optionalcourse.service.validator.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Pattern;

public final class UserRegisterValidator implements Validator<CreateUser> {

    private static final UserRegisterValidator INSTANCE = new UserRegisterValidator();
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[]A-Za-z ,-\\.`]{2,45}");
    private static final Pattern VALID_EMAIL_REGEX =
            Pattern.compile("^[A-Z\\d._%+-]+@[A-Z\\d.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private UserRegisterValidator() {
    }

    public static UserRegisterValidator getInstance() {
        return INSTANCE;
    }

    // TODO: 6/20/2022 change returned value. Need info what`s wrong
    // TODO: 6/20/2022 validate password?
    @Override
    public boolean validate(CreateUser createUser) {
        if (!validateName(createUser.getFirstName())) {
            return false;
        }
        if (!validateName(createUser.getLastName())) {
            return false;
        }
        if (!emailValidate(createUser.getEmail())) {
            return false;
        }
        if (!birthdayValidate(createUser.getBirthday())) {
            return false;
        }
        if (!genderValidation(createUser.getGender())) {
            return false;
        }
        return true;
    }

    private boolean genderValidation(String gender) {
        return gender == null || Gender.find(gender).isPresent();
    }

    private boolean birthdayValidate(String birthday) {
        try {
            return Optional.ofNullable(birthday)
                    .map(LocalDate::parse)
                    .isPresent();
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    private boolean emailValidate(String email) {
        return VALID_EMAIL_REGEX.matcher(email).find();
    }

    private boolean validateName(String name) {
        return VALID_NAME_REGEX.matcher(name).find();
    }

}
