package com.revature.reimbursement.models;

public class ReimbCat {
    private String typeId;
    private String category;

    //region constructor


    public ReimbCat(String typeId, String category) {
        this.typeId = typeId;
        this.category = category;
    }
    public ReimbCat(){}
    //endgregion


    //region gets and sets


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //endregion


    @Override
    public String toString() {
        return "ReimbCat{" +
                "typeId='" + typeId + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
