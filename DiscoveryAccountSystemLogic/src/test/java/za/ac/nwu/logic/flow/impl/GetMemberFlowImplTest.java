package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.translator.MemberTranslator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetMemberFlowImplTest {
    @Mock //creates a mock of MemberTranslator (not the actual MemberTranslator)
    private MemberTranslator memberTranslator;

    @InjectMocks //translator is now mocked
    private GetMemberFlowImpl getMemberFlowService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMembers() throws Exception {
        try {
            MemberDto memberDto = new MemberDto();
            assertNotNull(memberDto);
            memberTranslator.getAllMembers();
            verify(memberTranslator, atLeastOnce()).getAllMembers();
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving all members."));
        }
    }

    @Test
    public void getMemberByEmail() throws Exception {
        try {
            MemberDto memberDto = new MemberDto();
            assertNotNull(memberDto);
            memberTranslator.getMemberByEmail(memberDto.getEmail());
//          assertEquals("reynardengels@gmail.com", getMemberFlowService.getMemberByEmail(memberDto.getEmail()));
            verify(memberTranslator, atLeastOnce()).getMemberByEmail(memberDto.getEmail());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member by their email address."));
        }
    }

    @Test
    public void getMemberById() throws Exception {
        try {
            MemberDto memberDto = new MemberDto();
            assertNotNull(memberDto);
            memberTranslator.getOneMember(memberDto.getMemId());
            verify(memberTranslator, atLeastOnce()).getOneMember(memberDto.getMemId());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving a member by their id."));
        }
    }
}