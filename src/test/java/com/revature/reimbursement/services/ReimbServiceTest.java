package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ReimbServiceTest {

    @Spy
    private ReimbDAO mockDAO;
    @InjectMocks
    private ReimbStatusService reimbStatusService;
    @InjectMocks
    public ReimbCatService reimbCatService;
    @InjectMocks
    private ReimbService reimbService;

    @Spy
    private Reimb mockReimb1 = new Reimb();
    @Spy
    private Reimb mockReimb2 = new Reimb();
    @Spy
    private Reimb mockReimb3 = new Reimb();
    @Spy
    private List<Reimb> mockReimbs = new ArrayList<>();

    @Test
    void getById() {
        //region <will fetch correct Reimb>
        mockReimb1.setReimbId("01");
        mockReimb2.setReimbId("02");
        mockReimb3.setReimbId("03");
        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        doReturn(mockReimb1).when(mockDAO).getById(("01"));
        assertEquals(mockReimb1, reimbService.getById("01"));
        //endregion
        //region <Will return null if reimbId does not exist>
        assertNull(reimbService.getById("04"));
        //endregion


    }

    @Test
    void getAll() {
        //region <Retrieved List will match>
        doReturn(mockReimbs).when(mockDAO).getAll();
        assertEquals(mockReimbs, reimbService.getAll());
        //endregion
    }

    @Test
    void getAllPendingByAuthorId() {
        //region <Will retrieve size 2 for 2 pending orders
        mockReimb2.setStatusId("0"); //Pending
        mockReimb1.setStatusId("1"); //Approved
        mockReimb3.setStatusId("0"); //Pending
        mockReimb2.setAuthorId("1"); //Same authors
        mockReimb1.setAuthorId("1");
        mockReimb3.setAuthorId("1");
        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        doReturn(mockReimbs).when(reimbService).getAll();
        assertEquals(2, reimbService.getAllPendingByAuthorId("1").size());
        //endregion
        //region <Will be empty list if author id does not exist>
        assertEquals( new ArrayList<>(), reimbService.getHistoryByAuthor("2"));
        //endregion

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

