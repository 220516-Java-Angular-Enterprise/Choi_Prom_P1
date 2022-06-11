package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.ReimbCat;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbDAO implements CrudDAO<Reimb>{

    @Override
    public void save(Reimb obj) {
    try(Connection con = ConnectionFactory.getInstance().getConnection()){
        PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursements (reimb_id, amount, submitted, resolved, description, payment_id, author_id, resolver_id, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, obj.getReimbId());
        ps.setInt(2, obj.getAmount());
        ps.setString(3, obj.getSubmitted());
        ps.setString(4, obj.getResolved());
        ps.setString(5, obj.getDescription());
        ps.setString(6, obj.getPaymentId());
        ps.setString(7, obj.getAuthorId());
        ps.setString(8, obj.getResolverId());
        ps.setString(9, obj.getStatusId());
        ps.setString(10, obj.getTypId());
        ps.executeUpdate();
    }catch (SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    }

    @Override
    public void update(Reimb obj) {
    try(Connection con = ConnectionFactory.getInstance().getConnection()){
        PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET" +
                "author_id = ?, " +
                "amount = ?, " +
                "submitted = ?, " +
                "resolved = ?, " +
                "description = ?," +
                "payment_id = ?," +
                "resolver_id = ?," +
                "status_id = ?," +
                "type_id = ? WHERE reimb_id = ?");
        ps.setString(1, obj.getAuthorId());
        ps.setInt(2, obj.getAmount());
        ps.setString(3, obj.getSubmitted());
        ps.setString(4, obj.getResolved());
        ps.setString(5, obj.getDescription());
        ps.setString(6, obj.getPaymentId());
        ps.setString(7, obj.getResolverId());
        ps.setString(8, obj.getStatusId());
        ps.setString(9, obj.getTypId());
        ps.setString(10, obj.getReimbId());
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimb getById(String id) {
        Reimb reimbursementOrder = new Reimb();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements where reimb_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reimbursementOrder.setReimbId(rs.getString("reimb_id"));
                reimbursementOrder.setAmount(rs.getInt("amount"));
                reimbursementOrder.setAuthorId(rs.getString("author_id"));
                reimbursementOrder.setDescription(rs.getString("description"));
                reimbursementOrder.setSubmitted(rs.getString("submitted"));
                reimbursementOrder.setResolved(rs.getString("resolved"));
                reimbursementOrder.setResolverId(rs.getString("resolver_id"));
                reimbursementOrder.setPaymentId(rs.getString("payment_id"));
                reimbursementOrder.setStatusId(rs.getString("status_id"));
                reimbursementOrder.setTypId(rs.getString("type_id"));

            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return reimbursementOrder;
    }

    public List<Reimb> getByAuthorId(String id){
        List<Reimb> reimbursementOrders = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements where author_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimb reimbursementOrder = new Reimb();
                reimbursementOrder.setReimbId(rs.getString("reimb_id"));
                reimbursementOrder.setAmount(rs.getInt("amount"));
                reimbursementOrder.setAuthorId(rs.getString("author_id"));
                reimbursementOrder.setDescription(rs.getString("description"));
                reimbursementOrder.setSubmitted(rs.getString("submitted"));
                reimbursementOrder.setResolved(rs.getString("resolved"));
                reimbursementOrder.setResolverId(rs.getString("resolver_id"));
                reimbursementOrder.setPaymentId(rs.getString("payment_id"));
                reimbursementOrder.setStatusId(rs.getString("status_id"));
                reimbursementOrder.setTypId(rs.getString("type_id"));
                reimbursementOrders.add(reimbursementOrder);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return reimbursementOrders;
    }

    public List<Reimb> getByAuthorIdAndStatusId(String id, String statusId){
        List<Reimb> reimbursementOrders = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE author_id = ? AND status_id= ?");
            ps.setString(1, id);
            ps.setString(2, statusId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimb reimbursementOrder = new Reimb();
                reimbursementOrder.setReimbId(rs.getString("reimb_id"));
                reimbursementOrder.setAmount(rs.getInt("amount"));
                reimbursementOrder.setAuthorId(rs.getString("author_id"));
                reimbursementOrder.setDescription(rs.getString("description"));
                reimbursementOrder.setSubmitted(rs.getString("submitted"));
                reimbursementOrder.setResolved(rs.getString("resolved"));
                reimbursementOrder.setResolverId(rs.getString("resolver_id"));
                reimbursementOrder.setPaymentId(rs.getString("payment_id"));
                reimbursementOrder.setStatusId(rs.getString("status_id"));
                reimbursementOrder.setTypId(rs.getString("type_id"));
                reimbursementOrders.add(reimbursementOrder);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return reimbursementOrders;
    }

    public List<Reimb> getByStatusId(String statusId){
        List<Reimb> reimbursementOrders = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE  status_id= ?");
            ps.setString(1, statusId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimb reimbursementOrder = new Reimb();
                reimbursementOrder.setReimbId(rs.getString("reimb_id"));
                reimbursementOrder.setAmount(rs.getInt("amount"));
                reimbursementOrder.setAuthorId(rs.getString("author_id"));
                reimbursementOrder.setDescription(rs.getString("description"));
                reimbursementOrder.setSubmitted(rs.getString("submitted"));
                reimbursementOrder.setResolved(rs.getString("resolved"));
                reimbursementOrder.setResolverId(rs.getString("resolver_id"));
                reimbursementOrder.setPaymentId(rs.getString("payment_id"));
                reimbursementOrder.setStatusId(rs.getString("status_id"));
                reimbursementOrder.setTypId(rs.getString("type_id"));
                reimbursementOrders.add(reimbursementOrder);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return reimbursementOrders;
    }

    @Override
    public List<Reimb> getAll() {
        List<Reimb> reimbursementOrders = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimb reimbursementOrder = new Reimb();
                reimbursementOrder.setReimbId(rs.getString("reimb_id"));
                reimbursementOrder.setAmount(rs.getInt("amount"));
                reimbursementOrder.setAuthorId(rs.getString("author_id"));
                reimbursementOrder.setDescription(rs.getString("description"));
                reimbursementOrder.setSubmitted(rs.getString("submitted"));
                reimbursementOrder.setResolved(rs.getString("resolved"));
                reimbursementOrder.setResolverId(rs.getString("resolver_id"));
                reimbursementOrder.setPaymentId(rs.getString("payment_id"));
                reimbursementOrder.setStatusId(rs.getString("status_id"));
                reimbursementOrder.setTypId(rs.getString("type_id"));
                reimbursementOrders.add(reimbursementOrder);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return reimbursementOrders;
    }
}
