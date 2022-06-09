package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.ReimbType;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class ReimbTypeDAO implements CrudDAO<ReimbType>{

    @Override
    public void save(ReimbType obj) {

    }

    @Override
    public void update(ReimbType obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ReimbType getById(String id) {
        return null;
    }

    @Override
    public List<ReimbType> getAll() {
        return null;
    }
}
