package com.revature.reimbursement;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.LoginRequest;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.services.ReimbService;
import com.revature.reimbursement.services.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]){

//        System.out.println(ConnectionFactory.getCon());
        ReimbService reimbService = new ReimbService(new ReimbDAO());
        List<ReimbPrincipal> reimbList = reimbService.getReimbsByAuthorIdAndStatusId("e1264796-edbf-403b-8821-2b5468a8f72a", "0");
        System.out.println(reimbList);
        List<ReimbPrincipal> newList = (List<ReimbPrincipal>) reimbList.stream().sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed()).collect(Collectors.toList());
        System.out.println(newList);
    }

}
