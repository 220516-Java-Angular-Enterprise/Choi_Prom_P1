package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbServiceTest {

    @Spy
    private ReimbDAO reimbDAO;
    @InjectMocks
    private ReimbStatusService reimbStatusService;
    @InjectMocks
    public ReimbCatService reimbCatService;

    @Spy
    private Reimb reimbursement = new Reimb();
    @Spy
    private List<Reimb> reimbursements = new ArrayList<>();

    @Test
    void getById() {

    }

    @Test
    void getAll() {

    }

    @Test
    void getAllPendingByAuthorId() {

    }

    @Test
    void getAllApproved() {

    }

    @Test
    void getAllDenied() {

    }

    @Test
    void getHistoryByAuthor() {

    }
}