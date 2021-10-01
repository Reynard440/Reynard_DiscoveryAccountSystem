package za.ac.nwu.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.config.RepositoryTestConfig;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public void shouldGetEmId() {
        List<Member_Transaction> memberTransactionList = new ArrayList<>(memberTransactionRepository.getByEmId(1));
        assertNotNull(memberTransactionList);
    }

    @Test
    public void shouldGetByMtIdAndTransactionDate() {
        List<Member_Transaction> memberTransactionList = new ArrayList<>(memberTransactionRepository.getByMtIdAndTransactionDate(1, LocalDate.parse("2021-08-31")));
        assertNotNull(memberTransactionList);
    }
}