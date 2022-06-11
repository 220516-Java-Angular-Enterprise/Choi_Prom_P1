package com.revature.reimbursement.dtos.response;

public class ReimbPrincipal {
    private String reimbId;
    private int amount;
    private String submitted;
    private String resolved;
    private String description;
    private String statusId;
    private String typeId;

    //region <constructors>
    public ReimbPrincipal(){}

    public ReimbPrincipal(String reimbId, int amount, String submitted, String resolved, String description, String statusId, String typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public ReimbPrincipal(String reimbId, int amount, String submitted, String description, String statusId, String typeId){
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.statusId = statusId;
        this.typeId = typeId;
    }
    //endregion

    //region <accessors and mutators>

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    //endregion


    //region overrides
    @Override
    public String toString() {
        return "ReimbPrincipal{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", description='" + description + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
    //endregion
}
