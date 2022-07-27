package com.epam.optionalcourse.service.mapper.impl;

import com.epam.optionalcourse.bean.course.CourseRun;
import com.epam.optionalcourse.bean.course.CreateCourseRun;
import com.epam.optionalcourse.service.mapper.Mapper;

import java.time.LocalDate;

public final class CreateCourseRunMapper implements Mapper<CreateCourseRun, CourseRun> {

    private static final CreateCourseRunMapper INSTANCE = new CreateCourseRunMapper();
    private static final String NEW_CHAR_LINE_BREAK = "<br>";

    private CreateCourseRunMapper() {
    }

    @Override
    public CourseRun mapFrom(CreateCourseRun object) {
        var replace = object.getCourseProgram().replace(System.lineSeparator(), NEW_CHAR_LINE_BREAK);
        var courseRun = new CourseRun();
        courseRun.setStartDate(LocalDate.parse(object.getStartDate()));
        courseRun.setEndDate(LocalDate.parse(object.getEndDate()));
        courseRun.setCourseProgram(replace);
        courseRun.setCourseId(Integer.parseInt(object.getCourseId()));
        courseRun.setTeacherId(object.getTeacherId());
        return courseRun;
    }

    public static CreateCourseRunMapper getInstance() {
        return INSTANCE;
    }
}
