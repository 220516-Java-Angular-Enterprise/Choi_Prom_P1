package com.revature.reimbursement.services;

import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserRoleServiceTest {
    @Inject
    UserRoleService userRoleService = new UserRoleService();
    @Test
    void getRoleById() {
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