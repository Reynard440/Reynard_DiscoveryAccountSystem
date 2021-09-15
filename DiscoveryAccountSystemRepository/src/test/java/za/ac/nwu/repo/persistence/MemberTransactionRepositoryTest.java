package za.ac.nwu.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.config.RepositoryTestConfig;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class MemberTransactionRepositoryTest {
    @Autowired
    MemberTransactionRepository memberTransactionRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetByMtId() throws Exception {
        try {
            Member_Transaction memberTransaction = memberTransactionRepository.getByMtId(1);
            assertNotNull(memberTransaction);
            assertEquals(LocalDate.parse("2021-08-31"), memberTransaction.getTransactionDate());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred", e);
            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    public void shouldGetByMtIdAndTransactionDate() throws Exception {
        try {
            Member_Transaction memberTransaction = memberTransactionRepository.getByMtIdAndTransactionDate(1, LocalDate.parse("2021-08-31"));
            assertNotNull(memberTransaction);
            assertEquals("Deposit", memberTransaction.getDescription());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred", e);
            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }
}