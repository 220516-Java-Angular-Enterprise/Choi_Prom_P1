package com.revature.reimbursement.dtos.requests;

import java.math.BigDecimal;

public class UpdatePendingReimbRequest {
    //region attributes
    private BigDecimal amount;
    private String type;
    private String description;
    private String id;
    //endregion attributes

    //region constructor

    public UpdatePendingReimbRequest(BigDecimal amount, String type, String description, String id) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.id = id;
    }

    public UpdatePendingReimbRequest(){}

    //endregion constructor

    //region gets and sets

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //endregion


    @Override
    public String toString() {
        return "UpdatePendingReimbRequest{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
