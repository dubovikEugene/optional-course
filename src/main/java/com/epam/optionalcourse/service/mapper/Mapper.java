package com.epam.optionalcourse.service.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
