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
import za.ac.nwu.translator.MemberTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeMediumServiceImplTest {
    @Mock //creates a mock of ExchangeMediumTranslator (not the actual ExchangeMediumTranslator)
    private ExchangeMediumTranslator serviceExchangeMediumTranslator;

    @InjectMocks //translator is now mocked
    private ExchangeMediumServiceImpl exchangeMediumService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newExchangeMedium() {
        try {
            when(serviceExchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
            ExchangeMediumDto result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto());
            assertNotNull(result);
            assertEquals(LocalDate.now(), result.getDate());
            assertFalse(result.getType().isEmpty());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).newExchangeMedium(any(Exchange_Medium.class));
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    public void increaseExchangeMediumTotal() {
        try {
            when(serviceExchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
            ExchangeMediumDto result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto());
            assertNotNull(result);
            assertEquals("Discovery currency", result.getDescription());
            assertEquals("Miles", result.getType());
            assertEquals(LocalDate.now(), result.getDate());
            serviceExchangeMediumTranslator.increaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).increaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while increasing a specific exchange medium's balance."));
        }
    }

    @Test
    public void decreaseExchangeMediumTotal() {
        try {
            when(serviceExchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
            ExchangeMediumDto result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto());
            assertNotNull(result);
            assertEquals("Discovery currency", result.getDescription());
            assertEquals("Miles", result.getType());
            assertEquals(LocalDate.now(), result.getDate());
            serviceExchangeMediumTranslator.decreaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).decreaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while decreasing a specific exchange medium's balance."));
        }
    }

    @Test
    public void checkTypeExist() {
        try {
            when(serviceExchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
            ExchangeMediumDto result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto());
            assertNotNull(result);
            assertEquals("Discovery currency", result.getDescription());
            assertEquals("Miles", result.getType());
            serviceExchangeMediumTranslator.checkTypeExists(result.getExchangeMediumID(), result.getType());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).checkTypeExists(result.getExchangeMediumID(), result.getType());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while checking whether or not a specific exchange medium exists."));
        }
    }
}