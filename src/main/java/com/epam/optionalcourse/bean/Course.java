package com.epam.optionalcourse.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 1667131033222982874L;

    Integer id;
    String courseName;
    String description;

    public Course() {
    }

    public Course(Integer id, String courseName, String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(courseName, course.courseName) && Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, description);
    }
}
