package com.epam.optionalcourse.service.mapper.impl;

import com.epam.optionalcourse.bean.course.CourseRun;
import com.epam.optionalcourse.bean.course.UpdateCourseRun;
import com.epam.optionalcourse.service.mapper.Mapper;

import java.time.LocalDate;

public final class UpdateCourseRunMapper implements Mapper<UpdateCourseRun, CourseRun> {

    private static final UpdateCourseRunMapper INSTANCE = new UpdateCourseRunMapper();
    private static final String NEW_CHAR_LINE_BREAK = "<br>";

    private UpdateCourseRunMapper() {
    }

    @Override
    public CourseRun mapFrom(UpdateCourseRun object) {
        var replace = object.getCourseProgram().replace(System.lineSeparator(), NEW_CHAR_LINE_BREAK);
        var courseRun = new CourseRun();
        courseRun.setId(Integer.parseInt(object.getId()));
        courseRun.setStartDate(LocalDate.parse(object.getStartDate()));
        courseRun.setEndDate(LocalDate.parse(object.getEndDate()));
        courseRun.setCourseProgram(replace);
        return courseRun;
    }

    public static UpdateCourseRunMapper getInstance() {
        return INSTANCE;
    }
}
