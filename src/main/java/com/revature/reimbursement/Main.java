package com.revature.reimbursement;

import com.revature.reimbursement.util.database.DatabaseConnection;

public class Main {
    public static void main(String args[]){
        System.out.println(DatabaseConnection.getCon());
    }
}
