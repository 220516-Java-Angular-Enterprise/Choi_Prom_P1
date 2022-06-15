package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.models.ReimbStat;

public class ReimbStatusService {

    private final ReimbStatDAO reimbStatDAO;

    public ReimbStatusService(ReimbStatDAO reimbStatDAO) {
        this.reimbStatDAO = reimbStatDAO;
    }

    public String getStatusById(String statusId){
        return reimbStatDAO.getById(statusId).getStatus();
    }

    public String getIdByStatus(String status){
        return reimbStatDAO.getByStatus(status).getStatusId();

    }
}
