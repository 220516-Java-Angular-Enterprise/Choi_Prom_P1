package com.revature.reimbursement.dtos.requests;

public class ActivateRequest {
    //region <attributes>
    private String id;
    private boolean active;
    //endregion

    //region <constructors>
    public ActivateRequest(){

    }

    public ActivateRequest(String id, boolean active){
        this.id = id;
        this.active = active;
    }
    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
//endregion

    //region <overrides>

    @Override
    public String toString() {
        return "ActivateRequest{" +
                "username='" + id + '\'' +
                '}';
    }

    //endregion
}
