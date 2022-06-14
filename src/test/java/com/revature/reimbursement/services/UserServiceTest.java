package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.LoginRequest;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.InvalidUserException;
import com.revature.reimbursement.util.custom_exceptions.ResourceConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
    private User activeUser = new User();
    @Spy
    private User inActiveUser = new User();
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

        //region <Invalid Username check>
        loginRequest.setUsername("abc");
        loginRequest.setPassword("P@ssw0rd");

        assertThrows(InvalidRequestException.class, () -> userService.login(loginRequest));
        //endregion

        //region <Invalid Password check>
        loginRequest.setUsername("testUsername");
        loginRequest.setPassword("passss");

        assertThrows(InvalidRequestException.class, () -> userService.login(loginRequest));
        //endregion

        //region <Successful login check>
        loginRequest.setUsername("haiiscool15");
        loginRequest.setPassword("P@ssw0rd");
        user = new User("01", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", true);

        doReturn(user).when(userDAO).getUserByUsernameAndPassword("haiiscool15", "P@ssw0rd");
        assertEquals(user, userService.login(loginRequest));
        //endregion
    }

    @Test
    void getAllUsers() {

        //region <Retrieved List will match>
        doReturn(users).when(userDAO).getAll();
        assertEquals(users, userService.getAllUsers());
        //endregion
    }

    @Test
    void getAllPendingUsers() {
        //region <List size will be 2 for 2 inactive users>
        user = new User("01", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", false);
        activeUser = new User("01", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", true);
        inActiveUser = new User("01", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", false);
        users.add(user);
        users.add(activeUser);
        users.add(inActiveUser);
        doReturn(users).when(userDAO).getAll();
        assertEquals(2, userService.getAllPendingUsers().size());
        //endregion
    }

    @Test
    void register() {
        //region <Duplicate username>
        newUserRequest.setUsername("Haiiscool");
        newUserRequest.setPassword("P@ssw0rd");
        newUserRequest.setEmail("thisisgoodemail@gmail.com");
        newUserRequest.setFirstName("Hai");
        newUserRequest.setLastName("Test");
        user.setUsername("Haiiscool");
        users.add(user);
        assertThrows(ResourceConflictException.class, () -> userService.register(newUserRequest));
        //endregion
        //region <Invalid username>
        newUserRequest.setUsername("naah");
        assertThrows(InvalidRequestException.class, () -> userService.register(newUserRequest));
        //endregion
        //region <Invalid password>
        newUserRequest.setUsername("Haiiscool2");
        newUserRequest.setPassword("pass");
        assertThrows(InvalidRequestException.class, () -> userService.register(newUserRequest));
        //endregion
        //region <Valid Register>
        newUserRequest.setPassword("P@ssw0rd");

        doNothing().when(userDAO).save(any());
        assertEquals(newUserRequest.getUsername(), userService.register(newUserRequest).getUsername());
        assertEquals(newUserRequest.getPassword(), userService.register(newUserRequest).getPassword());

    }

    @Test
    void getUserById() {
        //region <Will fetch correct user>
        user = new User("01", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", false);
        activeUser = new User("02", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", true);
        inActiveUser = new User("03", loginRequest.getUsername(), loginRequest.getPassword(), "",
                "", "", "", false);
        users.add(user);
        users.add(activeUser);
        users.add(inActiveUser);
        doReturn(user).when(userDAO).getById("01");
        assertEquals(user, userService.getUserById("01"));
        //endregion
        //region <Will return null if user id does not exist>
        assertNull(userService.getUserById("04"));
        //endregion

    }

    @Test
    void isValidPassword() {
        //region <Invalid Password check>
        String password = "pass";
        assertFalse(userService.isValidPassword(password));
        //endregion

        //region <Accepted password check>
        String password2 = "P@ssw0rd";
        assertTrue(userService.isValidPassword(password2));
        //endregion

    }

    @Test
    void isValidEmail() {
        //region <Invalid email check>
        String wrongEmail = "reallylongemail";
        assertFalse(userService.isValidEmail(wrongEmail));
        //endregion

        //region <valid email check>
        String rightEmail = "reallylongemail@gmail.com";
        assertTrue(userService.isValidEmail(rightEmail));
        //endregion

    }
}