package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.ReimbCat;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReimbTypeDAO implements CrudDAO<ReimbCat>{

    @Override
    public void save(ReimbCat obj) {

    }

    @Override
    public void update(ReimbCat obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ReimbCat getById(String id) {
        ReimbCat category = null;
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement_categories where type_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                category.setTypeId(rs.getString("type_id"));
                category.setCategory(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return category;
    }

    public  ReimbCat getByCategory(String category){
        ReimbCat cate = new ReimbCat(null, null);
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursement_categories where category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cate.setTypeId(rs.getString("type_id"));
                cate.setCategory(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        //System.out.println(cate);
        return cate;
    }

    @Override
    public List<ReimbCat> getAll() {
        return null;
    }
}
