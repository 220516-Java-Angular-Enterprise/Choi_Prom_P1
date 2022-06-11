package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.models.ReimbStat;

public class ReimbStatusService {

    private final ReimbStatDAO reimbStatDAO;

    public ReimbStatusService(ReimbStatDAO reimbStatDAO) {
        this.reimbStatDAO = reimbStatDAO;
    }

    public String getStatusById(String statusId){
        ReimbStat status = reimbStatDAO.getById(statusId);
        return status.getStatus();
    }

    public String getIdByStatus(String status){
        ReimbStat stat = reimbStatDAO.getByStatus(status);
        return stat.getStatusId();
    }
}
