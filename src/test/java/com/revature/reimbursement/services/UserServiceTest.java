package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.LoginRequest;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.custom_exceptions.AuthenticationException;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.ResourceConflictException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserService(new UserDAO());

    @Test
    public void login_WillThrowExceptionIfUsernameIsInvalid() {
//  Act
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("acb");
        loginRequest.setPassword("P@ssw0rd");
//  Assert
        assertThrows(InvalidRequestException.class, () -> userService.login(loginRequest));

    }
    @Test
    public void login_WillThrowExceptionIfPasswordIsInvalid() {
//  Act
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("amenokageguchi");
        loginRequest.setPassword("P@so");
//  Assert
        assertThrows(InvalidRequestException.class, () -> userService.login(loginRequest));

    }
    @Test
    public void login_WillThrowExceptionIfUsernameAndPasswordIsInvalid() {
//  Act
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("acb");
        loginRequest.setPassword("P@so");
//  Assert
        assertThrows(InvalidRequestException.class, () -> userService.login(loginRequest));

    }

    @Test
    public void login_WillThrowExceptionIfUserIsNotActive() {
//  Act
        LoginRequest loginRequest = new LoginRequest("haiiscool13", "#Haiiscool123");
        User user = new UserDAO().GetUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        user.setActive(false);
        userService.update(user);
//  Assert
        assertThrows(AuthenticationException.class, () -> userService.login(loginRequest));

    }


    @Test
    public void getAllUsers_WillNotBeNull() {
        //Assert
        assertNotNull(userService.getAllUsers());
    }

    @Test
    public void update_WillSetActiveToTrueIfUpdated() {
//        Act
        User user =  userService.getUserById("e1264796-edbf-403b-8821-2b5468a8f72a");
        user.setActive(true);
        userService.update(user);
//        Assert
        assertTrue(user.isActive());
    }

    @Test
    public void register_WillThrowExceptionIfDuplicateUsername() {
//        Act
        NewUserRequest request = new NewUserRequest("haiiscool13", "P@ssw0rd", "Hai", "Prom", "newemail22@gmail.com"); //username duplicate
//        Assert
        assertThrows(ResourceConflictException.class, () -> userService.register(request));
    }

    @Test
    public void register_WillThrowExceptionIfPasswordIsInvalid() {
//        Act
        NewUserRequest request = new NewUserRequest("haiiscool14", "P@sso", "Hai", "Prom", "newemail22@gmail.com"); //username duplicate
//        Assert
        assertThrows(InvalidRequestException.class, () -> userService.register(request));
    }

    @Test
    public void register_WillThrowExceptionIfUsernameIsInvalid() {
//        Act
        NewUserRequest request = new NewUserRequest("hai", "P@ssw0rd", "Hai", "Prom", "newemail22@gmail.com"); //username duplicate
//        Assert
        assertThrows(InvalidRequestException.class, () -> userService.register(request));
    }

    @Test
    public void register_WillThrowExceptionIfEmailIsInvalid() {
//        Act
        NewUserRequest request = new NewUserRequest("hai", "P@ssw0rd", "Hai", "Prom", "newemail22@"); //username duplicate
//        Assert
        assertThrows(InvalidRequestException.class, () -> userService.register(request));
    }

    @Test
    public void getUserById_WillBeNullIfIdIsInvalid() {
//        Act
        String id = "dasdasd";
//        Assert
        assertNull(userService.getUserById(id));
    }

   @Test
    public void isValidPassword_WillBeFalseIfPasswordIsInvalid(){
//        Act
       String password = "pas0";
//       Assert
       assertFalse(userService.isValidPassword(password));
   }

    @Test
    public void isValidPassword_WillBeFalseIfPasswordIsEmpty(){
//        Act
        String password = "";
//       Assert
        assertFalse(userService.isValidPassword(password));
    }

    @Test
    public void isValidEmail_WillBeFalseIfEmailIsEmpty(){
//        Act
        String email = "";
//       Assert
        assertFalse(userService.isValidEmail(email));
    }

    @Test
    public void isValidEmail_WillBeFalseIfEmailIsInvalid(){
//        Act
        String email = "bababadoo";
//       Assert
        assertFalse(userService.isValidEmail(email));
    }
}