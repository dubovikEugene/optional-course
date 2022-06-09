package com.epam.optionalcourse.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AuthorizedUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -1587236663745784810L;
    private Integer id;
    private String firstName;
    private String email;
    private String role;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizedUser that = (AuthorizedUser) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(email, that.email)
                && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, role);
    }
}
