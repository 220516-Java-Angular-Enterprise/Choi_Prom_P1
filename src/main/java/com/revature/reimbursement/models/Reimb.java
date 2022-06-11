package com.revature.reimbursement.models;



public class Reimb {
    //region attributes
    private String reimbId;
    private int amount;
    private String description;
    private String submitted;
    private String resolved;
    private String paymentId;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typId;
//endregion

    //region constructor
    public Reimb(String reimbId, int amount, String description, String submitted, String resolved, String paymentId, String authorId, String resolverId, String statusId, String typId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
        this.resolved = resolved;
        this.paymentId = paymentId;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typId = typId;
    }

    public Reimb(){}
    //endregion


    //region gets and sets
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypId() {
        return typId;
    }

    public void setTypId(String typId) {
        this.typId = typId;
    }
    //endregion


    @Override
    public String toString() {
        return "Reimb{" +
                "reimbIdentifier='" + reimbId + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", resolverId='" + resolverId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typId='" + typId + '\'' +
                '}';
    }
}
