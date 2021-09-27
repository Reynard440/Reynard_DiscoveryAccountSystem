package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewExchangeMediumServiceImplTest {
    @Mock
    private ExchangeMediumTranslator exchangeMediumTranslator;

    @Mock
    private MemberTranslator memberTranslator;

    @InjectMocks //translator is now mocked
    private ExchangeMediumServiceImpl exchangeMediumService;

    @InjectMocks //translator is now mocked
    private ViewExchangeMediumServiceImpl viewExchangeMediumService;

    ExchangeMediumDto result;

    @Before
    public void setUp() throws Exception {
        lenient().when(exchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
        lenient().when(memberTranslator.newMember(any(Member.class))).then(returnsFirstArg());
        result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto());
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    public void shouldGetExchangeMediumByEmID() {
        try {
            assertNotNull(result);
            assertEquals("Miles", result.getType());
            viewExchangeMediumService.getExchangeMediumByEmID(result.getExchangeMediumID());
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumByEmID(result.getExchangeMediumID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving an exchange medium by its id."));
        }
    }

    @Test
    public void shouldGetExchangeMediumCurrentByTypeAndID() {
        try {
            assertNotNull(result);
            viewExchangeMediumService.getExchangeMediumCurrentByTypeAndID(result.getType(), result.getExchangeMediumID());
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumCurrentByTypeAndID(result.getType(), result.getExchangeMediumID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the balance of an exchange medium via its type and id."));
        }
    }
}