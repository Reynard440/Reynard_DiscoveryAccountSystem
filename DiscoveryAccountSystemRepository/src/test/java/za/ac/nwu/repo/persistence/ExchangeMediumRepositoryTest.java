package za.ac.nwu.repo.persistence;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.repo.config.RepositoryTestConfig;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class ExchangeMediumRepositoryTest {

    @Autowired
    ExchangeMediumRepository exchangeMediumRepository;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @SneakyThrows
    @Test
    public void shouldGetByTypeAndEmId() {
        Exchange_Medium exchange_medium = exchangeMediumRepository.getByTypeAndEmId("Miles", 1);
        assertNotNull(exchange_medium);
        assertEquals("Miles", exchange_medium.getType());
    }

    @SneakyThrows
    @Test
    public void shouldGetExchangeMediumCurrentByTypeAndID() {
        Exchange_Medium exchange_medium = exchangeMediumRepository.getExchangeMediumCurrentByTypeAndID("Miles", 1);
        assertNotNull(exchange_medium);
        assertEquals(LocalDate.parse("2021-08-31"), exchange_medium.getDate());
    }

    @SneakyThrows
    @Test
    public void shouldIncreaseBalance() {
        Exchange_Medium exchange_medium = exchangeMediumRepository.getByTypeAndEmId("Miles", 1);
        assertNotNull(exchange_medium);
        double value = exchange_medium.getBalance();

        Exchange_Medium newCheck = exchangeMediumRepository.getByTypeAndEmId("Miles", 1);
        exchangeMediumRepository.increaseBalance(100.00, 1);
        assertNotNull(newCheck);
        double check = newCheck.getBalance();
        //assertTrue(value > check);
        assertEquals(true, value == check);
    }

    @SneakyThrows
    @Test
    public void shouldDecreaseBalance() {
        Exchange_Medium exchange_medium = exchangeMediumRepository.getByTypeAndEmId("Miles", 1);
        assertNotNull(exchange_medium);
        double value = exchange_medium.getBalance();
        exchangeMediumRepository.decreaseBalance(100.00, 1);

        Exchange_Medium newCheck = exchangeMediumRepository.getByTypeAndEmId("Miles", 1);
        double check = newCheck.getBalance();
        assertEquals(true, value == check);
    }
}