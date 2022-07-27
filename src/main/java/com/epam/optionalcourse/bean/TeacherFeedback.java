package com.epam.optionalcourse.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class TeacherFeedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 8952997949195165599L;
    private Integer courseRunId;
    private Integer studentId;
    private Integer mark;
    private String feedback;

    public TeacherFeedback() {
    }

    public TeacherFeedback(Integer courseRunId, Integer studentId, Integer mark, String feedback) {
        this.courseRunId = courseRunId;
        this.studentId = studentId;
        this.mark = mark;
        this.feedback = feedback;
    }

    public Integer getCourseRunId() {
        return courseRunId;
    }

    public void setCourseRunId(Integer courseRunId) {
        this.courseRunId = courseRunId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherFeedback that = (TeacherFeedback) o;
        return Objects.equals(courseRunId, that.courseRunId) && Objects.equals(studentId, that.studentId) && Objects.equals(mark, that.mark) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseRunId, studentId, mark, feedback);
    }
}
