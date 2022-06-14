package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserRoleDAO;
import com.revature.reimbursement.models.UserRole;

public class UserRoleService {
    UserRoleDAO userRoleDAO = new UserRoleDAO();

    public String getRoleById(String id){
        return userRoleDAO.getById(id).getRole();
    }
}

