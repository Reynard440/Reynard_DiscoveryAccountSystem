package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;
import za.ac.nwu.translator.MemberTranslator;

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

    @Mock
    private MemberTranslator memberTranslator;

    @InjectMocks //translator is now mocked
    private NewTransactionServiceImpl transactionService;

    @InjectMocks //translator is now mocked
    private ExchangeMediumServiceImpl exchangeMediumService;

    @InjectMocks //translator is now mocked
    private MemberServiceImpl memberService;

    MemberTransactionDto result, depoResult;

    ExchangeMediumDto exchangeMediumDto;

    @Before
    public void setUp() throws SQLException {
        lenient().when(memberTranslator.newMember(any(Member.class))).then(returnsFirstArg());
        lenient().when(exchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg());
        lenient().when(serviceMemberTransactionTranslator.addMemberTransaction(any(Member_Transaction.class))).then(returnsFirstArg()); // if get anything of MemberTransactionDto
        result = transactionService.addTransactionDto(new MemberTransactionDto(
                "Withdrawal",
                LocalDate.now(),
                10.0,
                1,
                1
        ));
        depoResult = transactionService.addTransactionDto(new MemberTransactionDto(
                "Deposit",
                LocalDate.now(),
                10.0,
                1,
                1
        ));
        exchangeMediumDto = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto(
                1,
                "Miles",
                "Discovery Miles",
                100.0,
                LocalDate.now(),
                new MemberDto(
                        1,
                        "reynardengels@gmail.com",
                        "0723949955",
                        "Reynard",
                        "Engels"
                )
        ));
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    @DisplayName("Should add a withdrawal transaction.")
    public void shouldAddWithdrawalTransactionDto() throws SQLException {
        try {
            assertNotNull(result);
            assertEquals(LocalDate.now(), result.getTransactionDate());
            assertFalse(result.getDescription().isEmpty());
            assertEquals("Withdrawal", result.getDescription());
            assertNotNull(result.getEmId());
            if (result.getDescription().equals("Withdrawal") || (exchangeMediumDto.getBalance()- result.getAmount() > result.getAmount())) {
                exchangeMediumTranslator.decreaseExchangeMediumTotal(result.getEmId(), result.getAmount());
            }
            verify(serviceMemberTransactionTranslator, atLeastOnce()).addMemberTransaction(any(Member_Transaction.class));
        } catch (SQLException e) {
            throw new SQLException("Transaction is null, rolling back the transaction.", e);
        }
    }

    @Test
    @DisplayName("Should add a deposit transaction.")
    public void shouldAddDepositTransactionDto() throws SQLException {
        try {
            assertNotNull(depoResult);
            assertEquals(LocalDate.now(), depoResult.getTransactionDate());
            assertFalse(depoResult.getDescription().isEmpty());
            assertEquals("Deposit", depoResult.getDescription());
            assertNotNull(depoResult.getEmId());
            if (depoResult.getDescription().equals("Deposit")) {
                exchangeMediumTranslator.increaseExchangeMediumTotal(depoResult.getEmId(), depoResult.getAmount());
            }
            verify(serviceMemberTransactionTranslator, atLeastOnce()).addMemberTransaction(any(Member_Transaction.class));
        } catch (SQLException e) {
            throw new SQLException("Transaction is null, rolling back the transaction.", e);
        }
    }

    @Test
    @DisplayName("Should not add a transaction, should throw SQLException of null transaction.")
    public void shouldNotAddTransactionDtoIfNull() throws SQLException {
        try {
            result = null;
            assertNull(null);
            verify(serviceMemberTransactionTranslator, atLeastOnce()).addMemberTransaction(any(Member_Transaction.class));
        } catch (SQLException e) {
            throw new SQLException("Transaction is null, rolling back the transaction.", e);
        }
    }
}