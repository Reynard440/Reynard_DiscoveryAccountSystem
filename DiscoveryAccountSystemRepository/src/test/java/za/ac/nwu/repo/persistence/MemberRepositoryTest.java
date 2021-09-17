package za.ac.nwu.repo.persistence;

import lombok.SneakyThrows;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @SneakyThrows
    @Test
    public void shouldGetById() {
        Member member = memberRepository.getById(1);
        assertNotNull(member);
        assertEquals("Reynard", member.getFirstName());
        assertEquals("0723949955", member.getPhoneNumber());
    }

    @SneakyThrows
    @Test
    public void shouldGetByEmail() {
        Member member = memberRepository.getByEmail("reynardengels@gmail.com");
        assertNotNull(member);
        assertEquals("Reynard", member.getFirstName());
        assertEquals("reynardengels@gmail.com", member.getEmail());
    }
}
