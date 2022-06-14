package com.revature.reimbursement.services;

import com.revature.reimbursement.dtos.requests.ApprovalRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    @Inject
    private final ReimbService reimbService;
    private final ReimbStatusService reimbStatusService;
    private final ReimbCatService reimbCatService;


    public ManagerService(ReimbService reimbService, ReimbStatusService reimbStatusService, ReimbCatService reimbCatService){
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
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return pending;
    }

    public List<ReimbPrincipal> getAllPendingLodging(){
        List<ReimbPrincipal> pendingLodging = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("0") && reimbursement.getTypId().equals("1")){
                pendingLodging.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return pendingLodging;
    }

    public List<ReimbPrincipal> getAllPendingTravel(){
        List<ReimbPrincipal> pendingTravel = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("0") && reimbursement.getTypId().equals("2")){
                pendingTravel.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return pendingTravel;
    }

    public List<ReimbPrincipal> getAllPendingFood(){
        List<ReimbPrincipal> pendingFood = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("0") && reimbursement.getTypId().equals("3")){
                pendingFood.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return pendingFood;
    }

    public List<ReimbPrincipal> getAllPendingOther(){
        List<ReimbPrincipal> pendingOther = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("0") && reimbursement.getTypId().equals("0")){
                pendingOther.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return pendingOther;
    }


    public List<ReimbPrincipal> getAllDenied(String resolver_id){
        List<ReimbPrincipal> denied = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("-1") && reimbursement.getResolverId().equals(resolver_id)){
                denied.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return denied;
    }

    public List<ReimbPrincipal> getAllApproved(String resolver_id){
        List<ReimbPrincipal> approved = new ArrayList<>();
        List<Reimb> reimbursements = reimbService.getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("1") && reimbursement.getResolverId().equals(resolver_id)){
                approved.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getStatusId()),
                        reimbCatService.getCategoryById(reimbursement.getTypId())));
            }
        }
        return approved;
    }

    public void setApproval(ApprovalRequest request, String resolver_id){
        if(!request.getStatus().equals("APPROVED") && !request.getStatus().equals("DENIED")){
            throw new InvalidRequestException("Unrecognized status.");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();

        Reimb reimbursement = reimbService.getById(request.getId());
        reimbursement.setResolved(dtf.format(now));
        reimbursement.setResolverId(resolver_id);
        reimbursement.setStatusId(reimbStatusService.getIdByStatus(request.getStatus()));
        reimbService.update(reimbursement);
    }

    public List<ReimbPrincipal> viewApprovalHistory(String resolver_id){
        List<Reimb> reimbursements = reimbService.getAll();
        List<Reimb> approvalHistory = new ArrayList<>();
        List<ReimbPrincipal> returnList = new ArrayList<>();
        for(Reimb reimb:reimbursements){
            if(reimb.getResolverId() != null && reimb.getResolverId().equals(resolver_id)){ //refine if condition
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
                    reimbStatusService.getStatusById(reimb.getStatusId()),
                    reimbCatService.getCategoryById(reimb.getTypId())
            );
            returnList.add(reimbPrincipal);
        }
        return returnList;
    }
}
