package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.translator.MemberTranslator;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceImplTest {
    @Mock //creates a mock of MemberTranslator (not the actual MemberTranslator)
    private MemberTranslator serviceTranslator;

    @InjectMocks //translator is now mocked
    private MemberServiceImpl memberService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newMember() throws Exception {
        try {
            when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg()); // if get anything of MemberDto
            MemberDto result = memberService.newMember(new MemberDto());
            assertNotNull(result);
            assertTrue(result.getEmail().contains("@gmail.com"));
            assertEquals(10, result.getPhoneNumber().length());
            assertEquals(0, result.getPhoneNumber().indexOf("0"));
            verify(serviceTranslator, atLeastOnce()).newMember(any(Member.class));
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a member."));
        }
    }

    @Test
    public void deleteMember() throws Exception {
        try {
            when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg());
            MemberDto result = memberService.newMember(new MemberDto());
            assertNotNull(result);
            serviceTranslator.deleteMember(result.getMemId());
            assertEquals("Reynard", result.getFirstName());
            verify(serviceTranslator, atLeastOnce()).deleteMember(result.getMemId());
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the deletion of the member."));
        }
    }

    @Test
    public void getMembers() throws Exception {
        try {
            when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg());
            MemberDto result = memberService.newMember(new MemberDto());
            assertNotNull(result);
            serviceTranslator.getAllMembers();
            verify(serviceTranslator, atLeastOnce()).getAllMembers();
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving all members."));
        }
    }

    @Test
    public void getMemberByEmail() throws Exception {
        try {
            when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg());
            MemberDto result = memberService.newMember(new MemberDto());
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
    public void getMemberById() throws Exception {
        try {
            when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg());
            MemberDto result = memberService.newMember(new MemberDto());
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