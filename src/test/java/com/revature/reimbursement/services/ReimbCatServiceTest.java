package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ReimbCatServiceTest {

    @Inject
    ReimbCatService reimbCatService = new ReimbCatService(new ReimbTypeDAO());
    @Test
    public void getCategoryById() {
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
    public void getIdByCategory() {
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