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
    public void shouldAddNewMember() throws Exception {
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
    public void shouldDeleteMember() throws Exception {
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
}