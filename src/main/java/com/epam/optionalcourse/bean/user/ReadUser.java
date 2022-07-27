package com.epam.optionalcourse.bean.user;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ReadUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -2470287287908949261L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    private Boolean isGetFeedback;

    public ReadUser() {
    }

    public ReadUser(Integer id, String firstName, String lastName, String email, Boolean isGetFeedback) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isGetFeedback = isGetFeedback;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsGetFeedback() {
        return isGetFeedback;
    }

    public void setIsGetFeedback(Boolean getFeedback) {
        isGetFeedback = getFeedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadUser readUser = (ReadUser) o;
        return isGetFeedback == readUser.isGetFeedback
                && Objects.equals(id, readUser.id)
                && Objects.equals(firstName, readUser.firstName)
                && Objects.equals(lastName, readUser.lastName)
                && Objects.equals(email, readUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, isGetFeedback);
    }
}
