package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.ActivateRequest;
import com.revature.reimbursement.dtos.requests.PasswordRequest;
import com.revature.reimbursement.dtos.requests.RoleRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminServiceTest {

    @Inject
    UserService userService = new UserService(new UserDAO());
    AdminService adminService = new AdminService(userService);
    User joseph = userService.getUserById("3bb07305-ca5c-4b33-9249-4d8c8e50cf45");

    @Test
    public void setUserActivity() { // Sets a user as "active" or "inactive"
        //region <act>
        ActivateRequest request1 = new ActivateRequest(joseph.getId(), false);
        ActivateRequest request2 = new ActivateRequest("fewfgwg",true);
        ActivateRequest request3 = null;

        //endregion

        //region <arrange>
        adminService.setUserActivity(request1);
        User tester1 = userService.getUserById(joseph.getId());

        //endregion

        //region <assert>
        assertFalse(tester1.isActive()); //tests normal use case
        //should throw exception for invalid id
        Assert.assertThrows(InvalidRequestException.class, () -> adminService.setUserActivity(request2));
        //should throw exception for null request
        Assert.assertThrows(InvalidRequestException.class, () -> adminService.setUserActivity(request3));
        //endregion
    }

    @Test
    public void setUserRole() {
        //region <act>
        RoleRequest request1 = new RoleRequest("3bb07305-ca5c-4b33-9249-4d8c8e50cf45", "2");
        RoleRequest request2 = new RoleRequest("3bb07305-ca5c-4b33-9249-4d8c8e50cf45", "4");
        RoleRequest request3 = null;
        //endregion

        //region <arrange>
        adminService.setUserRole(request1);
        User tester1 = userService.getUserById(joseph.getId());
        //endregion

        //region <assert>
        assertEquals("2", tester1.getRole_id());
        Assert.assertThrows(InvalidRequestException.class, () -> adminService.setUserRole(request2));
        Assert.assertThrows(InvalidRequestException.class, () -> adminService.setUserRole(request3));
        //endregion

    }

    @Test
    public void changeUserPassword() {
        //region <act>
        PasswordRequest request1 = new PasswordRequest(joseph.getId(), "P@ssw0rd");
        PasswordRequest request2 = new PasswordRequest(joseph.getId(), "P@ssword");
        PasswordRequest request3 = null;
        //endregion

        //region <arrange>
        adminService.changeUserPassword(request1);
        User tester1 = userService.getUserById(joseph.getId());
        //endregion

        //region <assert>
        assertNotEquals("P@ssw0rd", tester1.getPassword());
        Assert.assertThrows(InvalidRequestException.class, () -> adminService.changeUserPassword(request2));
        Assert.assertThrows(InvalidRequestException.class, () -> adminService.changeUserPassword(request3));
        //endregion

    }
}