package com.revature.reimbursement.dtos.response;

public class Principal {
    String username;
    String role;
    String id;


//Constructors
    public Principal(){}

    public Principal(String username, String role, String id) {
        this.username = username;
        this.role = role;
        this.id = id;
    }
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
