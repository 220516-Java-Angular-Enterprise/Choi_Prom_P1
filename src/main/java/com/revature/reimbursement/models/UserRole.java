package com.revature.reimbursement.models;

public class UserRole {
    private String roleId;
    private String role;

    //Constructors

    public UserRole(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public UserRole(){}

    //region gets and sets
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
                "roleId='" + roleId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
