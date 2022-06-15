package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.ReimbCat;
import com.revature.reimbursement.models.ReimbStat;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReimbStatDAO implements CrudDAO<ReimbStat>{

    @Override
    public void save(ReimbStat obj) {

    }

    @Override
    public void update(ReimbStat obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ReimbStat getById(String id) {
        ReimbStat status = new ReimbStat();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement_statuses where status_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                status.setStatusId(rs.getString("status_id"));
                status.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return status;
    }

    public ReimbStat getByStatus(String status){
        ReimbStat stat = new ReimbStat();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement_statuses where status = ?");
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                stat.setStatusId(rs.getString("status_id"));
                stat.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return stat;
    }

    @Override
    public List<ReimbStat> getAll() {
        return null;
    }
}
