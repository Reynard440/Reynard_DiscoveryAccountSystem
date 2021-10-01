package za.ac.nwu.translator.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.persistence.MemberRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberTranslatorImplTest {
    @Mock //creates a mock of MemberRepository (not the actual MemberRepository)
    private MemberRepository memberRepository;

    @InjectMocks //repository is now mocked
    private MemberTranslatorImpl memberTranslator;

    Member result;

    @Before
    public void setUp() throws Exception {
        when(memberRepository.save(any(Member.class))).then(returnsFirstArg());
        result = memberRepository.save(new Member());
    }

    @After
    public void tearDown() throws Exception {
        result = null;
    }

    @Test
    @DisplayName("Should get a member by id.")
    public void shouldGetOneMember() {
        try {
            assertNotNull(result);
            memberTranslator.getOneMember(result.getId());
            verify(memberRepository, atLeastOnce()).getById(result.getId());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member by their id."));
        }
    }

    @Transactional
    @Test
    @DisplayName("Should add a new member.")
    public void shouldAddNewMember() {
        try {
            assertNotNull(result);
            memberTranslator.newMember(result);
            verify(memberRepository, atLeastOnce()).save(any(Member.class));
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while creating a member."));
        }
    }

    @Test
    @DisplayName("Should get a member by email.")
    public void shouldGetMemberByEmail() {
        try {
            assertNotNull(result);
            memberTranslator.getMemberByEmail(result.getEmail());
            verify(memberRepository, atLeastOnce()).getByEmail(result.getEmail());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member by their email address."));
        }
    }
}