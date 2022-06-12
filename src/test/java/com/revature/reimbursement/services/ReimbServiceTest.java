package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.dtos.requests.ReimbRequest;
import com.revature.reimbursement.dtos.requests.UpdatePendingRequest;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ReimbServiceTest {
    ReimbService reimbService = new ReimbService(new ReimbDAO(), new ReimbStatusService(new ReimbStatDAO()), new ReimbCatService(new ReimbTypeDAO()));

    @Test
    public void getById_AssertNullIfEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String id = "";

        /* Arrange */

        /* Assert */
        assertNull(reimbService.getById(id).getReimbId());
    }

    @Test
    public void getAll_FieldsAreAssertNullIfIncorrect() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String id = "adwdwdw";

        /* Arrange */

        /* Assert */
        assertNull(reimbService.getById(id).getReimbId());
    }

    @Test
    public void update_WillEquals0IfUpdateTo0() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        Reimb reimb = reimbService.getById("8ab6e689-75ec-4850-9719-aec53e1d3cd5");
        reimb.setTypId("1"); // 0
        reimbService.update(reimb);
        /* Arrange */

        /* Assert */
        assertEquals("1", reimb.getTypId());
    }

    @Test
    public void getAllPending_WillNotBeNull() {

        /* Assert */
        assertNotNull(reimbService.getAllPending());
    }

    @Test
    public void getAllApproved_WillNotBeNull() {
        /* Assert */
        assertNotNull(reimbService.getAllApproved());
    }

    @Test
    public void getAllDenied() {
        /* Assert */
        assertNotNull(reimbService.getAllDenied());
    }

    @Test
    public void getHistoryByAuthor_WillThrowExceptionIfUserIsNotAuthor() {
        /* Act */
        String id = "null";
        /* Arrange */

        /* Assert */
        assertThrows(InvalidRequestException.class, () -> reimbService.getHistoryByAuthor(id));
    }

    @Test
    public void getHistoryByAuthor_WillThrowExceptionIfIdIsEmpty() {
        /* Act */
        String id = "";
        /* Arrange */

        /* Assert */
        assertThrows(InvalidRequestException.class, () -> reimbService.getHistoryByAuthor(id));
    }

    @Test
    public void updateReimb_WillThrowExceptionIfTypeIsInvalid() {
        /* Act */
        Reimb reimb = reimbService.getById("8ab6e689-75ec-4850-9719-aec53e1d3cd5");
        UpdatePendingRequest updatePendingRequest = new UpdatePendingRequest(reimb.getAmount(), "OTHER", reimb.getDescription(), reimb.getReimbId());
        /* Arrange */
        updatePendingRequest.setType("YUMMY");

        /* Assert */
        assertThrows(InvalidRequestException.class, () -> reimbService.updateReimb(updatePendingRequest));
    }

    @Test
    public void updateReimb_WillThrowExceptionIfTypeEmpty() {
        /* Act */
        Reimb reimb = reimbService.getById("8ab6e689-75ec-4850-9719-aec53e1d3cd5");
        UpdatePendingRequest updatePendingRequest = new UpdatePendingRequest(reimb.getAmount(), "OTHER", reimb.getDescription(), reimb.getReimbId());
        /* Arrange */
        updatePendingRequest.setType("");

        /* Assert */
        assertThrows(InvalidRequestException.class, () -> reimbService.updateReimb(updatePendingRequest));
    }

    @Test
    public void updateReimb_WillThrowExceptionIfStatusIsNotPending() {
        /* Act */
        Reimb reimb = reimbService.getById("46069948-aacc-4669-909c-b7ce32d6392f"); //APPROVED REQUEST
        UpdatePendingRequest updatePendingRequest = new UpdatePendingRequest(reimb.getAmount(), "FOOD", reimb.getDescription(), reimb.getReimbId());
        /* Arrange */


        /* Assert */
        assertThrows(InvalidRequestException.class, () -> reimbService.updateReimb(updatePendingRequest));
    }

    @Test
    public void createReimb_WillThrowExceptionIfRequestTypeIsInvalid() {
        /* Act */
        BigDecimal two = BigDecimal.valueOf(200);
        ReimbRequest request = new ReimbRequest(two, "YUMMY", "I went to the food court for lunch" );
        /* Arrange */


        /* Assert */
        assertThrows(InvalidRequestException.class, () -> reimbService.createReimb(request, "e1264796-edbf-403b-8821-2b5468a8f72a"));
    }
}