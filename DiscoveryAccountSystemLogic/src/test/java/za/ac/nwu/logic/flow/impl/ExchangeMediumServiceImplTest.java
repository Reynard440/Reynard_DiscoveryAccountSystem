package za.ac.nwu.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeMediumServiceImplTest {
    @Mock //creates a mock of ExchangeMediumTranslator (not the actual ExchangeMediumTranslator)
    private ExchangeMediumTranslator serviceExchangeMediumTranslator;

    @Mock
    private MemberTranslator memberTranslator;

    @InjectMocks //translator is now mocked
    private ExchangeMediumServiceImpl exchangeMediumService;

    ExchangeMediumDto result;

    @Before
    public void setUp() throws SQLException {
        lenient().when(serviceExchangeMediumTranslator.newExchangeMedium(any(Exchange_Medium.class))).then(returnsFirstArg()); // if get anything of MemberDto
        lenient().when(memberTranslator.newMember(any(Member.class))).then(returnsFirstArg());
        result = exchangeMediumService.newExchangeMedium(new ExchangeMediumDto(
                1,
                "Miles",
                "Discovery Miles",
                40,
                LocalDate.now(),
                new MemberDto(1)
        ));
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    @DisplayName("Should add a exchange medium.")
    public void shouldAddNewExchangeMedium() {
        try {
            assertNotNull(result);
            assertEquals(LocalDate.now(), result.getDate());
            assertFalse(result.getType().isEmpty());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).newExchangeMedium(any(Exchange_Medium.class));
        } catch(Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    @DisplayName("Should increase balance of exchange medium.")
    public void shouldIncreaseExchangeMediumTotal() {
        try {
            assertNotNull(result);
            assertEquals("Discovery Miles", result.getDescription());
            assertEquals("Miles", result.getType());
            assertEquals(LocalDate.now(), result.getDate());
            exchangeMediumService.increaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).increaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while increasing a specific exchange medium's balance."));
        }
    }

    @Test
    @DisplayName("Should decrease balance of exchange medium.")
    public void shouldDecreaseExchangeMediumTotal() {
        try {
            assertNotNull(result);
            assertEquals("Discovery Miles", result.getDescription());
            assertEquals("Miles", result.getType());
            assertEquals(LocalDate.now(), result.getDate());
            exchangeMediumService.decreaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).decreaseExchangeMediumTotal(result.getExchangeMediumID(), result.getBalance());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while decreasing a specific exchange medium's balance."));
        }
    }

    @Test
    @DisplayName("Should check if the type exist")
    public void shouldCheckTypeExist() {
        try {
            assertNotNull(result);
            assertEquals("Discovery Miles", result.getDescription());
            assertEquals("Miles", result.getType());
            exchangeMediumService.checkTypeExist(result.getExchangeMediumID(), result.getType());
            verify(serviceExchangeMediumTranslator, atLeastOnce()).checkTypeExists(result.getExchangeMediumID(), result.getType());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while checking whether or not a specific exchange medium exists."));
        }
    }

    @Test
    @DisplayName("Should configure to new exchange medium.")
    public void shouldConfigureExchangeMedium() {
        try {
            assertNotNull(result);
            exchangeMediumService.configureExchangeMedium(result.getType(), "Dollars", 0.14, 1, 1, "US Currency");
            verify(serviceExchangeMediumTranslator, atLeastOnce()).configureExchangeMedium(result.getType(), "Dollars", 0.14, 1, 1, "US Currency");
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while configuring to a specific exchange medium."));
        }
    }
}