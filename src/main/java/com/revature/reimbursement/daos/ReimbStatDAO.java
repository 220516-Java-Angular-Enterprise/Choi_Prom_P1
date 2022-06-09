package com.revature.reimbursement.daos;

import com.revature.reimbursement.models.ReimbStat;
import com.revature.reimbursement.util.database.ConnectionFactory;

import java.sql.Connection;
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
        return null;
    }

    @Override
    public List<ReimbStat> getAll() {
        return null;
    }
}
