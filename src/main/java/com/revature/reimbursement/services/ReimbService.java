package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.dtos.requests.ReimbRequest;
import com.revature.reimbursement.dtos.requests.UpdatePendingReimbRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;

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
                    reimb.getStatusId(),
                    reimb.getTypId()
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

    public void updateReimb(UpdatePendingReimbRequest request){
        Reimb currentReimb = reimbDAO.getById(request.getId());

        currentReimb.setAmount(request.getAmount());
        currentReimb.setDescription(request.getDescription());
        currentReimb.setTypId(request.getType());

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
