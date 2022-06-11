package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.models.ReimbCat;

public class ReimbCatService {

    private final ReimbTypeDAO reimbTypeDAO;

    public ReimbCatService(ReimbTypeDAO reimbTypeDAO) {
        this.reimbTypeDAO = reimbTypeDAO;
    }


    public String getCategoryById(String typeId){
        ReimbCat category = reimbTypeDAO.getById(typeId);
        return category.getCategory();
    }

    public String getIdByCategory(String category){
        ReimbCat cate = reimbTypeDAO.getByCategory(category);
        return cate.getTypeId();
    }


}
