package com.revature.reimbursement;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.services.UserService;
import com.revature.reimbursement.util.database.DatabaseConnection;

public class Main {
    public static void main(String args[]){
        new UserService(new UserDAO()).register(new NewUserRequest("joseph375", "P@ssw0rd"));


    }

}
