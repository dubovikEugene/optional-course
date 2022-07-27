package com.epam.optionalcourse.bean.course;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ReadCourseRun implements Serializable {

    @Serial
    private static final long serialVersionUID = 285880707909758916L;
    private Integer id;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    private String courseProgram;
    private String teacherFirstName;
    private String teacherLastName;


    public ReadCourseRun() {
    }

    public ReadCourseRun(Integer id,
                         String courseName,
                         LocalDate startDate,
                         LocalDate endDate,
                         String description,
                         String courseProgram,
                         String teacherFirstName,
                         String teacherLastName) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.courseProgram = courseProgram;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseProgram() {
        return courseProgram;
    }

    public void setCourseProgram(String courseProgram) {
        this.courseProgram = courseProgram;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadCourseRun course = (ReadCourseRun) o;
        return Objects.equals(id, course.id)
                && Objects.equals(courseName, course.courseName)
                && Objects.equals(startDate, course.startDate)
                && Objects.equals(endDate, course.endDate)
                && Objects.equals(description, course.description)
                && Objects.equals(courseProgram, course.courseProgram)
                && Objects.equals(teacherFirstName, course.teacherFirstName)
                && Objects.equals(teacherLastName, course.teacherLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                courseName,
                startDate,
                endDate,
                description,
                courseProgram,
                teacherFirstName,
                teacherLastName);
    }
}
