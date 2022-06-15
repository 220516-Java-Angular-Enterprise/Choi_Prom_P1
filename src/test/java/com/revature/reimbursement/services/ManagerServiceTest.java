package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

    @Spy
    ReimbDAO reimbDAO;
    @Spy
    ReimbTypeDAO reimbTypeDAO;
    @Spy
    ReimbStatDAO reimbStatDAO;

    @Spy
    private ReimbCatService reimbCatService = new ReimbCatService(reimbTypeDAO);
    @Spy
    private ReimbStatusService reimbStatusService = new ReimbStatusService(reimbStatDAO);
    @Spy
    private ReimbService reimbService = new ReimbService(reimbDAO, reimbStatusService, reimbCatService);
    @InjectMocks
    private ManagerService managerService;


    @Spy
    private Reimb pendLodgeReimb = new Reimb();
    @Spy
    private Reimb pendTravelReimb = new Reimb();
    @Spy
    private Reimb pendFoodReimb = new Reimb();
    @Spy
    private Reimb pendOtherReimb = new Reimb();
    @Spy
    private Reimb approvedReimb = new Reimb();
    @Spy
    private Reimb deniedReimb = new Reimb();
    @Spy
    private ReimbPrincipal pendingLodging = new ReimbPrincipal();
    @Spy
    private ReimbPrincipal pendingTravel = new ReimbPrincipal();
    @Spy
    private ReimbPrincipal pendingFood = new ReimbPrincipal();
    @Spy
    private ReimbPrincipal pendingOther = new ReimbPrincipal();
    @Spy
    private ReimbPrincipal approved = new ReimbPrincipal();
    @Spy
    private ReimbPrincipal denied = new ReimbPrincipal();
    @Spy
    private List<Reimb> reimbursements = new ArrayList<>();

    @Test
    void getAllPending() {
        List<ReimbPrincipal> pendList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");


        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        pendList.add(pendingLodging);
        pendList.add(pendingTravel);
        pendList.add(pendingFood);
        pendList.add(pendingOther);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("OTHER").when(reimbCatService).getCategoryById("0");
        doReturn("LODGING").when(reimbCatService).getCategoryById("1");
        doReturn("TRAVEL").when(reimbCatService).getCategoryById("2");
        doReturn("FOOD").when(reimbCatService).getCategoryById("3");

        assertEquals(pendList.toString(), managerService.getAllPending().toString());
    }

    @Test
    void getAllPendingLodging() {
        List<ReimbPrincipal> pendList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");


        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        pendList.add(pendingLodging);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("LODGING").when(reimbCatService).getCategoryById("1");

        assertEquals(pendList.toString(), managerService.getAllPendingLodging().toString());

    }

    @Test
    void getAllPendingTravel() {
        List<ReimbPrincipal> pendList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");



        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        pendList.add(pendingTravel);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("TRAVEL").when(reimbCatService).getCategoryById("2");

        assertEquals(pendList.toString(), managerService.getAllPendingTravel().toString());
    }

    @Test
    void getAllPendingFood() {
        List<ReimbPrincipal> pendList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");



        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        pendList.add(pendingFood);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("FOOD").when(reimbCatService).getCategoryById("3");

        assertEquals(pendList.toString(), managerService.getAllPendingFood().toString());
    }

    @Test
    void getAllPendingOther() {
        List<ReimbPrincipal> pendList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");



        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        pendList.add(pendingOther);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("OTHER").when(reimbCatService).getCategoryById("0");

        assertEquals(pendList.toString(), managerService.getAllPendingOther().toString());
    }

    @Test
    void getAllDenied() {
        List<ReimbPrincipal> deniedList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");
        deniedReimb.setResolverId("Bob");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");

        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        deniedList.add(denied);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("DENIED").when(reimbStatusService).getStatusById("-1");
        doReturn("FOOD").when(reimbCatService).getCategoryById("3");

        assertEquals(deniedList.toString(), managerService.getAllDenied("Bob").toString());
    }

    @Test
    void getAllApproved() {
        List<ReimbPrincipal> deniedList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");
        approvedReimb.setResolverId("Bob");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");

        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        deniedList.add(approved);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("APPROVED").when(reimbStatusService).getStatusById("1");
        doReturn("TRAVEL").when(reimbCatService).getCategoryById("2");

        assertEquals(deniedList.toString(), managerService.getAllApproved("Bob").toString());
    }

    @Test
    void viewApprovalHistory() {
        List<ReimbPrincipal> deniedList = new ArrayList<>();
        pendLodgeReimb.setReimbId("01");
        pendTravelReimb.setReimbId("02");
        pendFoodReimb.setReimbId("03");
        pendOtherReimb.setReimbId("04");
        approvedReimb.setReimbId("05");
        deniedReimb.setReimbId("06");
        pendLodgeReimb.setStatusId("0");
        pendTravelReimb.setStatusId("0");
        pendFoodReimb.setStatusId("0");
        pendOtherReimb.setStatusId("0");
        approvedReimb.setStatusId("1");
        deniedReimb.setStatusId("-1");
        pendLodgeReimb.setTypId("1");
        pendTravelReimb.setTypId("2");
        pendFoodReimb.setTypId("3");
        pendOtherReimb.setTypId("0");
        approvedReimb.setTypId("2");
        deniedReimb.setTypId("3");
        deniedReimb.setResolverId("Bob");
        approvedReimb.setResolverId("Bob");


        pendingLodging.setReimbId("01");
        pendingTravel.setReimbId("02");
        pendingFood.setReimbId("03");
        pendingOther.setReimbId("04");
        approved.setReimbId("05");
        denied.setReimbId("06");
        pendingLodging.setStatusId("PENDING");
        pendingTravel.setStatusId("PENDING");
        pendingFood.setStatusId("PENDING");
        pendingOther.setStatusId("PENDING");
        approved.setStatusId("APPROVED");
        denied.setStatusId("DENIED");
        pendingLodging.setTypeId("LODGING");
        pendingTravel.setTypeId("TRAVEL");
        pendingFood.setTypeId("FOOD");
        pendingOther.setTypeId("OTHER");
        approved.setTypeId("TRAVEL");
        denied.setTypeId("FOOD");

        reimbursements.add(pendLodgeReimb);
        reimbursements.add(pendTravelReimb);
        reimbursements.add(pendFoodReimb);
        reimbursements.add(pendOtherReimb);
        reimbursements.add(approvedReimb);
        reimbursements.add(deniedReimb);

        deniedList.add(approved);
        deniedList.add(denied);

        doReturn(reimbursements).when(reimbService).getAll();
        doReturn("DENIED").when(reimbStatusService).getStatusById("-1");
        doReturn("APPROVED").when(reimbStatusService).getStatusById("1");
        doReturn("TRAVEL").when(reimbCatService).getCategoryById("2");
        doReturn("FOOD").when(reimbCatService).getCategoryById("3");

        assertEquals(deniedList.toString(), managerService.viewApprovalHistory("Bob").toString());
    }
}