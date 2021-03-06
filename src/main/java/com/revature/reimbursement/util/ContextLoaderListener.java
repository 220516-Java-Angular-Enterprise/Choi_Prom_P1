package com.revature.reimbursement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.services.*;
import com.revature.reimbursement.servlets.AdminServlet;
import com.revature.reimbursement.servlets.AuthServlet;
import com.revature.reimbursement.servlets.ManagerServlet;
import com.revature.reimbursement.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* Need this ContextLoaderListener for our dependency injection upon deployment. */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nInitializing Reimbursement Web Application");

        /* ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects) */
        ObjectMapper mapper = new ObjectMapper();

        /* Dependency injection. */
        TokenService tokenService = new TokenService(new JwtConfig());
        ReimbStatusService reimbStatusService = new ReimbStatusService(new ReimbStatDAO());
        ReimbCatService reimbCatService = new ReimbCatService(new ReimbTypeDAO());
        ReimbService reimbService = new ReimbService(new ReimbDAO(), reimbStatusService, reimbCatService);
        UserService userService = new UserService(new UserDAO());

        ManagerService managerService = new ManagerService(reimbService, reimbStatusService, reimbCatService);
        AdminService adminService = new AdminService(userService);

        UserServlet userServlet = new UserServlet(mapper, userService, tokenService, reimbService);
        AuthServlet authServlet = new AuthServlet(mapper, userService, tokenService);
        AdminServlet adminServlet = new AdminServlet(mapper, adminService, userService, tokenService);
        ManagerServlet managerServlet = new ManagerServlet(mapper, managerService, reimbService, tokenService);

        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("AdminServlet", adminServlet).addMapping("/admin/*");
        context.addServlet("ManagerServlet", managerServlet).addMapping("/manager/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down the Reimbursement Web Application");
    }
}
