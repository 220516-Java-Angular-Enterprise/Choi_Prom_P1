package com.revature.reimbursement.dtos.response;

import com.revature.reimbursement.models.User;

public class Principal {
    String id;
    String username;
    String role;


    //region <constructors>
    public Principal(){}

    public Principal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole_id();
    }

    public Principal(String id, String username, String role) {
        this.username = username;
        this.role = role;
        this.id = id;
    }

    //endregion <constructors>

    //region gets and sets
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //endregion

    @Override
    public String toString() {
        return "Principal{" +
                "userName='" + username + '\'' +
                ", role='" + role + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
