package com.epam.optionalcourse.bean;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Course implements Serializable {

    @Serial
    private static final long serialVersionUID = 285880707909758916L;
    private Integer id;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private User teacher;


    public Course() {
    }

    public Course(Integer id,
                  String courseName,
                  LocalDate startDate,
                  LocalDate endDate,
                  String description,
                  User teacher) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.teacher = teacher;
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id)
                && Objects.equals(courseName, course.courseName)
                && Objects.equals(startDate, course.startDate)
                && Objects.equals(endDate, course.endDate)
                && Objects.equals(description, course.description)
                && Objects.equals(teacher, course.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                courseName,
                startDate,
                endDate,
                description,
                teacher);
    }
}
