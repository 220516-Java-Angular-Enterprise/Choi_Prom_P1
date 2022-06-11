package com.revature.reimbursement.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.dtos.requests.ActivateRequest;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.services.ManagerService;
import com.revature.reimbursement.services.TokenService;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ManagerServlet extends HttpServlet {
    @Inject
    //region <attributes>
    private final ObjectMapper mapper;
    private final ManagerService managerService;
    private final TokenService tokenService;
    //endregion

    //region <constructors>
    public ManagerServlet(ObjectMapper mapper, ManagerService managerService, TokenService tokenService){
        this.mapper = mapper;
        this.managerService = managerService;
        this.tokenService = tokenService;
    }
    //endregion


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        String[] uris = req.getRequestURI().split("/");

        if(requester == null){ //checks for valid auth token
            resp.setStatus(401); //Unauthorized
            return;
        }

        if(!requester.getRole().equals("FIN_MAN")){ //checks requester authorization using auth token
            resp.setStatus(403); //Forbidden
            return;
        }

        try{
            if(uris.length == 4 && uris[3].equals("viewPending")){
                List<ReimbPrincipal> pending = managerService.getAllPending();
                pending.stream().sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(pending));
            }
            else if(uris.length == 4 && uris[3].equals("viewHistory")){
                List<ReimbPrincipal> history = managerService.viewApprovalHistory(requester.getId());
                history.stream().sorted(Comparator.comparing(ReimbPrincipal::getSubmitted).reversed());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(history));
            }
            else{
                throw new InvalidRequestException("The specified path does not exist.");
            }
        } catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        String[] uris = req.getRequestURI().split("/");

        if(requester == null){ //checks for valid auth token
            resp.setStatus(401); //Unauthorized
            return;
        }

        if(!requester.getRole().equals("FIN_MAN")){ //checks requester authorization using auth token
            resp.setStatus(403); //Forbidden
            return;
        }
        try{

        } catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
