package za.ac.nwu.translator.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberTransactionTranslatorImplTest {
    @Mock //creates a mock of MemberTransactionRepository (not the actual MemberTransactionRepository)
    private MemberTransactionRepository memberTransactionRepository;

    @Mock
    private ExchangeMediumRepository exchangeMediumRepository;

    @InjectMocks //repository is now mocked
    private MemberTransactionTranslatorImpl memberTransactionTranslator;

    Member_Transaction result;

    @Before
    public void setUp() throws Exception {
        lenient().when(memberTransactionRepository.save(any(Member_Transaction.class))).then(returnsFirstArg());
        lenient().when(exchangeMediumRepository.save(any(Exchange_Medium.class))).then(returnsFirstArg());
        result = memberTransactionRepository.save(new Member_Transaction(
                1,
                LocalDate.now(),
                "Withdrawal",
                100.0,
                1
        ));
    }

    @After
    public void tearDown() throws Exception {
        result = null;
    }

    @Transactional
    @Test
    @DisplayName("Should add a new transaction.")
    public void shouldAddMemberTransaction() {
        try {
            assertNotNull(result);
            memberTransactionTranslator.addMemberTransaction(result);
            verify(memberTransactionRepository, atLeastOnce()).save(any(Member_Transaction.class));
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while creating a member transaction."));
        }
    }

    @Test
    @DisplayName("Should get a transaction by EmId.")
    public void shouldGetMemberTransactionID() {
        try {
            assertNotNull(result);
            when(memberTransactionTranslator.getMemberTransactionID(result.getEmId().getEmId())).thenReturn(Collections.singletonList(result));
            memberTransactionTranslator.getMemberTransactionID(result.getEmId().getEmId());
            verify(memberTransactionRepository, atLeastOnce()).getByEmId(result.getEmId().getEmId());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member transaction by id."));
        }
    }

    @Test
    @DisplayName("Should get a transaction by EmId and date.")
    public void shouldGetTransactionByIdAndDate() {
        try {
            assertNotNull(result);
            when(memberTransactionTranslator.getTransactionByIdAndDate(result.getEmId().getEmId(), result.getTransactionDate())).thenReturn(Collections.singletonList(result));
            memberTransactionTranslator.getTransactionByIdAndDate(result.getEmId().getEmId(), result.getTransactionDate());
            verify(memberTransactionRepository, atLeastOnce()).getByMtIdAndTransactionDate(result.getEmId().getEmId(), result.getTransactionDate());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member transaction by id and date."));
        }
    }
}