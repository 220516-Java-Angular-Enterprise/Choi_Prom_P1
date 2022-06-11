package com.revature.reimbursement.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.ApprovalRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    @Inject
    private final ReimbDAO reimbDAO;
    private final ReimbStatusService reimbStatusService;
    private final ReimbCatService reimbCatService;


    public ManagerService(ReimbDAO reimbDAO,
                          ReimbStatusService reimbStatusService, ReimbCatService reimbCatService){
        this.reimbDAO = reimbDAO;
        this.reimbStatusService = reimbStatusService;
        this.reimbCatService = reimbCatService;
    }

    public List<ReimbPrincipal> getAllPending(){
        List<ReimbPrincipal> pending = new ArrayList<>();
        List<Reimb> reimbursements = reimbDAO.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("0")){
                pending.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getReimbId()),
                        reimbCatService.getCategoryById(reimbursement.getReimbId())));
            }
        }
        return pending;
    }

    public void setApproval(ApprovalRequest request){
        if(!request.getStatus().equals("APPROVED") && !request.getStatus().equals("DENIED")){
            throw new InvalidRequestException("Unrecognized status.");
        }
        Reimb reimbursement = reimbDAO.getById(request.getId());
        reimbursement.setResolved(request.getResolvedDate());
        reimbursement.setResolverId(request.getResolverId());
        reimbursement.setStatusId(reimbStatusService.getIdByStatus(request.getStatus()));
        reimbDAO.update(reimbursement);
    }

    public List<ReimbPrincipal> viewApprovalHistory(String resolver_id){
        List<Reimb> reimbursements = reimbDAO.getAll();
        List<Reimb> approvalHistory = new ArrayList<>();
        List<ReimbPrincipal> returnList = new ArrayList<>();
        for(Reimb reimb:reimbursements){
            if(reimb.getResolverId() != null && reimb.getResolverId().equals(resolver_id)){
                approvalHistory.add(reimb);
            }
        }

        for(Reimb reimb:approvalHistory){
            ReimbPrincipal reimbPrincipal = new ReimbPrincipal(
                    reimb.getReimbId(),
                    reimb.getAmount(),
                    reimb.getSubmitted(),
                    reimb.getResolved(),
                    reimb.getDescription(),
                    reimb.getStatusId(),
                    reimb.getTypId()
            );
            returnList.add(reimbPrincipal);
        }
        return returnList;
    }
}
