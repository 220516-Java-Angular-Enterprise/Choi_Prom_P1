package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.util.annotations.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class ReimbCatServiceTest {

    @Spy
    private ReimbTypeDAO reimbTypeDAO;
    @InjectMocks
    private ReimbCatService reimbCatService;

    @Test
    void getCategoryById() {

    }

    @Test
    void getIdByCategory() {

    }
}