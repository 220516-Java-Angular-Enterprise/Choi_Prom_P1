package com.revature.reimbursement.services;

import com.revature.reimbursement.dtos.requests.ActivateRequest;
import com.revature.reimbursement.dtos.requests.PasswordRequest;
import com.revature.reimbursement.dtos.requests.RoleRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

public class AdminService {
    @Inject
    private final UserService userService;

    @Inject
    public AdminService(UserService userService){
        this.userService = userService;
    }

    //activates or deactivates a user account
    public void setUserActivity(ActivateRequest request){
        User user = userService.getUserById(request.getId());
        if(user == null){
            throw new InvalidRequestException("The request is not valid");
        }
        if(request.isActive()){
            user.setActive(true);
        }
        else{
            user.setActive(false);
        }

        userService.update(user);
    }

    //changes a user's role
    public void setUserRole(RoleRequest request){
        User user = userService.getUserById(request.getId());
        if(user == null || Integer.valueOf(request.getRole_id()) < 0 || Integer.valueOf(request.getRole_id()) > 2){
            throw new InvalidRequestException("The request is not valid");
        }
        user.setRole_id(request.getRole_id());
        userService.update(user);
    }

    //changes a user's password
    public void changeUserPassword(PasswordRequest request){
        if(!userService.isValidPassword(request.getPassword())){
            throw new InvalidRequestException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
        }
        User user = userService.getUserById(request.getId());
        user.setPassword(request.getPassword());
        userService.update(user);
    }
}
