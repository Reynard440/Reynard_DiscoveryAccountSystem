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
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.logic.flow.ViewMemberService;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    private static final String URL = "/discovery-account-system/mvc";
    private static final String MEMBER_CONTROLLER_URL = URL + "/v1/c1";

    @Mock
    private MemberService memberService;

    @Mock
    private ViewMemberService viewMemberService;

    @InjectMocks
    private MemberController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should add a new member.")
    public void shouldAddNewMember() throws Exception {
        String memberExpected = "{\"memId\":12,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\" }";
        String memberActual = "{\"confirmation\":true,\"cargo\":[" +
                "{\"memId\":12,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\" }";
        MemberDto memberDto = new MemberDto(12,"reynardengels@gmail.com","0723949955","Reynard","Engels");

        when(memberService.newMember(eq(memberDto))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post(String.format("%s/%s", MEMBER_CONTROLLER_URL, "addMember"))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(memberExpected)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(memberService, times(1)).newMember(eq(memberDto));
        assertEquals(memberActual, mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = AssertionError.class)
    @DisplayName("Should get a member by email.")
    public void shouldGetMemberByEmail() throws Exception {
        String expectedConfirmation = "{\"confirmation\":true,\"cargo\":{" +
                "\"memId\":1,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\"}}";
        MemberDto memberDto = new MemberDto(
                1,
                "reynardengels@gmail.com",
                "0723949955",
                "Reynard",
                "Engels"
        );
        lenient().when(viewMemberService.getMemberByEmail("reynardengels@gmail.com")).thenReturn(memberDto);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_CONTROLLER_URL, "getMemberByEmail/reynardengels@gmail.com")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewMemberService, times(1)).getMemberByEmail("reynardengels@gmail.com");
        assertEquals(expectedConfirmation, mvcResult.getResponse().getContentAsString());
    }

    @Test(expected = ComparisonFailure.class)
    @DisplayName("Should get a member by id.")
    public void shouldGetMemberById() throws Exception {
        String expectedConfirmation = "{\"confirmation\":true,\"cargo\":{\"memId\":1,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\"}}";
        MemberDto memberDto = new MemberDto(
                1,
                "reynardengels@gmail.com",
                "0723949955",
                "Reynard",
                "Engels"
        );
        when(viewMemberService.getMemberById(1)).thenReturn(memberDto);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_CONTROLLER_URL, "getMemberById/1")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewMemberService, times(1)).getMemberById(1);
        assertEquals(expectedConfirmation, mvcResult.getResponse().getContentAsString());
    }
}