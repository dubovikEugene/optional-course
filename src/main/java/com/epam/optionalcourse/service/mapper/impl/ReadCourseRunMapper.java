package com.epam.optionalcourse.service.mapper.impl;

import com.epam.optionalcourse.bean.CourseRun;
import com.epam.optionalcourse.bean.ReadCourseRun;
import com.epam.optionalcourse.service.mapper.Mapper;

public final class ReadCourseRunMapper implements Mapper<CourseRun, ReadCourseRun> {

    private static final ReadCourseRunMapper INSTANCE = new ReadCourseRunMapper();

    private ReadCourseRunMapper() {
    }

    @Override
    public ReadCourseRun mapFrom(CourseRun object) {
        return null;
    }

    public static ReadCourseRunMapper getINSTANCE() {
        return INSTANCE;
    }
}
