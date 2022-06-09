package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.UserRole;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRoleDAO implements CrudDAO<UserRole>{

    @Override
    public void save(UserRole obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_roles (role_id, role) VALUES (?, ?)");
            ps.setString(1, obj.getRoleId());
            ps.setString(2, obj.getRole());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(UserRole obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public UserRole getById(String id) {
        UserRole userRole = new UserRole();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user_roles where role_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserRole usRole = new UserRole(
                        rs.getString("role_id"),
                        rs.getString("role"));
                userRole = usRole;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return userRole;
    }

    @Override
    public List<UserRole> getAll() {
        return null;
    }
}
