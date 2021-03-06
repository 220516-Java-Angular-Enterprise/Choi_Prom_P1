package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dtos.requests.LoginRequest;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.services.TokenService;
import com.revature.reimbursement.services.UserRoleService;
import com.revature.reimbursement.services.UserService;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.AuthenticationException;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.InvalidUserException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthServlet(ObjectMapper mapper, UserService userService, TokenService tokenService) {
        this.mapper = mapper;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LoginRequest request = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = new Principal(userService.login(request));
            UserRoleService userRoleService = new UserRoleService();
            //Stateful session management
            String token = tokenService.generateToken(principal);
            resp.setHeader("Authorization", token);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString("Username: " + principal.getUsername() +
                    "\n"+ "Role:" + userRoleService.getRoleById(principal.getRole()) +
                    "\n"+ "User_id:" + principal.getId()));
        } catch(JsonParseException | JsonMappingException | NullPointerException e) {
            resp.setStatus(400); //BAD REQUEST
        } catch(AuthenticationException e) {
            resp.setStatus(401); //UNAUTHORIZED
        } catch(InvalidUserException e){
            resp.setStatus(403); //FORBIDDEN
        } catch(InvalidRequestException e){
            resp.setStatus(404); //NOT FOUND
        } catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
