package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.translator.MemberTranslator;

import java.sql.SQLException;

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

    MemberDto result;

    @Before
    public void setUp() throws SQLException {
        when(serviceTranslator.newMember(any(Member.class))).then(returnsFirstArg()); // if get anything of MemberDto
        result = memberService.newMember(new MemberDto(
                1,
                "reynardengels@gmail.com",
                "0723949955",
                "Reynard",
                "Engels"
        ));
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    @DisplayName("Should add a member.")
    public void shouldAddNewMember() {
        try {
            assertNotNull(result);
            assertTrue(result.getEmail().contains("@gmail.com"));
            assertEquals(10, result.getPhoneNumber().length());
            assertEquals(0, result.getPhoneNumber().indexOf("0"));
            verify(serviceTranslator, atLeastOnce()).newMember(any(Member.class));
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a member."));
        }
    }
}