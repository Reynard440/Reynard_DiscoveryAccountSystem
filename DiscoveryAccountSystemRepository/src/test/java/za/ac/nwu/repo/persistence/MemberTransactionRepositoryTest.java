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
    public void getByMtId() {
//        Member_Transaction member_transaction = memberTransactionRepository.getByMtId(1);
//        assertNotNull(member_transaction);
//        assertEquals(java.util.Optional.of(1), member_transaction.getMtId());
    }

    @Test
    public void getByMtIdAndTransactionDate() {
    }
}