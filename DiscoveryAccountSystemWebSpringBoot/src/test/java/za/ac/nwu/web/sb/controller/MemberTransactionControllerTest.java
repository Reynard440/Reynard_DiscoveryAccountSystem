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
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberTransactionControllerTest {

    private static final String URL = "/discovery-account-system/mvc";
    private static final String MEMBER_TRANSACTION_CONTROLLER_URL = URL + "/v1/c2";

    @Mock
    private ViewMemberTransactionService memberTransactionService;

    @Mock
    private NewTransactionService newTransactionService;

    @InjectMocks
    private MemberTransactionController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should add a new transaction.")
    public void shouldAddNewTransaction() {
        try {
            String exExpected = "{\"mtId\":1,\"transactionDate\":\"2012-01-01\",\"description\":\"Deposit\",\"amount\":2000,\"emId\":1}";
            String exActual = "{\"confirmation\":true,\"cargo\":[" +
                    "{\"mtId\":1,\"transactionDate\":\"2012-01-01\",\"description\":\"Deposit\",\"amount\":2000,\"emId\":1}";
            MemberTransactionDto memberTransactionDto = new MemberTransactionDto(1,LocalDate.parse("2012-01-01"),"Deposit",2000,1);

            when(newTransactionService.addTransactionDto(eq(memberTransactionDto))).then(returnsFirstArg());

            MvcResult mvcResult = mockMvc.perform(post(String.format("%s/%s", MEMBER_TRANSACTION_CONTROLLER_URL, "newTransaction"))
                            .servletPath(URL)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(exExpected)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            verify(newTransactionService, times(1)).addTransactionDto(eq(memberTransactionDto));
            assertEquals(exActual, mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred: ", e);
        }
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should get a transaction by EmId.")
    public void shouldGetMemberTransactionByID() {
        try {
            String expectedResponse = "{\"confirmation\":true,\"cargo\":{\"description\":\"Deposit\",\"transactionDate\":\"2021-08-31\",\"amount\":2000,\"emId\":1,\"mtId\":1}}";
            List<MemberTransactionDto> memberTransactionDtoList = new ArrayList<>();
            MemberTransactionDto memberTransactionDto = new MemberTransactionDto("Deposit",LocalDate.parse("2021-08-31"),2000, 1, 1);
            memberTransactionDtoList.add(memberTransactionDto);
            when(memberTransactionService.getMemberTransactionID(1)).thenReturn(memberTransactionDtoList);

            MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_TRANSACTION_CONTROLLER_URL, "getMemberTransactionByID/1")))
                            .servletPath(URL)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
            verify(memberTransactionService, times(1)).getMemberTransactionID(1);
            assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the member transaction by id: ", e);
        }
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should get a transaction by mem id and date.")
    public void shouldGetTransactionByIDAndDate() {
        try {
            String expectedResponse = "{\"confirmation\":true,\"cargo\":{\"description\":\"Deposit\",\"transactionDate\":\"2021-08-31\",\"amount\":2000,\"emId\":1,\"mtId\":1}}";
            List<MemberTransactionDto> memberTransactionDtos = new ArrayList<>();
            MemberTransactionDto memberTransactionDto = new MemberTransactionDto("Deposit",LocalDate.parse("2021-08-31"),2000, 1, 1);
            memberTransactionDtos.add(memberTransactionDto);
            when(memberTransactionService.getTransactionByIdAndDate(1, LocalDate.parse("2021-08-31"))).thenReturn(memberTransactionDtos);

            MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_TRANSACTION_CONTROLLER_URL, "getTransactionByIdAndDate/1/2021-08-31")))
                            .servletPath(URL)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
            verify(memberTransactionService, times(1)).getTransactionByIdAndDate(1, LocalDate.parse("2021-08-31"));
            assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the member transaction by id and date: ", e);
        }
    }
}