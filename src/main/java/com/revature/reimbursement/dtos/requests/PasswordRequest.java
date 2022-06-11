package com.revature.reimbursement.dtos.requests;

public class PasswordRequest {
    //region <attributes>
    private String id;
    private String password;
    //endregion

    //region <constructors>
    public PasswordRequest(){

    }

    public PasswordRequest(String id, String password){
        this.id = id;
        this.password = password;
    }
    //endregion

    //region <accessors and mutators>
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    //endregion

}
