package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserRoleDAO;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.models.UserRole;

public class UserRoleService {
    UserRoleDAO userRoleDAO = new UserRoleDAO();

    public void registerUserRole(UserRole userRole){
        userRoleDAO.save(userRole);
    }

    public String getRolebyId(String id){
        UserRole userRole = userRoleDAO.getById(id);
        return userRole.getRole();
    }
}

