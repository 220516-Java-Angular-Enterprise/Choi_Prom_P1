package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dtos.requests.ActivateRequest;
import com.revature.reimbursement.dtos.requests.PasswordRequest;
import com.revature.reimbursement.dtos.requests.RoleRequest;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.services.AdminService;
import com.revature.reimbursement.services.TokenService;
import com.revature.reimbursement.services.UserService;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final AdminService adminService;
    private final UserService userService;
    private final TokenService tokenService;

    public AdminServlet(ObjectMapper mapper, AdminService adminService,
                        UserService userService, TokenService tokenService) {
        this.mapper = mapper;
        this.adminService = adminService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        String[] uris = req.getRequestURI().split("/");

        if(requester == null){ //checks for valid auth token
            resp.setStatus(401); //Unauthorized
            return;
        }

        if(!requester.getRole().equals("ADMIN")){ //checks requester authorization using auth token
            resp.setStatus(403); //Forbidden
            return;
        }

        try{
            if(uris.length == 4 && uris[3].equals("setActive")){ //path to set user account active state
                ActivateRequest request = mapper.readValue(req.getInputStream(), ActivateRequest.class);
                adminService.setUserActivity(request);
                User user = userService.getUserById(request.getId());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString("Username: " + user.getUsername() +
                        "\n"+ "Role_id:" + user.getRole_id() +
                        "\n"+ "User_id:" + user.getId()));
            }
            else if(uris.length == 4 && uris[3].equals("assignRole")){ //path to assign user role
                RoleRequest request = mapper.readValue(req.getInputStream(), RoleRequest.class);
                adminService.setUserRole(request);
                User user = userService.getUserById(request.getId());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(user));
            }
            else if(uris.length == 4 && uris[3].equals("changePassword")){
                PasswordRequest request = mapper.readValue(req.getInputStream(), PasswordRequest.class);
                adminService.changeUserPassword(request);
                User user = userService.getUserById(request.getId());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(user));
            }
            else{
                throw new InvalidRequestException("The specified path does not exist.");
            }
        } catch(InvalidRequestException e){
            resp.setStatus(404); //NOT FOUND
        } catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }


    @Override //get all users
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if(requester == null){
            resp.setStatus(401); //Unauthorized
            return;
        }

        if(!requester.getRole().equals("ADMIN")){
            resp.setStatus(403); //Forbidden
            return;
        }
        List<User> users = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(users));
    }
}
