package com.revature.reimbursement.dtos.requests;

public class ApprovalRequest {
    //region <attributes>
    private String id;
    private String resolvedDate;
    private String status;
    //endregion

    //region <constructors>
    public ApprovalRequest(){

    }

    public ApprovalRequest(String id, String resolvedDate, String status) {
        this.id = id;
        this.resolvedDate = resolvedDate;
        this.status = status;
    }
//endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //endregion


    @Override
    public String toString() {
        return "ApprovalRequest{" +
                "id='" + id + '\'' +
                ", resolvedDate='" + resolvedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
