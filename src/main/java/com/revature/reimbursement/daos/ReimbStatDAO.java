package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.ReimbStat;
import com.revature.reimbursement.models.UserRole;
import com.revature.reimbursement.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReimbStatDAO implements CrudDAO<ReimbStat>{
    Connection con = DatabaseConnection.getCon();

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
        return null;
    }

    @Override
    public List<ReimbStat> getAll() {
        return null;
    }
}
