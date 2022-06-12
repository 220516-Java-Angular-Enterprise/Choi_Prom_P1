package com.revature.reimbursement.services;

import com.revature.reimbursement.models.UserRole;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRoleServiceTest {
    @Inject
    UserRoleService userRoleService = new UserRoleService();
    @Test
    public void getRoleById() {
        //region <act>

        //endregion

        //region <arrange>

        //endregion

        //region <assert>
        assertEquals("FIN_MAN", userRoleService.getRoleById("1"));
        assertNull(userRoleService.getRoleById("5"));
        //endregion
    }
}