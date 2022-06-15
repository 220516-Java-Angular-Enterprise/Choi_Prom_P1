package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.models.ReimbCat;

public class ReimbCatService {

    private final ReimbTypeDAO reimbTypeDAO;

    public ReimbCatService(ReimbTypeDAO reimbTypeDAO) {
        this.reimbTypeDAO = reimbTypeDAO;
    }


    public String getCategoryById(String typeId){
        return reimbTypeDAO.getById(typeId).getCategory();
    }

    public String getIdByCategory(String category){
        return reimbTypeDAO.getByCategory(category).getTypeId();
    }


}
