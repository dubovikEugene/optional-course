package com.epam.optionalcourse.bean.user;

import com.epam.optionalcourse.bean.course.ReadCourseRun;
import com.epam.optionalcourse.bean.feedback.TeacherFeedback;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AuthorizedUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -1587236663745784810L;
    private Integer id;
    private String firstName;
    private String email;
    private String role;

    private List<ReadCourseRun> courses;
    private List<TeacherFeedback> feedbacks;

    public AuthorizedUser() {
    }

    public AuthorizedUser(Integer id, String firstName, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ReadCourseRun> getCourses() {
        return courses;
    }

    public void setCourses(List<ReadCourseRun> courses) {
        this.courses = courses;
    }

    public List<TeacherFeedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<TeacherFeedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizedUser that = (AuthorizedUser) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(courses, that.courses) && Objects.equals(feedbacks, that.feedbacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, role, courses, feedbacks);
    }
}
