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
import za.ac.nwu.repo.config.RepositoryTestConfig;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
//        Member member = memberRepository.getById(1);
//        assertNotNull(member);
//        assertEquals(1, java.util.Optional.ofNullable(member.getId()));
    }

    @Test
    public void getByEmail() {
//        Member member = memberRepository.getByEmail("reynardengels@gmail.com");
//        assertNotNull(member);
//        assertEquals("reynardengels@gmail.com", member.getEmail());
    }

    @Test
    public void deleteById() {
    }
}
