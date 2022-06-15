package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ReimbStatusServiceTest {
    @Inject
    ReimbStatusService reimbStatusService = new ReimbStatusService(new ReimbStatDAO());

    @Test
    void getStatusById() {
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
    void getIdByStatus() {
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