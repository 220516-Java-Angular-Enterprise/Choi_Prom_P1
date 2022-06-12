package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.dtos.requests.ReimbRequest;
import com.revature.reimbursement.dtos.requests.UpdatePendingRequest;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.services.ReimbService;
import com.revature.reimbursement.services.TokenService;
import com.revature.reimbursement.services.UserService;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class UserServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;
    private final TokenService tokenService;

    private final ReimbService reimbService;

    @Inject
    public UserServlet(ObjectMapper mapper, UserService userService,
                       TokenService tokenService, ReimbService reimbService) {
        this.mapper = mapper;
        this.userService = userService;
        this.tokenService = tokenService;
        this.reimbService = reimbService;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] uris = req.getRequestURI().split("/"); //Splits doPost for various commands


            if (uris.length == 4 && uris[3].equals("register")) { //registers a new user
                    NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
                    User createdUser = userService.register(request);
                    resp.setStatus(201); // CREATED
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(createdUser.getId()));

            } else if (uris.length == 4 && uris[3].equals("createReimb")) { //Create new reimb request
                Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
                if (requester == null) {
                    resp.setStatus(401); //Unauthorized if user does not have token / logged in.
                    return;
                }
                ReimbRequest request = mapper.readValue(req.getInputStream(), ReimbRequest.class);
                Reimb createdReimb = reimbService.createReimb(request, requester.getId());
                resp.setStatus(201); // Created
                resp.setContentType("application/json");
                resp.getWriter()
                        .write(mapper.writeValueAsString("Reimbursement Request Id: " + createdReimb.getReimbId()));

            } else if(uris.length == 4 && uris[3].equals("updateReimb")){ // Update Reimb request
                Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
                if (requester == null) {
                    resp.setStatus(401); //Unauthorized if user does not have token / logged in.
                    return;
                }
                    UpdatePendingRequest request = mapper
                            .readValue(req.getInputStream(), UpdatePendingRequest.class);
                    reimbService.updateReimb(request);
                    resp.setStatus(204); //NO CONTENT
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString("Successfully updated:" + request));

            }else throw new InvalidRequestException("The specified path does not exist.");

        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // RESOURCE CONFLICT
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] uris = req.getRequestURI().split("/");//Splits doPost for various commands

            Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

            if (requester == null) {
                resp.setStatus(401); //Unauthorized if user does not have token / logged in.
                return;
            }
            if (uris.length == 4 && uris[3].equals("sortHistoryByRecentDate")) { // Sort by most recent date.
                    List<ReimbPrincipal> reimbList = reimbService.getHistoryByAuthor(requester.getId());
                    List<ReimbPrincipal> returnList = reimbList.stream()
                            .sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed())
                            .collect(Collectors.toList());
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(returnList));

            } else if (uris.length == 4 && uris[3].equals("sortHistoryByOldestDate")) { // Sort by oldest date.
                    List<ReimbPrincipal> reimbList = reimbService.getHistoryByAuthor(requester.getId());
                    List <ReimbPrincipal> returnList = reimbList.stream()
                            .sorted(Comparator.comparing(ReimbPrincipal::getSubmitted)).collect(Collectors.toList());
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(returnList));

            } else if (uris.length == 4 && uris[3].equals("viewPending")) { // View pending orders
                    List<ReimbPrincipal> reimbList = reimbService.getAllPending();
                    reimbList.stream().sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed());
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(reimbList));

            } else if (uris.length == 4 && uris[3].equals("viewDenied")) { // View denied orders
                    List<ReimbPrincipal> reimbList = reimbService.getAllDenied();
                    reimbList.stream().sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed());
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(reimbList));

            } else if (uris.length == 4 && uris[3].equals("viewApproved")) { // View Approved orders
                    List<ReimbPrincipal> reimbList = reimbService.getAllApproved();
                    reimbList.stream().sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed());
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(reimbList));

            } else throw new InvalidRequestException("The specified path does not exist.");

        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // RESOURCE CONFLICT
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}