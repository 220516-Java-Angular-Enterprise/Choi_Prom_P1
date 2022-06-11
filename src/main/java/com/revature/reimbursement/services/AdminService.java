package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.ActivateRequest;
import com.revature.reimbursement.dtos.requests.PasswordRequest;
import com.revature.reimbursement.dtos.requests.RoleRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.InvalidUserException;

public class AdminService {
    @Inject
    private final UserDAO userDAO;
    private final UserService userService;

    @Inject
    public AdminService(UserDAO userDAO, UserService userService){
        this.userDAO = userDAO;
        this.userService = userService;
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

    //changes a user's role
    public void setUserRole(RoleRequest request){
        User user = userDAO.getById(request.getId());
        if(user == null || Integer.valueOf(request.getRole_id()) < 0 || Integer.valueOf(request.getRole_id()) > 2){
            throw new InvalidRequestException("The request is not valid");
        }
        user.setRole_id(request.getRole_id());
        userDAO.update(user);
    }

    //changes a user's password
    public void changeUserPassword(PasswordRequest request){
        if(!userService.isValidPassword(request.getPassword())){
            throw new InvalidRequestException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
        }
        User user = userDAO.getById(request.getId());
        user.setPassword(request.getPassword());
        userDAO.update(user);
    }
}
