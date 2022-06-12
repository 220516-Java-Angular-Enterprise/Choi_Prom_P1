package com.revature.reimbursement.dtos.requests;

import com.revature.reimbursement.models.User;

public class NewUserRequest {
    //region <attributes>
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;

    //endregion

    //region <constructors>
    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

//endregion

    //region <Accessors and Mutators>
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //endregion



    //region <methods>
    public User extractUser() {
        return new User(username, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

//endregion
}