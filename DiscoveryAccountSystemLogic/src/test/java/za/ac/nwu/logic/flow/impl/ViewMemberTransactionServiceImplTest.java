package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewMemberTransactionServiceImplTest {
    @Mock //creates a mock of MemberTransactionTranslator (not the actual MemberTransactionTranslator)
    private MemberTransactionTranslator memberTransactionTranslator;

    @Mock
    private ExchangeMediumTranslator exchangeMediumTranslator;

    @InjectMocks //translator is now mocked
    private ViewMemberTransactionServiceImpl viewMemberTransactionService;

    @InjectMocks
    private NewTransactionServiceImpl transactionService;

    MemberTransactionDto result;

    List<MemberTransactionDto> memberTransactionDtoList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        lenient().when(memberTransactionTranslator.addMemberTransaction(any(Member_Transaction.class))).then(returnsFirstArg()); // if get anything of MemberTransactionDto
        result = transactionService.addTransactionDto(new MemberTransactionDto(
                "Deposit",
                LocalDate.now(),
                10.0,
                1,
                1
        ));
        memberTransactionDtoList.add(result);
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    @DisplayName("Should get the transaction by an exchange medium's id.")
    public void shouldGetMemberTransactionID() {
        try {
            assertNotNull(memberTransactionDtoList);
            assertEquals(LocalDate.now(), result.getTransactionDate());
            when(viewMemberTransactionService.getMemberTransactionID(1)).thenReturn(memberTransactionDtoList);
            memberTransactionTranslator.getMemberTransactionID(1);
            verify(memberTransactionTranslator, atLeastOnce()).getMemberTransactionID(1);
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member's transaction by id."));
        }
    }

    @Test
    @DisplayName("Should get the transaction by id and date.")
    public void shouldGetTransactionByIdAndDate() {
        try {
            assertNotNull(result);
            viewMemberTransactionService.getTransactionByIdAndDate(1, LocalDate.parse("2021-08-31"));
            verify(memberTransactionTranslator, atLeastOnce()).getTransactionByIdAndDate(1, LocalDate.parse("2021-08-31"));
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member's transaction by id and a given date."));
        }
    }
}