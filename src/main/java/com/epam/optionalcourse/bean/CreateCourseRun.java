package com.epam.optionalcourse.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CreateCourseRun implements Serializable {

    @Serial
    private static final long serialVersionUID = 8123104151896180171L;

    private String startDate;
    private String endDate;
    private String courseProgram;
    private String courseId;
    private Integer teacherId;

    public CreateCourseRun() {
    }

    public CreateCourseRun(String startDate, String endDate, String courseProgram, String courseId, Integer teacherId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseProgram = courseProgram;
        this.courseId = courseId;
        this.teacherId = teacherId;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCourseRun that = (CreateCourseRun) o;
        return Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(courseProgram, that.courseProgram)
                && Objects.equals(courseId, that.courseId)
                && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, courseProgram, courseId, teacherId);
    }
}
