package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dtos.requests.LoginRequest;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.services.UserService;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;

    public AuthServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LoginRequest request = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = userService.login(request.getUsername(), request.getPassword());
            resp.setStatus(201);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(principal.getUsername()+", "+ principal.getRole()+", "+ principal.getId()));
        } catch(InvalidRequestException e){
            resp.setStatus(404);
        } catch (ResourceConflictException e){
            resp.setStatus(409);
        } catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
