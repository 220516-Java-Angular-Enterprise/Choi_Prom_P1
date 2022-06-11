package com.revature.reimbursement.models;

public class ReimbStat {
    //region attributes
    private String statusId;
    private String status;
    //endregion

    //region constructor
    public ReimbStat(String statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public ReimbStat(){}
//endregion constructor

    //region gets and sets
    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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
        return "ReimbStat{" +
                "statusId='" + statusId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
