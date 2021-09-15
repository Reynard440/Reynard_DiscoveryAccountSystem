package za.ac.nwu.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.config.RepositoryTestConfig;

import static org.junit.Assert.*;

import java.sql.SQLException;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetById() throws Exception {
        try {
            Member member = memberRepository.getById(1);
            assertNotNull(member);
            assertEquals("1", member.getId().toString());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred", e);
            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    public void shouldGetByEmail() throws Exception {
        try {
            Member member = memberRepository.getByEmail("reynardengels@gmail.com");
            assertNotNull(member);
            assertEquals("Reynard", member.getFirstName());
            assertEquals("reynardengels@gmail.com", member.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred", e);
            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    public void shouldDeleteById() throws Exception {
    }
}
