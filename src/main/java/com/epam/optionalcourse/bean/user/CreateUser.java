package com.epam.optionalcourse.bean.user;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CreateUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -1219391427623385057L;

    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String password;
    private String gender;

    public CreateUser() {
    }

    public CreateUser(String firstName,
                      String lastName,
                      String birthday,
                      String email,
                      String password,
                      String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.gender = gender;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUser that = (CreateUser) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(birthday, that.birthday)
                && Objects.equals(email, that.email)
                && Objects.equals(password, that.password)
                && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, email, password, gender);
    }
}
