package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class ReimbCatServiceTest {
    @Inject
    ReimbCatService reimbCatService = new ReimbCatService(new ReimbTypeDAO());

    @Test
    void getCategoryById() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertEquals("TRAVEL", reimbCatService.getCategoryById("2"));
        assertNull(reimbCatService.getCategoryById("34h3"));
        //endregion
    }

    @Test
    void getIdByCategory() {
        //region <act>
        //endregion

        //region <arrange>
        //endregion

        //region <assert>
        assertEquals("2", reimbCatService.getIdByCategory("TRAVEL"));
        assertNull(reimbCatService.getIdByCategory("heh"));
        //endregion
    }
}