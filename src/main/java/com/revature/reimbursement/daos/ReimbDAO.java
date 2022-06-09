package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class ReimbDAO implements CrudDAO<Reimb>{

    @Override
    public void save(Reimb obj) {

    }

    @Override
    public void update(Reimb obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimb getById(String id) {
        return null;
    }

    @Override
    public List<Reimb> getAll() {
        return null;
    }
}
