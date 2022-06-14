package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceTest {
    @Inject
    UserService userService = new UserService(new UserDAO());
    ReimbCatService reimbCatService = new ReimbCatService(new ReimbTypeDAO());
    ReimbStatusService reimbStatusService = new ReimbStatusService(new ReimbStatDAO());
    ReimbService reimbService = new ReimbService(new ReimbDAO(), reimbStatusService, reimbCatService);
    ManagerService managerService = new ManagerService(reimbService, reimbStatusService, reimbCatService);
    User joseph = userService.getUserById("");
    Reimb testReimb = reimbService.getById("");

    @Test
    void getAllPending() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertNotNull(managerService.getAllPending());
        //endregion
    }

    @Test
    void getAllPendingLodging() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }

    @Test
    void getAllPendingTravel() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }

    @Test
    void getAllPendingFood() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }

    @Test
    void getAllPendingOther() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }

    @Test
    void getAllDenied() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }

    @Test
    void getAllApproved() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }

    @Test
    void viewApprovalHistory() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertNotNull(managerService.viewApprovalHistory(joseph.getId()));
        //endregion
    }
}