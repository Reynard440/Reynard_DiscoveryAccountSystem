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
        result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto(
                1,
                "Miles",
                "Discovery Miles",
                100.0,
                LocalDate.now(),
                new MemberDto(1,
                        "reynardengels@gmail.com",
                        "0723949955",
                        "Reynard",
                        "Engels")
        ));
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    public void shouldGetExchangeMediumByMemID() {
        try {
            assertNotNull(result);
            assertEquals("Miles", result.getType());
            viewExchangeMediumService.getExchangeMediumByMemID(1);
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumByMemID(1);
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

    @Test
    public void shouldGetExchangeMediumByEmID() {
        try {
            assertNotNull(result);
            viewExchangeMediumService.getExchangeMediumByEmID(1);
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumByEmID(1);
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the exchange medium via its id."));
        }
    }
}