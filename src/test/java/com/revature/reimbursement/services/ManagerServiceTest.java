package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.ApprovalRequest;
import com.revature.reimbursement.dtos.requests.PasswordRequest;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerServiceTest {

    @Inject
    UserService userService = new UserService(new UserDAO());
    ReimbCatService reimbCatService = new ReimbCatService(new ReimbTypeDAO());
    ReimbStatusService reimbStatusService = new ReimbStatusService(new ReimbStatDAO());
    ReimbService reimbService = new ReimbService(new ReimbDAO(), reimbStatusService, reimbCatService);
    ManagerService managerService = new ManagerService(reimbService, reimbStatusService, reimbCatService);
    User joseph = userService.getUserById("3bb07305-ca5c-4b33-9249-4d8c8e50cf45");
    Reimb testReimb = reimbService.getById("46069948-aacc-4669-909c-b7ce32d6392f");
    @Test
    public void getAllPending() {
        //region <act>

        //endregion

        //region <arrange>

        //endregion

        //region <assert>
        assertNotNull(managerService.getAllPending());
        //endregion
    }

    @Test
    public void setApproval() {
        //region <act>
        ApprovalRequest request1 = new ApprovalRequest(testReimb.getReimbId(), "DENIED");
        ApprovalRequest request2 = new ApprovalRequest(testReimb.getReimbId(), "waffles");
        ApprovalRequest request3 = null;
        //endregion

        //region <arrange>
        managerService.setApproval(request1, joseph.getId());
        Reimb test1 = reimbService.getById(testReimb.getReimbId());
        //endregion

        //region <assert>
        assertEquals("DENIED", reimbStatusService.getStatusById(test1.getStatusId()));
        Assert.assertThrows(InvalidRequestException.class, () -> managerService.setApproval(request2, joseph.getId()));
        Assert.assertThrows(InvalidRequestException.class, () -> managerService.setApproval(request3, joseph.getId()));
        //endregion
    }

    @Test
    public void viewApprovalHistory() {
        //region <act>

        //endregion

        //region <arrange>

        //endregion

        //region <assert>
        assertNotNull(managerService.viewApprovalHistory(joseph.getId()));
        //endregion
    }
}