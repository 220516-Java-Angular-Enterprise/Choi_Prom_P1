package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.models.UserRole;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import com.revature.reimbursement.util.custom_exceptions.InvalidUserException;
import com.revature.reimbursement.util.custom_exceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;

/* Purpose: validation ie. checks username, password, and retrieve data from our daos. */
public class UserService {

    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Principal login(String username, String password) {
        Principal principal = new Principal();
        UserRoleService userRoleService = new UserRoleService();
        List<User> users = userDAO.getAll();

        for (User u: users){
            if (u.getUsername().equals(username)){
                if (u.getPassword().equals(password)){
                    principal.setId(u.getId());
                    principal.setUsername(u.getUsername());
                    principal.setRole((userRoleService.getRolebyId(u.getRole_id())));
                    break;
                }
            }
        }
       return principal;
    }

    public User register(NewUserRequest request) {
        User user = request.extractUser(); //Username, email, firstName, and lastName are already passed into user.

        if (isNotDuplicateUsername(user.getUsername())) {
            if (isValidUsername(user.getUsername())) {
                if (isValidPassword(request.getPassword())) {
                        user.setPassword(request.getPassword()); //Sets password
                    if(isValidEmail(user.getEmail())){
                        user.setId(UUID.randomUUID().toString()); //Sets Id
                        user.setRole_id(UUID.randomUUID().toString()); //Sets Role Id
                        user.setActive(true); //Sets Active boolean
                        //Saving role_id in user_roles table
                        UserRoleService userRoleService = new UserRoleService();
                        UserRole userRole = new UserRole(user.getRole_id(), "DEFAULT"); //Create new userRole with same user role ID
                        userRoleService.registerUserRole(userRole); // Registers into user into user_role table
                        userDAO.save(user); // Reigsters user.

                    } else throw new InvalidRequestException("Invalid email entered.");
                } else throw new InvalidRequestException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
            } else throw new InvalidRequestException("Invalid username. Username needs to be 8-20 characters long.");
        } else throw new ResourceConflictException("Username is already taken :(");

        return user;
    }

    public User getUserById(String id) {
        return userDAO.getById(id);
    }

    private boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isNotDuplicateUsername(String username) {
        return !userDAO.getAllUsernames().contains(username);
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    public boolean isValidEmail(String email){
    return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    }
    private User isValidCredentials(User user) {
        if (user.getUsername() == null && user.getPassword() == null)
            throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return user;
    }



}
