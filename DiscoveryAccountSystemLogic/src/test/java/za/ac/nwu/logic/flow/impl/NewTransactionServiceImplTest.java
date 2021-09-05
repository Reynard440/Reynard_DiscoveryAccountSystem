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
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewTransactionServiceImplTest {
    @Mock //creates a mock of MemberTransactionTranslator (not the actual MemberTransactionTranslator)
    private MemberTransactionTranslator serviceMemberTransactionTranslator;

    @InjectMocks //translator is now mocked
    private NewTransactionServiceImpl transactionService;
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addTransactionDto() {
        try {
            when(serviceMemberTransactionTranslator.addMemberTransaction(any(MemberTransactionDto.class))).then(returnsFirstArg()); // if get anything of MemberTransactionDto
            MemberTransactionDto result = transactionService.addTransactionDto(new MemberTransactionDto());
            assertNotNull(result);
            assertEquals(LocalDate.now(), result.getMT_TransactionDate());
            assertFalse(result.getMT_Description().isEmpty());
            verify(serviceMemberTransactionTranslator, atLeastOnce()).addMemberTransaction(any(MemberTransactionDto.class));
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a new transaction."));
        }
    }
}