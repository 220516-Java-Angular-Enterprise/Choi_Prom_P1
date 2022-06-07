package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.UserDAO;
import com.revature.reimbursement.dtos.requests.NewUserRequest;
import com.revature.reimbursement.models.User;
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

    public User login(String username, String password) {

        User user = new User();
        List<User> users = userDAO.getAll();

        for (User u : users) {
            if (u.getUserName().equals(username)) {
                user.setId(u.getId());
                user.setUserName(u.getUserName());
                if (u.getUserPassword().equals(password)) {
                    user.setUserPassword(u.getUserPassword());
                    break;
                }
            }
            if (u.getUserPassword().equals(password)) {
                user.setUserPassword(u.getUserPassword());
            }
        }

        return isValidCredentials(user);
    }

    public User register(NewUserRequest request) {
        User user = request.extractUser();

        if (isNotDuplicateUsername(user.getUserName())) {
            if (isValidUsername(user.getUserName())) {
                if (isValidPassword(user.getUserPassword())) {
                    user.setId(UUID.randomUUID().toString());
                    userDAO.save(user);
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

    private User isValidCredentials(User user) {
        if (user.getUserName() == null && user.getUserPassword() == null)
            throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUserName() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getUserPassword() == null) throw new InvalidUserException("Incorrect password.");

        return user;
    }
}
