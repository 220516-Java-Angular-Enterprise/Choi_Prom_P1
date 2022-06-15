package com.revature.reimbursement.models;

public class UserRole {
    private String id;
    private String role;

    //Constructors

    public UserRole(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserRole(){}

    //region gets and sets
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //endregion

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
