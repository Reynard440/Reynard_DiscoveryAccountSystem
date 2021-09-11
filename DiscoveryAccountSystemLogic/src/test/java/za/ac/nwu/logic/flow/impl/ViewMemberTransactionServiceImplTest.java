package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.translator.MemberTransactionTranslator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ViewMemberTransactionServiceImplTest {
    @Mock //creates a mock of MemberTransactionTranslator (not the actual MemberTransactionTranslator)
    private MemberTransactionTranslator memberTransactionTranslator;

    @InjectMocks //translator is now mocked
    private ViewMemberTransactionServiceImpl viewMemberTransactionService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllMemberTransaction() {
        try {
            MemberTransactionDto memberTransactionDto = new MemberTransactionDto();
            assertNotNull(memberTransactionDto);
            memberTransactionTranslator.getMemberTransactions();
            verify(memberTransactionTranslator, atLeastOnce()).getMemberTransactions();
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving all member transactions."));
        }
    }

    @Test
    public void getMemberTransactionID() {
        try {
            MemberTransactionDto memberTransactionDto = new MemberTransactionDto();
            assertNotNull(memberTransactionDto);
            memberTransactionTranslator.getMemberTransactionID(memberTransactionDto.getExID());
            verify(memberTransactionTranslator, atLeastOnce()).getMemberTransactionID(memberTransactionDto.getExID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member's transaction by id."));
        }
    }

    @Test
    public void getTransactionByIdAndDate() {
        try {
            MemberTransactionDto memberTransactionDto = new MemberTransactionDto();
            assertNotNull(memberTransactionDto);
            memberTransactionTranslator.getTransactionByIdAndDate(memberTransactionDto.getExID(), memberTransactionDto.getTransactionDate());
            verify(memberTransactionTranslator, atLeastOnce()).getTransactionByIdAndDate(memberTransactionDto.getExID(), memberTransactionDto.getTransactionDate());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member's transaction by id and a given date."));
        }
    }
}