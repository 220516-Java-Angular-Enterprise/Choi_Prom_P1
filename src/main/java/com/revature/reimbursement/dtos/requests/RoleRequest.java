package com.revature.reimbursement.dtos.requests;

public class RoleRequest {
    //region <attributes>
    private String id;
    private String role_id;
    //endregion

    //region <constructors>
    public RoleRequest(){

    }

    public RoleRequest(String id, String role_id){
        this.id = id;
        this.role_id = role_id;
    }
    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    //endregion

    //region <overrides>

    @Override
    public String toString() {
        return "RoleRequest{" +
                "id='" + id + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }

    //endregion
}
