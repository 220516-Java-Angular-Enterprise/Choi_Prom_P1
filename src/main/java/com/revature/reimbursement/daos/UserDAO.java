package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.custom_exceptions.InvalidSQLException;
import com.revature.reimbursement.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role_id, email, given_name, surname, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getRole_id());
            ps.setString(5, obj.getEmail());
            ps.setString(6, obj.getGivenName());
            ps.setString(7, obj.getSurname());
            ps.setBoolean(8, obj.isActive());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        User user = new User();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User us = new User(rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role_id"),
                        rs.getString("email"),
                        rs.getString("given_name"),
                        rs.getString("surname"),
                        rs.getBoolean("is_active"));
                user = us;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role_id"),
                        rs.getString("email"),
                        rs.getString("given_name"),
                        rs.getString("surname"),
                        rs.getBoolean("is_active"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return users;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT userName FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("userName"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return usernames;
    }

    public User getUserbyUsernameAndPassword(String username, String password){
        User user = new User();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT users WHERE username='"+username+"' AND password='"+password+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User us = new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role_id"),
                        rs.getString("email"),
                        rs.getString("given_name"),
                        rs.getString("surname"),
                        rs.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return user;
    }
}
