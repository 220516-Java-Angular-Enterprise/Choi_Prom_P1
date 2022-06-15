package com.revature.reimbursement.services;

import com.revature.reimbursement.daos.ReimbDAO;
import com.revature.reimbursement.daos.ReimbStatDAO;
import com.revature.reimbursement.daos.ReimbTypeDAO;
import com.revature.reimbursement.dtos.response.ReimbPrincipal;
import com.revature.reimbursement.models.Reimb;
import com.revature.reimbursement.models.ReimbCat;
import com.revature.reimbursement.models.ReimbStat;
import com.revature.reimbursement.models.User;
import com.revature.reimbursement.util.annotations.Inject;
import com.revature.reimbursement.util.custom_exceptions.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReimbServiceTest {

    @Spy
    private ReimbDAO mockDAO;
    @Spy
    private ReimbStatDAO mockStatDAO;
    @Spy
    private ReimbTypeDAO mockTypeDAO;

    @Spy
    private ReimbStatusService reimbStatusService = new ReimbStatusService(mockStatDAO);
    @Spy
    public ReimbCatService reimbCatService = new ReimbCatService(mockTypeDAO);
    @InjectMocks
    private ReimbService reimbService;

    @Spy
    private Reimb mockReimb1 = new Reimb();
    @Spy
    private Reimb mockReimb2 = new Reimb();
    @Spy
    private Reimb mockReimb3 = new Reimb();

    @Spy ReimbPrincipal mockReimbPrin1 = new ReimbPrincipal();
    @Spy ReimbPrincipal mockReimbPrin2 = new ReimbPrincipal();
    @Spy ReimbPrincipal mockReimbPrin3 = new ReimbPrincipal();
    @Spy
    private List<Reimb> mockReimbs = new ArrayList<>();
    @Spy
    private ReimbStat mockReimbStat = new ReimbStat();
    @Spy
    private ReimbStat mockReimbStat2 = new ReimbStat();
    @Spy
    private ReimbCat mockReimbCat = new ReimbCat();
    @Spy
    private ReimbCat mockReimbCat2 = new ReimbCat();
    @Test
    void getById() {
        //region <will fetch correct Reimb>
        mockReimb1.setReimbId("01");
        mockReimb2.setReimbId("02");
        mockReimb3.setReimbId("03");
        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        doReturn(mockReimb1).when(mockDAO).getById(("01"));
        assertEquals(mockReimb1, reimbService.getById("01"));
        //endregion
        //region <Attribute will return null if reimbId does not exist>
        assertNull(reimbService.getById("04").getReimbId());
        //endregion


    }

    @Test
    void getAll() {
        //region <Retrieved List will match>
        doReturn(mockReimbs).when(mockDAO).getAll();
        assertEquals(mockReimbs, reimbService.getAll());
        //endregion
    }

    @Test
    void getAllPendingByAuthorId() {
        //region <Will retrieve size 2 for 2 pending orders>
        List<ReimbPrincipal> pendingList = new ArrayList<>();


        mockReimb1.setReimbId("01");
        mockReimb2.setReimbId("02");
        mockReimb3.setReimbId("03");


        mockReimb1.setStatusId("0");
        mockReimb2.setStatusId("1");
        mockReimb3.setStatusId("0");

        mockReimb1.setTypId("0");
        mockReimb2.setTypId("0");
        mockReimb3.setTypId("0");

        mockReimb1.setAuthorId("0");
        mockReimb2.setAuthorId("0");
        mockReimb3.setAuthorId("0");


        mockReimbPrin1.setReimbId("01");
        mockReimbPrin2.setReimbId("02");
        mockReimbPrin3.setReimbId("03");


        mockReimbPrin1.setStatusId("PENDING");
        mockReimbPrin2.setStatusId("APPROVED");
        mockReimbPrin3.setStatusId("PENDING");

        mockReimbPrin1.setTypeId("OTHER");
        mockReimbPrin2.setTypeId("OTHER");
        mockReimbPrin3.setTypeId("OTHER");


        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        pendingList.add(mockReimbPrin1);
        pendingList.add(mockReimbPrin3);

        doReturn(mockReimbs).when(mockDAO).getAll();

        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("OTHER").when(reimbCatService).getCategoryById("0");
        assertEquals(pendingList.toString(), reimbService.getAllPendingByAuthorId("0").toString());

        //endregion
        //region <Will throw exception if user does not exist>
        assertThrows(InvalidRequestException.class, () -> reimbService.getHistoryByAuthor("2"));
        //endregion

    }

    @Test
    void getAllApproved() {
        //region <Will equals Approved List when called.>
        List<ReimbPrincipal> approvedList = new ArrayList<>();

        mockReimb1.setReimbId("01");
        mockReimb2.setReimbId("02");
        mockReimb3.setReimbId("03");


        mockReimb1.setStatusId("0");
        mockReimb2.setStatusId("1");
        mockReimb3.setStatusId("0");

        mockReimb1.setTypId("0");
        mockReimb2.setTypId("0");
        mockReimb3.setTypId("0");

        mockReimb1.setAuthorId("0");
        mockReimb2.setAuthorId("0");
        mockReimb3.setAuthorId("0");


        mockReimbPrin1.setReimbId("01");
        mockReimbPrin2.setReimbId("02");
        mockReimbPrin3.setReimbId("03");


        mockReimbPrin1.setStatusId("PENDING");
        mockReimbPrin2.setStatusId("APPROVED");
        mockReimbPrin3.setStatusId("PENDING");

        mockReimbPrin1.setTypeId("OTHER");
        mockReimbPrin2.setTypeId("OTHER");
        mockReimbPrin3.setTypeId("OTHER");


        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        approvedList.add(mockReimbPrin2);

        doReturn("APPROVED").when(reimbStatusService).getStatusById("1");
        doReturn("OTHER").when(reimbCatService).getCategoryById("0");
        doReturn(mockReimbs).when(mockDAO).getAll();
        assertEquals(approvedList.toString(), reimbService.getAllApproved().toString());
        //endregion
    }

    @Test
    void getAllDenied() {
//region <Will return denied list when called>
        List<ReimbPrincipal> deniedList = new ArrayList<>();

        mockReimb1.setReimbId("01");
        mockReimb2.setReimbId("02");
        mockReimb3.setReimbId("03");


        mockReimb1.setStatusId("-1");
        mockReimb2.setStatusId("-1");
        mockReimb3.setStatusId("0");

        mockReimb1.setTypId("0");
        mockReimb2.setTypId("0");
        mockReimb3.setTypId("0");

        mockReimb1.setAuthorId("0");
        mockReimb2.setAuthorId("0");
        mockReimb3.setAuthorId("0");


        mockReimbPrin1.setReimbId("01");
        mockReimbPrin2.setReimbId("02");
        mockReimbPrin3.setReimbId("03");


        mockReimbPrin1.setStatusId("DENIED");
        mockReimbPrin2.setStatusId("DENIED");
        mockReimbPrin3.setStatusId("PENDING");

        mockReimbPrin1.setTypeId("OTHER");
        mockReimbPrin2.setTypeId("OTHER");
        mockReimbPrin3.setTypeId("OTHER");


        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        deniedList.add(mockReimbPrin1);
        deniedList.add(mockReimbPrin2);

        doReturn("DENIED").when(reimbStatusService).getStatusById("-1");
        doReturn("OTHER").when(reimbCatService).getCategoryById("0");
        doReturn(mockReimbs).when(mockDAO).getAll();
        assertEquals(deniedList.toString(), reimbService.getAllDenied().toString());
    }

    @Test
    void getHistoryByAuthor() {
        //region <Will return history when called>
        List<ReimbPrincipal> history = new ArrayList<>();

        mockReimb1.setReimbId("01");
        mockReimb2.setReimbId("02");
        mockReimb3.setReimbId("03");


        mockReimb1.setStatusId("-1");
        mockReimb2.setStatusId("-1");
        mockReimb3.setStatusId("0");

        mockReimb1.setTypId("0");
        mockReimb2.setTypId("0");
        mockReimb3.setTypId("0");

        mockReimb1.setAuthorId("0");
        mockReimb2.setAuthorId("0");
        mockReimb3.setAuthorId("0");


        mockReimbPrin1.setReimbId("01");
        mockReimbPrin2.setReimbId("02");
        mockReimbPrin3.setReimbId("03");


        mockReimbPrin1.setStatusId("DENIED");
        mockReimbPrin2.setStatusId("DENIED");
        mockReimbPrin3.setStatusId("PENDING");

        mockReimbPrin1.setTypeId("OTHER");
        mockReimbPrin2.setTypeId("OTHER");
        mockReimbPrin3.setTypeId("OTHER");


        mockReimbs.add(mockReimb1);
        mockReimbs.add(mockReimb2);
        mockReimbs.add(mockReimb3);

        history.add(mockReimbPrin1);
        history.add(mockReimbPrin2);
        history.add(mockReimbPrin3);


        doReturn("DENIED").when(reimbStatusService).getStatusById("-1");
        doReturn("PENDING").when(reimbStatusService).getStatusById("0");
        doReturn("OTHER").when(reimbCatService).getCategoryById("0");
        doReturn(mockReimbs).when(mockDAO).getAll();
        assertEquals(history.toString(), reimbService.getHistoryByAuthor("0").toString());
        //endRegion

        //region <Will throw exception if author does not exist>
        assertThrows(InvalidRequestException.class, () -> reimbService.getHistoryByAuthor("045"));
        //endRegion
    }
}

