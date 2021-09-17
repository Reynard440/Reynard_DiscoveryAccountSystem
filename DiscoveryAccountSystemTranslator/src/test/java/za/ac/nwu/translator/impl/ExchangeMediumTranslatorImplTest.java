package za.ac.nwu.translator.impl;

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
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTranslator;

import java.time.LocalDate;

import static org.junit.Assert.*;
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
    public void ShouldGetExchangeMediumByEmID() {
        try {
            assertNotNull(result);
            exchangeMediumRepository.getByEM_ID(result.getEmId());
            verify(exchangeMediumRepository, atLeastOnce()).getByEM_ID(result.getEmId());
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving an exchange medium by its id."));
        }
    }

    @Test
    public void shouldIncreaseExchangeMediumTotal() {
        try {
            assertNotNull(result);
            exchangeMediumRepository.increaseBalance(10, 1);
            verify(exchangeMediumRepository, atLeastOnce()).increaseBalance(10, 1);
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while increasing the balance of an exchange medium."));
        }
    }

    @Test
    public void shouldDecreaseExchangeMediumTotal() {
        try {
            assertNotNull(result);
            exchangeMediumRepository.decreaseBalance(10, 1);
            verify(exchangeMediumRepository, atLeastOnce()).decreaseBalance(10, 1);
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while decreasing the balance of an exchange medium."));
        }
    }

    @Test
    public void shouldCheckTypeExists() {
        try {
            assertNotNull(result);
            exchangeMediumRepository.checkTypeExist(1, "Miles");
            verify(exchangeMediumRepository, atLeastOnce()).checkTypeExist(1, "Miles");
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while checking whether or not a exchange medium exists."));
        }
    }

    @Test
    public void shouldAddNewExchangeMedium() {
        try {
            assertNotNull(result);
            verify(exchangeMediumRepository, atLeastOnce()).save(any(Exchange_Medium.class));
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while creating an exchange medium."));
        }
    }

    @Test
    public void shouldGetExchangeMediumCurrentByTypeAndID() {
        try {
            assertNotNull(result);
            exchangeMediumRepository.getExchangeMediumCurrentByTypeAndID("Miles", 1);
            verify(exchangeMediumRepository, atLeastOnce()).getExchangeMediumCurrentByTypeAndID("Miles", 1);
        } catch (Exception e) {
            assertTrue(e.getMessage().equalsIgnoreCase("An error occurred while retrieving the current balance of an exchange medium."));
        }
    }
}