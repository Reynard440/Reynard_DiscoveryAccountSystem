package za.ac.nwu.translator.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeMediumTranslatorImplTest {
    @Mock //creates a mock of ExchangeMediumRepository (not the actual ExchangeMediumRepository)
    private ExchangeMediumRepository exchangeMediumRepository;

    @InjectMocks //repository is now mocked
    private ExchangeMediumTranslatorImpl exchangeMediumTranslator;

    Exchange_Medium result;

    @Before
    public void setUp() throws Exception {
        when(exchangeMediumRepository.save(any(Exchange_Medium.class))).then(returnsFirstArg());
        result = exchangeMediumRepository.save(new Exchange_Medium());
    }

    @After
    public void tearDown() throws Exception {
        result = null;
    }

    @Test
    @DisplayName("Should get the exchange medium by mem id.")
    public void ShouldGetExchangeMediumByMemID() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.getExchangeMediumByMemID(1);
            verify(exchangeMediumRepository, atLeastOnce()).getByMemID(1);
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving an exchange medium by its id."));
        }
    }

    @Transactional
    @Test
    @DisplayName("Should increase the balance of a exchange medium.")
    public void shouldIncreaseExchangeMediumTotal() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.increaseExchangeMediumTotal(1, 10.0);
            verify(exchangeMediumRepository, atLeastOnce()).increaseBalance(10, 1);
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while increasing the balance of an exchange medium."));
        }
    }

    @Transactional
    @Test
    @DisplayName("Should decrease the balance of a exchange medium.")
    public void shouldDecreaseExchangeMediumTotal() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.decreaseExchangeMediumTotal(1, 10.0);
            verify(exchangeMediumRepository, atLeastOnce()).decreaseBalance(10, 1);
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while decreasing the balance of an exchange medium."));
        }
    }

    @Transactional
    @Test
    @DisplayName("Should configure from one exchange medium to another.")
    public void shouldConfigureExchangeMedium() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.configureExchangeMedium("Miles", "Dollars", 0.14, 1, 1, "US Currency");
            verify(exchangeMediumRepository, atLeastOnce()).switchExchangeMedium("Miles", "Dollars", 0.14, 1, 1, "US Currency");
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while configuring the exchange medium."));
        }
    }

    @Test
    @DisplayName("Should check if a exchange medium exists.")
    public void shouldCheckTypeExists() {
        assertNotNull(result);
        exchangeMediumTranslator.checkTypeExists(1, "Miles");
        verify(exchangeMediumRepository, atLeastOnce()).existsByTypeAndMemID_Id("Miles", 1);
    }

    @Transactional
    @Test
    @DisplayName("Should add a new exchange medium.")
    public void shouldAddNewExchangeMedium() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.newExchangeMedium(result);
            verify(exchangeMediumRepository, atLeastOnce()).save(any(Exchange_Medium.class));
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while creating an exchange medium."));
        }
    }

    @Test
    @DisplayName("Should get the balance of a exchange medium by mem id and date.")
    public void shouldGetExchangeMediumCurrentByTypeAndID() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.getExchangeMediumCurrentByTypeAndID("Miles", 1);
            verify(exchangeMediumRepository, atLeastOnce()).getExchangeMediumCurrentByTypeAndID("Miles", 1);
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the current balance of an exchange medium."));
        }
    }

    @Test
    @DisplayName("Should get the exchange medium by EmId.")
    public void shouldGetExchangeMediumByEmID() {
        try {
            assertNotNull(result);
            exchangeMediumTranslator.getExchangeMediumByEmID(1);
            verify(exchangeMediumRepository, atLeastOnce()).getByEmId(1);
        } catch (SQLException e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the current balance of an exchange medium."));
        }
    }
}