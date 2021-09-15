package za.ac.nwu.repo.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.config.RepositoryTestConfig;

import java.time.LocalDate;

import static com.sun.javaws.JnlpxArgs.verify;
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

    @Test
    public void shouldGetByTypeAndEmId() throws Exception {
        try {
            Exchange_Medium exchange_medium = exchangeMediumRepository.getByTypeAndEmId("Miles", 1);
            assertNotNull(exchange_medium);
            assertEquals("Miles", exchange_medium.getType());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred", e);
            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    public void shouldGetExchangeMediumCurrentByTypeAndID() throws Exception{
        try {
            Exchange_Medium exchange_medium = exchangeMediumRepository.getExchangeMediumCurrentByTypeAndID("Plays", 4);
            assertNotNull(exchange_medium);
            assertEquals(LocalDate.parse("2021-05-06"), exchange_medium.getDate());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred", e);
            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
        }
    }

    @Test
    public void shouldIncreaseBalance() throws Exception {
//        try {
//            exchangeMediumRepository.increaseBalance(100.00, 1);
//            verify()
//        } catch (Exception e) {
//            throw new RuntimeException("An error occurred", e);
//            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
//        }
    }

    @Test
    public void shouldDecreaseBalance() throws Exception {
//        try {
//            exchangeMediumRepository.increaseBalance(100.00, 1);
//            verify()
//        } catch (Exception e) {
//            throw new RuntimeException("An error occurred", e);
//            //assertTrue(e.getMessage().equalsIgnoreCase("An error occurred during the creation of a exchange medium."));
//        }
    }
}