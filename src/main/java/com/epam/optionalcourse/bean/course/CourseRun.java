package com.epam.optionalcourse.bean.course;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CourseRun implements Serializable {

    @Serial
    private static final long serialVersionUID = 285880707909758916L;
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String courseProgram;
    private Integer courseId;
    private Integer teacherId;
    private Integer isEnded;


    public CourseRun() {
    }

    public CourseRun(Integer id,
                     LocalDate startDate,
                     LocalDate endDate,
                     String courseProgram,
                     Integer courseId,
                     Integer teacherId,
                     Integer isEnded) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseProgram = courseProgram;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.isEnded = isEnded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCourseProgram() {
        return courseProgram;
    }

    public void setCourseProgram(String courseProgram) {
        this.courseProgram = courseProgram;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getIsEnded() {
        return isEnded;
    }

    public void setIsEnded(Integer isEnded) {
        this.isEnded = isEnded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRun courseRun = (CourseRun) o;
        return Objects.equals(id, courseRun.id)
                && Objects.equals(startDate, courseRun.startDate)
                && Objects.equals(endDate, courseRun.endDate)
                && Objects.equals(courseProgram, courseRun.courseProgram)
                && Objects.equals(courseId, courseRun.courseId)
                && Objects.equals(teacherId, courseRun.teacherId)
                && Objects.equals(isEnded, courseRun.isEnded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, courseProgram, courseId, teacherId, isEnded);
    }
}
