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
import za.ac.nwu.logic.flow.ViewMemberService;

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
    private MemberService memberService;

    @Mock
    private ViewMemberService viewMemberService;

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
        String expectedConfirmation = "{\"confirmation\":true,\"cargo\":[{" +
                "\"memId\":1," +
                "\"email\":\"reynardengels@gmail.com\"," +
                "\"phoneNumber\":\"0723949955\"," +
                "\"firstName\":\"Reynard\"," +
                "\"lastName\":\"Engels\"}," +
                "{" +
                "\"memId\":2," +
                "\"email\":\"lourenzengels@gmail.com\"," +
                "\"phoneNumber\":\"0826516473\"," +
                "\"firstName\":\"Lourenz\"," +
                "\"lastName\":\"Engels\"" +
                "}," +
                "{" +
                "\"memId\":4," +
                "\"email\":\"rudidreyer@gmail.com\"," +
                "\"phoneNumber\":\"0795046299\"," +
                "\"firstName\":\"Rudi\"," +
                "\"lastName\":\"Dreyer\"" +
                "}," +
                "{" +
                "\"memId\":5," +
                "\"email\":\"caityopperman@gmail.com\"," +
                "\"phoneNumber\":\"0865412233\"," +
                "\"firstName\":\"Caitlyn\"," +
                "\"lastName\":\"Opperman\"" +
                "}," +
                "{" +
                "\"memId\":6," +
                "\"email\":\"kevenvanstaden@gmail.com\"," +
                "\"phoneNumber\":\"0874569781\"," +
                "\"firstName\":\"Kevin\"," +
                "\"lastName\":\"Van Staden\"" +
                "}," +
                "{" +
                "\"memId\":7," +
                "\"email\":\"zandervanstaden@gmail.com\"," +
                "\"phoneNumber\":\"0753247658\"," +
                "\"firstName\":\"Zander\"," +
                "\"lastName\":\"Van Staden\"" +
                "}," +
                "{" +
                "\"memId\":8," +
                "\"email\":\"ivangiezing@gmail.com\"," +
                "\"phoneNumber\":\"0764321596\"," +
                "\"firstName\":\"Ivan\"," +
                "\"lastName\":\"Giezing\"" +
                "}," +
                "{" +
                "\"memId\": 9," +
                "\"email\":\"marniseLambrecths@gmail.com\"," +
                "\"phoneNumber\":\"0746628473\"," +
                "\"firstName\":\"Marnise\"," +
                "\"lastName\":\"Lambrecths\"" +
                "}," +
                "{" +
                "\"memId\":10," +
                "\"email\":\"johankohuman@gmail.com\"," +
                "\"phoneNumber\":\"0816574582\"," +
                "\"firstName\":\"Johanko\"," +
                "\"lastName\":\"Human\"" +
                "}," +
                "{" +
                "\"memId\":11," +
                "\"email\":\"marnusjacobs@gmail.com\"," +
                "\"phoneNumber\":\"0765047431\"," +
                "\"firstName\":\"Marnus\"," +
                "\"lastName\":\"Jacobs\"}]}";

        List<MemberDto> memberDtos = new ArrayList<>();
        memberDtos.add(new MemberDto(1,"reynardengels@gmail.com", "0723949955", "Reynard", "Engels"));
        memberDtos.add(new MemberDto(2,"lourenzengels@gmail.com", "0826516473", "Lourenz", "Engels"));
        memberDtos.add(new MemberDto(4,"rudidreyer@gmail.com", "0795046299", "Rudi", "Dreyer"));
        memberDtos.add(new MemberDto(5,"caityopperman@gmail.com", "0865412233", "Caitlyn", "Opperman"));
        memberDtos.add(new MemberDto(6,"kevenvanstaden@gmail.com", "0874569781", "Kevin", "Van Staden"));
        memberDtos.add(new MemberDto(7,"zandervanstaden@gmail.com", "0753247658", "Zander", "Van Staden"));
        memberDtos.add(new MemberDto(8,"ivangiezing@gmail.com", "0764321596", "Ivan", "Giezing"));
        memberDtos.add(new MemberDto(9,"marniseLambrecths@gmail.com", "0746628473", "Marnise", "Lambrecths"));
        memberDtos.add(new MemberDto(10,"johankohuman@gmail.com", "0816574582", "Johanko", "Human"));
        memberDtos.add(new MemberDto(11,"marnusjacobs@gmail.com", "0765047431", "Marnus", "Jacobs"));

        when(viewMemberService.getMembers()).thenReturn(memberDtos);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_CONTROLLER_URL, "getAllMembers")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewMemberService, times(1)).getMembers();
        assertEquals(expectedConfirmation, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void newMember() throws Exception {
        String memberExpected = "{\"memId\":12,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\" }";
        String memberActual = "{\"confirmation\":true,\"cargo\":[" +
                "{\"memId\":12,\"email\":\"reynardengels@gmail.com\",\"phoneNumber\":\"0723949955\",\"firstName\":\"Reynard\",\"lastName\":\"Engels\" }";
        MemberDto memberDto = new MemberDto(12, "reynardengels@gmail.com", "0723949955","Reynard", "Engels");

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

    @Test
    public void getMemberByEmail() throws Exception {
        String expectedConfirmation = "{\"confirmation\":true,\"cargo\":{" +
                "\"memId\":1," + "\"email\":\"reynardengels@gmail.com\"," + "\"phoneNumber\":\"0723949955\"," +
                "\"firstName\":\"Reynard\"," + "\"lastName\":\"Engels\"}}";
        MemberDto memberDto = new MemberDto(
                1,
                "Reynard",
                "Engels",
                "reynardengels@gmail.com",
                "0723949955"
        );
        when(viewMemberService.getMemberByEmail("reynardengels@gmail.com")).thenReturn(memberDto);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", MEMBER_CONTROLLER_URL, "getMemberByEmail/reynardengels@gmail.com")))
                        .servletPath(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(viewMemberService, times(1)).getMemberByEmail("reynardengels@gmail.com");
        assertEquals(expectedConfirmation, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getMemberById() throws Exception {
        String expectedConfirmation = "{\"confirmation\":true,\"cargo\":{" +
                "\"memId\":1," + "\"email\":\"reynardengels@gmail.com\"," + "\"phoneNumber\":\"0723949955\"," +
                "\"firstName\":\"Reynard\"," + "\"lastName\":\"Engels\"}}";
        MemberDto memberDto = new MemberDto(
                1,
                "Reynard",
                "Engels",
                "reynardengels@gmail.com",
                "0723949955"
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