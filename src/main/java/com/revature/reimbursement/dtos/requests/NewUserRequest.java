package com.revature.reimbursement.dtos.requests;

import com.revature.reimbursement.models.User;

public class NewUserRequest {
    //region <attributes>
    private String userName;
    private String userPassword;

    private final String role_id = "DEFAULT";
    //endregion

    //region <constructors>
    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
    //endregion

    //region <Accessors and Mutators>
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole_id() {
        return role_id;
    }
    //endregion


    //region <methods>
    public User extractUser() {
        return new User(userName, userPassword, role_id);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + userName + '\'' +
                ", password='" + userPassword + '\'' +
                ", role='" + role_id + '\'' +
                '}';
    }
    //endregion
}