package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceTest {

    @Spy
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Spy
    private List<ReimbPrincipal> principals = new ArrayList<>();

    @Test
    void getAllPending() {

    }

    @Test
    void getAllPendingLodging() {

    }

    @Test
    void getAllPendingTravel() {

    }

    @Test
    void getAllPendingFood() {

    }

    @Test
    void getAllPendingOther() {

    }

    @Test
    void getAllDenied() {

    }

    @Test
    void getAllApproved() {

    }

    @Test
    void viewApprovalHistory() {

    }
}