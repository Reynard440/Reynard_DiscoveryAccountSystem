package za.ac.nwu.web.sb.controller;

import lombok.SneakyThrows;
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
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.logic.flow.MemberServiceFlow;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    private static final String URL = "/discovery-account-system/mvc";
    private static final String MEMBER_CONTROLLER_URL = URL + "/v1/c1";

    @Mock
    private MemberServiceFlow memberServiceFlow;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @SneakyThrows
    @Test
    public void getAllMembers() throws Exception {
//        String expectedConfirmation = "{\"confirmation\": true, \"cargo\": [" +
//                "{\"firstName\": Reynard, \"lastName\": \"Engels\", \"email\": \"reynardengels@gmail.com\", \"phoneNumber\": \"0723949955\" }," +
//                "{\"firstName\": Lourenz, \"lastName\": \"Engels\", \"email\": \"lourenzengels@gmail.com\", \"phoneNumber\": \"0826516473\" }," +
//                "{\"firstName\": Rudi, \"lastName\": \"Dreyer\", \"email\": \"rudidreyer@gmail.com\", \"phoneNumber\": \"0795046299\"}";
//        List<MemberDto> memberDtos = new ArrayList<>();
//        memberDtos.add(new MemberDto("Reynard", "Engels", "reynardengels@gmail.com", "0723949955"));
//        memberDtos.add(new MemberDto("Lourenz", "Engels", "lourenzengels@gmail.com", "0826516473"));
//        memberDtos.add(new MemberDto("Rudi", "Dreyer", "rudidreyer@gmail.com", "0795046299"));
//
//        when(memberServiceFlow.getMembers()).thenReturn(memberDtos);
//
//        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_CONTROLLER_URL, "getAllMembers")))
//                        .servletPath(URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        verify(memberServiceFlow, times(1)).getMembers();
//        assertEquals(expectedConfirmation, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void newMember() throws Exception {
//        String memberExpected = "{\"email\": \"reynardengels@gmail.com\"firstName\": null,\"phoneNumber\": \"0723949955\",\"lastName\": \"Engels\" }";
//        String memberActual = "{\"confirmation\": true, \"cargo\": [" +
//                "{\"email\": \"reynardengels@gmail.com\"firstName\": null,\"phoneNumber\": \"0723949955\",\"lastName\": \"Engels\" }";
//        MemberDto memberDto = new MemberDto("null", "Engels", "reynardengels@gmail.com", "0723949955");
//
//        when(memberService.newMember(eq(memberDto))).then(returnsFirstArg());
//
//        MvcResult mvcResult = mockMvc.perform(post(MEMBER_CONTROLLER_URL)
//                        .servletPath(URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(memberExpected)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        verify(memberService, times(1)).newMember(eq(memberDto));
//        assertEquals(memberActual, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getMemberByEmail() throws Exception {
    }

    @Test
    public void getMemberById() throws Exception {
    }
}