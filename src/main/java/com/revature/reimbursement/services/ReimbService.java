package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.dtos.requests.ApprovalRequest;
import com.revature.reimbursement.dtos.requests.ReimbRequest;
import com.revature.reimbursement.dtos.requests.UpdatePendingReimbRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReimbService {
    private final ReimbDAO reimbDAO;

    public ReimbService(ReimbDAO reimbDAO) {
        this.reimbDAO = reimbDAO;
    }


    public List<ReimbPrincipal> getReimbsByAuthorID(String authorId){
        List<Reimb> reimbList= reimbDAO.getByAuthorId(authorId);
        List<ReimbPrincipal> returnList = new ArrayList<>();
        for(Reimb reimb: reimbList){
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

    public List<ReimbPrincipal> getNonPendingReimbsByAuthorId(String id){
        List<Reimb> reimbList = reimbDAO.getByAuthorId(id);
        List<ReimbPrincipal> returnList = new ArrayList<>();
        for(Reimb reimb: reimbList){
            if(!(reimb.getStatusId().equals("0"))) {
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
        }
        return returnList;
    }

    public List<ReimbPrincipal> getReimbsByAuthorIdAndStatusId(String authorId, String statusId){
        List<Reimb> reimbList = reimbDAO.getByAuthorIdAndStatusId(authorId, statusId);
        List<ReimbPrincipal> returnList = new ArrayList<>();
        for(Reimb reimb: reimbList){
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

    public List<ReimbPrincipal> getReimbsByStatusId(String statusId){
        List<Reimb> reimbList = reimbDAO.getByStatusId(statusId);
        List<ReimbPrincipal> returnList = new ArrayList<>();
        for(Reimb reimb: reimbList){
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

    public List<Reimb> getAll(){
        return reimbDAO.getAll();
    }

    public void update(Reimb reimbursement){
        reimbDAO.update(reimbursement);
    }

    public void updateReimb(UpdatePendingReimbRequest request){
        Reimb currentReimb = reimbDAO.getById(request.getId());

        System.out.println("Status ID: " + currentReimb.getStatusId());
        if(currentReimb.getStatusId().equals("0")){ //Will go further if status is pending
            System.out.println("Recognized as pending.");
            currentReimb.setTypId(new ReimbCatService(new ReimbTypeDAO()).getIdByCategory(request.getType()));  //Sets type
            System.out.println(currentReimb);
            if(!(currentReimb.getTypId() == null)){  // If type does not exist, throws invalid request
                currentReimb.setAmount(request.getAmount());
                currentReimb.setDescription(request.getDescription());
            } else throw new InvalidRequestException("Type entered does not exist.");
        } else throw new InvalidRequestException("The current order is not pending.");

        reimbDAO.update(currentReimb);
    }

    public Reimb createReimb(ReimbRequest request, String id){
        ReimbCatService catService = new ReimbCatService(new ReimbTypeDAO());
        Reimb reimbursement = new Reimb(); 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy,MM,dd HH:mm");
        LocalDateTime now = LocalDateTime.now();


        reimbursement.setTypId(catService.getIdByCategory(request.getType())); //Extract from request
        reimbursement.setDescription(request.getDescription());
        reimbursement.setAmount(request.getAmount());

        reimbursement.setReimbId(UUID.randomUUID().toString()); // Sets other attributes
        reimbursement.setStatusId("0"); // Pending
        reimbursement.setAuthorId(id);
        reimbursement.setSubmitted(dtf.format(now));

        reimbDAO.save(reimbursement); // Save in database
        return reimbursement;
    }

    public Reimb getById(String id){
        Reimb reimb = reimbDAO.getById(id);
        return reimb;
    }

}
