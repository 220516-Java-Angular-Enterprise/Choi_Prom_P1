package com.revature.reimbursement.models;

public class User {
    //region <attributes>
    private String id;
    private String username;
    private String password;
    private String role_id;

    private String email;
    private String givenName;
    private String surname;
    private boolean isActive;


    //endregion

//region constructor
    public User(String id, String username, String password, String role_id, String email, String givenName, String surname, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
    }

    public User(String username, String email, String givenName, String surname) {
        this.username = username;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
    }

    public User(){}

    //region gets and sets
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    //endregion


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_id='" + role_id + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}