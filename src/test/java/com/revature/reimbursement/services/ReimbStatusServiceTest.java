package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReimbStatusServiceTest {

    @Inject
    ReimbStatusService reimbStatusService = new ReimbStatusService(new ReimbStatDAO());
    @Test
    public void getStatusById() {
        //region <act>

        //endregion

        //region <arrange>

        //endregion

        //region <assert>
        assertEquals("APPROVED", reimbStatusService.getStatusById("1"));
        assertNull(reimbStatusService.getStatusById("6"));
        //endregion
    }

    @Test
    public void getIdByStatus() {
        //region <act>

        //endregion

        //region <arrange>

        //endregion

        //region <assert>
        assertEquals("-1", reimbStatusService.getIdByStatus("DENIED"));
        assertNull(reimbStatusService.getIdByStatus("MAYBE"));
        //endregion
    }
}