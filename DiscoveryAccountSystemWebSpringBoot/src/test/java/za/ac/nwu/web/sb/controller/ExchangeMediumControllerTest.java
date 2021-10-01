package za.ac.nwu.web.sb.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeMediumControllerTest {
    private static final String URL = "/discovery-account-system/mvc";
    private static final String EXCHANGE_MEDIUM_CONTROLLER_URL = URL + "/v1/c3";

    @Mock
    private ViewExchangeMediumService viewExchangeMediumService;

    @Mock
    private ExchangeMediumService exchangeMediumService;

    @InjectMocks
    private ExchangeMediumController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should add a new exchange medium.")
    public void shouldAddNewExchangeMedium() throws Exception {
        String exExpected = "{\"exchangeMediumID\":1,\"type\":\"Miles\",\"description\":\"This is a new Discovery currency type that keeps track of all your MILES\",\"balance\":40,\"date\":\"2021-08-05\",\"memID\":1}";
        String exActual = "{\"confirmation\":true,\"cargo\":[" +
                "{\"exchangeMediumID\":1,\"type\":\"Miles\",\"description\":\"This is a new Discovery currency type that keeps track of all your MILES\",\"balance\":\"40\",\"date\":\"2021-08-05\",\"memID\":1 }";
        ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto(1,"Miles","This is a new Discovery currency type that keeps track of all your MILES",40, LocalDate.parse("2021-08-05"), 1);

        when(exchangeMediumService.newExchangeMedium(eq(exchangeMediumDto))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post(String.format("%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "new"))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(exExpected)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(exchangeMediumService, times(1)).newExchangeMedium(eq(exchangeMediumDto));
        assertEquals(exActual, mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should get an exchange medium by EmId.")
    public void getExchangeMediumByEmId() throws Exception {
        String expectedResponse = "{\"confirmation\":true,\"cargo\":{" +
                "\"exchangeMediumID\":1," +
                "\"type\":\"Miles\"," +
                "\"date\":\"[2021-07-12]\"," +
                "\"description\":\"Discovery currency\"," +
                "\"balance\":2300," +
                "\"memID\":{\"memId\":1,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"lastName\":\"Engels\",\"firstName\":\"Reynard\"}}}";

        MemberDto memberDto = new MemberDto(
                1,
                "reynardengels@gmail.com",
                "0723949955",
                "Reynard",
                "Engels"
        );

        ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto(1,"Miles","Discovery currency",2300, LocalDate.parse("2021-07-12"), memberDto);

        when(viewExchangeMediumService.getExchangeMediumByEmID(1)).thenReturn(exchangeMediumDto);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "getExchangeMediumByEmId", "1")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewExchangeMediumService, times(1)).getExchangeMediumByEmID(1);
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should get an exchange medium by mem id.")
    public void shouldGetExchangeMediumByMemId() throws Exception {
        String expectedResponse = "{\"confirmation\":true,\"cargo\":{" +
                "\"exchangeMediumID\":1," +
                "\"type\":\"Miles\"," +
                "\"date\":\"[2021-07-12]\"," +
                "\"description\":\"Discovery currency\"," +
                "\"balance\":2300," +
                "\"memID\":{\"memId\":1,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"lastName\":\"Engels\",\"firstName\":\"Reynard\"}}}";

        MemberDto memberDto = new MemberDto(
                1,
                "reynardengels@gmail.com",
                "0723949955",
                "Reynard",
                "Engels"
        );
        List<ExchangeMediumDto> exchangeMediumDtoList = new ArrayList<>();
        ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto(1,"Miles","Discovery currency",2300, LocalDate.parse("2021-07-12"), memberDto);
        exchangeMediumDtoList.add(exchangeMediumDto);

        when(viewExchangeMediumService.getExchangeMediumByMemID(1)).thenReturn(exchangeMediumDtoList);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "getExchangeMediumById", "1")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewExchangeMediumService, times(1)).getExchangeMediumByMemID(1);
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should get the balance of an exchange medium.")
    public void shouldGetExchangeMediumCurrentByTypeAndID() throws Exception {
        String expectedResponse = "{\"confirmation\":true,\"cargo\":{" +
                "\"exchangeMediumID\":1," +
                "\"type\":\"Miles\"," +
                "\"date\":\"[2021-07-12]\"," +
                "\"description\":\"Discovery currency\"," +
                "\"balance\":2300," +
                "\"memID\":{\"memId\":1,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"lastName\":\"Engels\",\"firstName\":\"Reynard\"}}}";

        MemberDto memberDto = new MemberDto(
                1,
                "reynardengels@gmail.com",
                "0723949955",
                "Reynard",
                "Engels"
        );
        ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto(1,"Miles","Discovery currency",2300, LocalDate.parse("2021-07-12"), memberDto);

        when(viewExchangeMediumService.getExchangeMediumCurrentByTypeAndID("Miles", 1)).thenReturn(exchangeMediumDto);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s/%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "getExchangeMediumCurrentByTypeAndID", "Miles", "1")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewExchangeMediumService, times(1)).getExchangeMediumCurrentByTypeAndID("Miles", 1);
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should configure from one exchange medium to a new one.")
    public void shouldConfigureExchangeMedium() throws Exception {
        String expectedResponse = "{\"confirmation\":true,\"cargo\":{" +
                "\"exchangeMediumID\":1," +
                "\"type\":\"Miles\"," +
                "\"date\":\"2021-07-12\"," +
                "\"description\":\"Discovery currency\"," +
                "\"memID\":{\"memId\":1,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\"}," +
                "\"balance\":919.1}}";

        MvcResult mvcResult = mockMvc.perform(put((String.format("%s/%s/%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "switchToExchangeMedium", "Dollars",1)))
                        .param("newType", "Miles")
                        .param("adjust", "0.091")
                        .param("member", "1")
                        .param("description", "Discovery Miles")
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(exchangeMediumService, times(1)).configureExchangeMedium("Dollars", "Miles", 0.091, 1, 1, "Discovery Miles");
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }
}