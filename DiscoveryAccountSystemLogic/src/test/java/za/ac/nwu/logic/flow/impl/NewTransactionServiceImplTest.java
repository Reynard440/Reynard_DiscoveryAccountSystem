package za.ac.nwu.logic.flow.impl;

import net.bytebuddy.asm.Advice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewTransactionServiceImplTest {
    @Mock
    private ExchangeMediumTranslator exchangeMediumTranslator;

    @Mock //creates a mock of MemberTransactionTranslator (not the actual MemberTransactionTranslator)
    private MemberTransactionTranslator serviceMemberTransactionTranslator;

    @InjectMocks //translator is now mocked
    private NewTransactionServiceImpl transactionService;

    MemberTransactionDto result;

    @Before
    public void setUp() throws SQLException {
        lenient().when(exchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg());
        lenient().when(serviceMemberTransactionTranslator.addMemberTransaction(any(Member_Transaction.class))).then(returnsFirstArg()); // if get anything of MemberTransactionDto
        result = transactionService.addTransactionDto(new MemberTransactionDto());
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    public void shouldAddTransactionDto() {
        try {
            assertNotNull(result);
            assertEquals(LocalDate.now(), result.getTransactionDate());
            assertFalse(result.getDescription().isEmpty());
            verify(serviceMemberTransactionTranslator, atLeastOnce()).addMemberTransaction(any(Member_Transaction.class));
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a new transaction."));
        }
    }
}