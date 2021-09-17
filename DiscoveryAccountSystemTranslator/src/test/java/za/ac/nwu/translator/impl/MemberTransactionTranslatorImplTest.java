package za.ac.nwu.translator.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberTransactionTranslatorImplTest {
    @Mock //creates a mock of MemberTransactionRepository (not the actual MemberTransactionRepository)
    private MemberTransactionRepository memberTransactionRepository;

    @InjectMocks //repository is now mocked
    private MemberTransactionTranslatorImpl memberTransactionTranslator;

    Member_Transaction result;

    @Before
    public void setUp() throws Exception {
        when(memberTransactionRepository.save(any(Member_Transaction.class))).then(returnsFirstArg());
        result = memberTransactionRepository.save(new Member_Transaction());
    }

    @After
    public void tearDown() throws Exception {
        result = null;
    }

    @Test
    public void shouldAddMemberTransaction() {
        try {
            assertNotNull(result);
            verify(memberTransactionRepository, atLeastOnce()).save(any(Member_Transaction.class));
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while creating a member transaction."));
        }
    }

    @Test
    public void getMemberTransactionID() {
        try {
            assertNotNull(result);
            memberTransactionRepository.getByMtId(result.getMtId());
            verify(memberTransactionRepository, atLeastOnce()).getByMtId(result.getMtId());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member transaction by id."));
        }
    }

    @Test
    public void getTransactionByIdAndDate() {
        try {
            assertNotNull(result);
            memberTransactionRepository.getByMtIdAndTransactionDate(result.getMtId(), result.getTransactionDate());
            verify(memberTransactionRepository, atLeastOnce()).getByMtIdAndTransactionDate(result.getMtId(), result.getTransactionDate());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member transaction by id and date."));
        }
    }
}