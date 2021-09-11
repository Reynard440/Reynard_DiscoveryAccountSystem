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
import za.ac.nwu.translator.ExchangeMediumTranslator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ViewExchangeMediumServiceImplTest {

    @Mock
    private ExchangeMediumTranslator exchangeMediumTranslator;

    @InjectMocks
    private ViewExchangeMediumServiceImpl viewExchangeMediumService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getExchangeMediumByEmID() throws Exception {
        try {
            ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto();
            assertNotNull(exchangeMediumDto);
            exchangeMediumTranslator.getExchangeMediumByEmID(exchangeMediumDto.getExchangeMediumID());
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumByEmID(exchangeMediumDto.getExchangeMediumID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving an exchange medium by its id."));
        }
    }

    @Test
    public void getExchangeMediumCurrentByTypeAndID() throws Exception {
        try {
            ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto();
            assertNotNull(exchangeMediumDto);
            exchangeMediumTranslator.getExchangeMediumCurrentByTypeAndID(exchangeMediumDto.getType(), exchangeMediumDto.getExchangeMediumID());
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumCurrentByTypeAndID(exchangeMediumDto.getType(), exchangeMediumDto.getExchangeMediumID());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the balance of an exchange medium via its type and id."));
        }
    }

    @Test
    public void getAllExchangeMedium() throws Exception {

        try {
            ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto();
            assertNotNull(exchangeMediumDto);
            exchangeMediumTranslator.getExchangeMediumDtos();
            verify(exchangeMediumTranslator, atLeastOnce()).getExchangeMediumDtos();
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving all the exchange mediums."));
        }
    }
}