package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.translator.MemberTranslator;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewMemberServiceImplTest {

    @Mock //creates a mock of MemberTranslator (not the actual MemberTranslator)
    private MemberTranslator serviceTranslator;


    @InjectMocks //translator is now mocked
    private MemberServiceImpl memberService;

    MemberDto result;

    @Before
    public void setUp() throws Exception {
        when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg());
        result = memberService.newMember(new MemberDto());
    }

    @After
    public void tearDown() throws Exception {
        result = null;
    }

    @Test
    public void shouldGetMembers() {
        try {
            assertNotNull(result);
            serviceTranslator.getAllMembers();
            verify(serviceTranslator, atLeastOnce()).getAllMembers();
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving all members."));
        }
    }

    @Test
    public void shouldGetMemberByEmail() {
        try {
            assertNotNull(result);
            System.out.println(result.getFirstName());
            serviceTranslator.getMemberByEmail(result.getEmail());
            assertEquals("reynardengels@gmail.com", result.getEmail());
            verify(serviceTranslator, atLeastOnce()).getMemberByEmail(result.getEmail());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member by their email address."));
        }
    }

    @Test
    public void shouldGetMemberById() {
        try {
            assertNotNull(result);
            serviceTranslator.getOneMember(result.getMemId());
            assertEquals("reynardengels@gmail.com", result.getEmail());
            assertEquals("0723949955", result.getPhoneNumber());
            verify(serviceTranslator, atLeastOnce()).getOneMember(result.getMemId());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member by their id."));
        }
    }
}