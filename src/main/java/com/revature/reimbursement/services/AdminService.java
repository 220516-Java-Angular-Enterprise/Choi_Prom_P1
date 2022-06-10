package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.ActivateRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.InvalidUserException;

public class AdminService {
    @Inject
    private final UserDAO userDAO;

    @Inject
    public AdminService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    //activates or deactivates a user account
    public void setUserActivity(ActivateRequest request){
        User user = userDAO.getById(request.getId());
        if(user == null){
            throw new InvalidRequestException("The request is not valid");
        }
        if(request.isActive()){
            user.setActive(true);
        }
        else{
            user.setActive(false);
        }

        userDAO.update(user);
    }
}
