package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.JwtConfig;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenServiceTest {
    @Inject
    UserService userService = new UserService(new UserDAO());
    TokenService tokenService = new TokenService(new JwtConfig());
    User joseph = userService.getUserById("3bb07305-ca5c-4b33-9249-4d8c8e50cf45");
    Principal principal = new Principal(joseph);
    @Test
    public void workingTokenMethods(){
        String token = tokenService.generateToken(principal);
        Principal requester = tokenService.extractRequesterDetails(token);
        assertEquals(joseph.getUsername(), requester.getUsername());
    }
}