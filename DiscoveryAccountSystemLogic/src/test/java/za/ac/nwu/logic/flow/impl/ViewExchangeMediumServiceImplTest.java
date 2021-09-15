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
import za.ac.nwu.translator.ExchangeMediumTranslator;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViewExchangeMediumServiceImplTest {
    @Mock
    private ExchangeMediumTranslator exchangeMediumTranslator;

    @InjectMocks
    private ViewExchangeMediumServiceImpl viewExchangeMediumService;

    @InjectMocks //translator is now mocked
    private ExchangeMediumServiceImpl exchangeMediumService;

    ExchangeMediumDto result;

    @Before
    public void setUp() throws Exception {
        when(exchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
        result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto());
    }

    @After
    public void tearDown() throws Exception {
        result = null;
    }

    @Test
    public void shouldGetExchangeMediumByEmID() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.getExchangeMediumByEmID(result.getExchangeMediumID());
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumByEmID(result.getExchangeMediumID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving an exchange medium by its id."));
        }
    }

    @Test
    public void shouldGetExchangeMediumCurrentByTypeAndID() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.getExchangeMediumCurrentByTypeAndID(result.getType(), result.getExchangeMediumID());
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumCurrentByTypeAndID(result.getType(), result.getExchangeMediumID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the balance of an exchange medium via its type and id."));
        }
    }

    @Test
    public void shouldGetAllExchangeMedium() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.getExchangeMediumDtos();
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumDtos();
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving all the exchange mediums."));
        }
    }
}