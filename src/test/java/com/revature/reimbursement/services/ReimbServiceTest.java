package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class ReimbServiceTest {
    @Inject
    ReimbService reimbService = new ReimbService(new ReimbDAO(), new ReimbStatusService(new ReimbStatDAO()),
            new ReimbCatService(new ReimbTypeDAO()));

    @Test
    void getById() {
        //region <act>
        String id = "";
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertNull(reimbService.getById(id).getReimbId());
        //endregion
    }

    @Test
    void getAll() {
        //region <act>
        String id = "adwdwdw";
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertNull(reimbService.getById(id).getReimbId());
        //endregion
    }

    @Test
    void getAllPendingByAuthorId() {
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
        assertNotNull(reimbService.getAllApproved());
        //endregion
    }

    @Test
    void getAllDenied() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertNotNull(reimbService.getAllDenied());
        //endregion
    }

    @Test
    void getHistoryByAuthor() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        //endregion
    }
}