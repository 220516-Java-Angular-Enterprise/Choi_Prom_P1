package com.revature.reimbursement.dtos.requests;

public class LoginRequest {
    String username;
    String password;


    // Constructors
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequest(){}

//region Gets & Sets
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
//endregion
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
