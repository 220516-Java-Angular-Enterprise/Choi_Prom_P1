package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.dtos.requests.ApprovalRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    @Inject
    private final ReimbService reimbService;
    private final ReimbStatusService reimbStatusService;
    private final ReimbCatService reimbCatService;


    public ManagerService(ReimbService reimbService,
                          ReimbStatusService reimbStatusService, ReimbCatService reimbCatService){
        this.reimbService = reimbService;
        this.reimbStatusService = reimbStatusService;
        this.reimbCatService = reimbCatService;
    }

    public List<ReimbPrincipal> getAllPending(){
        List<ReimbPrincipal> pending = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
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
        Reimb reimbursement = reimbService.getById(request.getId());
        reimbursement.setResolved(request.getResolvedDate());
        reimbursement.setResolverId(request.getResolverId());
        reimbursement.setStatusId(reimbStatusService.getIdByStatus(request.getStatus()));
        reimbService.update(reimbursement);
    }

    public List<ReimbPrincipal> viewApprovalHistory(String resolver_id){
        List<Reimb> reimbursements = reimbService.getAll();
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
                    new ReimbStatusService(new ReimbStatDAO()).getStatusById(reimb.getStatusId()),
                    new ReimbCatService(new ReimbTypeDAO()).getCategoryById(reimb.getTypId())
            );
            returnList.add(reimbPrincipal);
        }
        return returnList;
    }
}
