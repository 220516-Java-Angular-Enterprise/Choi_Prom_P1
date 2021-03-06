package com.revature.reimbursement.dtos.requests;

import java.math.BigDecimal;

public class ReimbRequest {
    //region attributes
    private BigDecimal amount;
    private String type;
    private String description;
    //endregion attributes

    //region constructor
    public ReimbRequest(BigDecimal amount, String type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }
    public ReimbRequest(){}
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
    //endregion


    @Override
    public String toString() {
        return "ReimbRequest{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
