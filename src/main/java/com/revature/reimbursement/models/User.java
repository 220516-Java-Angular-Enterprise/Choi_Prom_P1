package com.revature.reimbursement.models;

public class User {
    //region <attributes>
    private String id;
    private String userName;
    private String userPassword;
    private String role_id;
    //region <TO BE IMPLEMENTED>
    //private String email;
    //private String givenName;
    //private String surname;
    //private boolean isActive;
    //endregion

    //endregion


    //region <constructors>
    public User() {
        super();
    }

    public User(String userName, String password, String role_id) {
        this.userName = userName;
        this.userPassword = password;
        this.role_id = role_id;
    }

    public User(String id, String userName, String password, String role_id) {
        this.id = id;
        this.userName = userName;
        this.userPassword = password;
        this.role_id = role_id;
    }
    //endregion

    //region <Accessors and Mutators>
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
    //endregion

    //region <methods>
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + userName + '\'' +
                ", role='" + role_id + '\'' +
                '}';
    }
    //endregion
}