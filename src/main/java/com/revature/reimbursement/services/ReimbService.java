package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.dtos.requests.ReimbRequest;
import com.revature.reimbursement.dtos.requests.UpdatePendingRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReimbService {
    @Inject
    private final ReimbDAO reimbDAO;
    private final ReimbStatusService reimbStatusService;
    public final ReimbCatService reimbCatService;

    public ReimbService(ReimbDAO reimbDAO, ReimbStatusService reimbStatusService, ReimbCatService reimbCatService) {
        this.reimbDAO = reimbDAO;
        this.reimbStatusService = reimbStatusService;
        this.reimbCatService = reimbCatService;
    }


    public Reimb getById(String id){
        return reimbDAO.getById(id);
    }

    public List<Reimb> getAll() {
        return reimbDAO.getAll();
    }

    public void update(Reimb reimbursement) {
        reimbDAO.update(reimbursement);
    }

    public List<ReimbPrincipal> getAllPending(){
        List<ReimbPrincipal> pending = new ArrayList<>();
        List<Reimb> reimbursements = getAll();
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

    public List<ReimbPrincipal> getAllApproved(){
        List<ReimbPrincipal> approved = new ArrayList<>();
        List<Reimb> reimbursements = getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("1")){
                approved.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getReimbId()),
                        reimbCatService.getCategoryById(reimbursement.getReimbId())));
            }
        }
        return approved;
    }

    public List<ReimbPrincipal> getAllDenied(){
        List<ReimbPrincipal> denied = new ArrayList<>();
        List<Reimb> reimbursements = getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals("-1")){
                denied.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getReimbId()),
                        reimbCatService.getCategoryById(reimbursement.getReimbId())));
            }
        }
        return denied;
    }

    public List<ReimbPrincipal> getHistoryByAuthor(String id){
        List<ReimbPrincipal> history = new ArrayList<>();
        List<Reimb> reimbursements = getAll();
        for(Reimb reimbursement: reimbursements){
            if(reimbursement.getStatusId().equals(id)){
                history.add(new ReimbPrincipal(reimbursement.getReimbId(), reimbursement.getAmount(),
                        reimbursement.getSubmitted(), reimbursement.getDescription(),
                        reimbStatusService.getStatusById(reimbursement.getReimbId()),
                        reimbCatService.getCategoryById(reimbursement.getReimbId())));
            } else throw new InvalidRequestException("User does not exist.");
        }
        return history;
    }

    public void updateReimb(UpdatePendingRequest request){
        Reimb currentReimb = reimbDAO.getById(request.getId());

        if(currentReimb.getStatusId().equals("0")){ //Will go further if status is pending
            currentReimb.setTypId(reimbCatService.getIdByCategory(request.getType()));  //Sets type
            if(!(currentReimb.getTypId() == null)){  // If type does not exist, throws invalid request
                currentReimb.setAmount(request.getAmount());
                currentReimb.setDescription(request.getDescription());
            } else throw new InvalidRequestException("Type entered does not exist.");
        } else throw new InvalidRequestException("The current order is not pending.");

        reimbDAO.update(currentReimb);
    }

    public Reimb createReimb(ReimbRequest request, String id){
        Reimb reimbursement = new Reimb(); 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();

        reimbursement.setTypId(reimbCatService.getIdByCategory(request.getType())); //Extract from request
        if(reimbursement.getTypId() == null){
            throw new InvalidRequestException("Type does not exist.");
        } else {
            reimbursement.setDescription(request.getDescription());
            reimbursement.setAmount(request.getAmount());
            reimbursement.setReimbId(UUID.randomUUID().toString()); // Sets other attributes
            reimbursement.setStatusId("0"); // Pending
            reimbursement.setAuthorId(id);
            reimbursement.setSubmitted(dtf.format(now));
        }

        reimbDAO.save(reimbursement); // Save in database
        return reimbursement;
    }
}
