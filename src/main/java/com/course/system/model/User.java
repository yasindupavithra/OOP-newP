package com.course.system.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * IMPROVEMENT (OOP):
 * - Now implements Describable interface (interface-based polymorphism)
 * - Added equals() and hashCode() based on ID
 * - UserType stored as String for file compatibility, validated via enum
 */
public abstract class User implements Serializable, Describable {
    private String id;
    private String username;
    private String password;
    private String email;
    private String userType;

    public User() {}

    public User(String id, String username, String password, String email, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    // IMPROVEMENT: Declared in Describable interface, implemented by subclasses
    @Override
    public abstract String getRoleDescription();

    // IMPROVEMENT: equals and hashCode based on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + "|" + username + "|" + password + "|" + email + "|" + userType;
    }
}
