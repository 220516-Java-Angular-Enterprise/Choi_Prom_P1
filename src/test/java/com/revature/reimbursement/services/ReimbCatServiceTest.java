package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.ReimbCat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReimbCatServiceTest {

    @Spy
    private ReimbTypeDAO reimbTypeDAO;
    @InjectMocks
    private ReimbCatService reimbCatService;

    @Spy
    private ReimbCat other = new ReimbCat();
    @Spy
    private ReimbCat lodging = new ReimbCat();
    @Spy
    private ReimbCat travel = new ReimbCat();
    @Spy
    private ReimbCat food = new ReimbCat();

    @Test
    void getCategoryById() {
        other.setCategory("OTHER");
        lodging.setCategory("LODGING");
        travel.setCategory("TRAVEL");
        food.setCategory("FOOD");

        when(reimbTypeDAO.getById("0")).thenReturn(other);
        when(reimbTypeDAO.getById("1")).thenReturn(lodging);
        when(reimbTypeDAO.getById("2")).thenReturn(travel);
        when(reimbTypeDAO.getById("3")).thenReturn(food);


        assertEquals("OTHER", reimbCatService.getCategoryById("0"));
        assertEquals("LODGING", reimbCatService.getCategoryById("1"));
        assertEquals("TRAVEL", reimbCatService.getCategoryById("2"));
        assertEquals("FOOD", reimbCatService.getCategoryById("3"));
    }

    @Test
    void getIdByCategory() {
        other.setTypeId("0");
        lodging.setTypeId("1");
        travel.setTypeId("2");
        food.setTypeId("3");

        when(reimbTypeDAO.getByCategory("OTHER")).thenReturn(other);
        when(reimbTypeDAO.getByCategory("LODGING")).thenReturn(lodging);
        when(reimbTypeDAO.getByCategory("TRAVEL")).thenReturn(travel);
        when(reimbTypeDAO.getByCategory("FOOD")).thenReturn(food);


        assertEquals("0", reimbCatService.getIdByCategory("OTHER"));
        assertEquals("1", reimbCatService.getIdByCategory("LODGING"));
        assertEquals("2", reimbCatService.getIdByCategory("TRAVEL"));
        assertEquals("3", reimbCatService.getIdByCategory("FOOD"));
    }
}