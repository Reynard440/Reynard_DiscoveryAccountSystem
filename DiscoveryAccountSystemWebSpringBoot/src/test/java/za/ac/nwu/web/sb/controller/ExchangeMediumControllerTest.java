package za.ac.nwu.web.sb.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.logic.flow.ViewMemberService;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private ExchangeMediumService exchangeMediumServicee;

    @InjectMocks
    private ExchangeMediumController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldAddNewExchangeMedium() throws Exception {
        String exExpected = "{\"exchangeMediumID\":1,\"type\":\"Miles\",\"description\":\"This is a new Discovery currency type that keeps track of all your MILES\",\"balance\":40,\"date\":\"2021-08-05\",\"memID\":1}";
        String exActual = "{\"confirmation\":true,\"cargo\":[" +
                "{\"exchangeMediumID\":1,\"type\":\"Miles\",\"description\":\"This is a new Discovery currency type that keeps track of all your MILES\",\"balance\":\"40\",\"date\":\"2021-08-05\",\"memID\":1 }";
        ExchangeMediumDto exchangeMediumDto = new ExchangeMediumDto(1,"Miles","This is a new Discovery currency type that keeps track of all your MILES",40, LocalDate.parse("2021-08-05"), 1);

        when(exchangeMediumServicee.newExchangeMedium(eq(exchangeMediumDto))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post(String.format("%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "new"))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(exExpected)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(exchangeMediumServicee, times(1)).newExchangeMedium(eq(exchangeMediumDto));
        assertEquals(exActual, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetExchangeMediumById() throws Exception {
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

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s/%s", EXCHANGE_MEDIUM_CONTROLLER_URL, "getExchangeMediumById", "1")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewExchangeMediumService, times(1)).getExchangeMediumByEmID(1);
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test
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
        verify(viewExchangeMediumService, times(1)).getExchangeMediumCurrentByTypeAndID("Miles1", 1);
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }
}