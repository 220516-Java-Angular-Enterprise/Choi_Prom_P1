package com.revature.reimbursement.dtos.requests;

public class DeleteRequest {
    //region <attributes>
    private String id;
    //endregion

    //region <constructors>
    public DeleteRequest(){

    }

    public DeleteRequest(String id) {
        this.id = id;
    }

    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //endregion
}
