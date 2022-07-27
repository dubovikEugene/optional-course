package com.epam.optionalcourse.bean.course;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UpdateCourseRun implements Serializable {

    @Serial
    private static final long serialVersionUID = -6259319981033370512L;
    private String id;
    private String startDate;
    private String endDate;
    private String courseProgram;

    public UpdateCourseRun() {
    }

    public UpdateCourseRun(String id, String startDate, String endDate, String courseProgram) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseProgram = courseProgram;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCourseProgram() {
        return courseProgram;
    }

    public void setCourseProgram(String courseProgram) {
        this.courseProgram = courseProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCourseRun that = (UpdateCourseRun) o;
        return Objects.equals(id, that.id)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(courseProgram, that.courseProgram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, courseProgram);
    }
}
