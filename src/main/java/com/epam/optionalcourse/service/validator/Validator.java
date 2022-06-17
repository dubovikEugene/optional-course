package com.epam.optionalcourse.service.validator;

public interface Validator<T> {

    boolean validate(T t);
}
