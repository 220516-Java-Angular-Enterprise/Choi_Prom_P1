package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.LoginRequest;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.custom_exceptions.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Spy
    private UserDAO userDAO;
    @InjectMocks
    private UserService userService;

    @Spy
    private User user = new User();
    @Spy
    private NewUserRequest newUserRequest = new NewUserRequest();
    @Spy
    private LoginRequest loginRequest = new LoginRequest();
    @Spy
    private List<String> usernames = new ArrayList<>();
    @Spy
    private List<User> users = new ArrayList<>();

    @Test
    void login() {
        //region <Inactive user check>
        loginRequest.setUsername("testUsername");
        loginRequest.setPassword("P@ssw0rd");
        user = new User("01", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", false);

        doReturn(user).when(userDAO).getUserByUsernameAndPassword("testUsername", "P@ssw0rd");

        assertThrows(InvalidUserException.class, () -> userService.login(loginRequest));
        //endregion

        //region <Invalid Credential check>
        //endregion

        //region <Successful login check>
        //endregion
    }

    @Test
    void getAllUsers() {

    }

    @Test
    void getAllPendingUsers() {

    }

    @Test
    void register() {

    }

    @Test
    void getUserById() {

    }

    @Test
    void isValidPassword() {
        //region <Invalid Password check>
        //endregion

        //region <Accepted password check>
        //endregion

    }

    @Test
    void isValidEmail() {
        //region <Invalid email check>
        //endregion

        //region <valid email check>
        //endregion

    }
}