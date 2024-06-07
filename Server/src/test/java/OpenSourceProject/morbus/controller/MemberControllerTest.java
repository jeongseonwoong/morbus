package OpenSourceProject.morbus.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberSetting memberSetting;

    @Test
    public void testSignUp() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"));
    }

    @Test
    public void testLogIn() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testGetMemberRepository() throws Exception {
        // 가짜 멤버 리스트 생성
        List<Member> fakeMemberList = new ArrayList<>();
        Member member = new Member();
        member.setId(1L);
        member.setName("mem1");
        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("mem2");
        fakeMemberList.add(member);
        fakeMemberList.add(member2);

        // MemberSetting 목 객체 설정
        when(memberSetting.findMember()).thenReturn(fakeMemberList);

        // GET 요청 테스트
        mockMvc.perform(get("/getMemberRepository"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("mem1"))
                .andExpect(jsonPath("$[1]").value("mem2"));
    }

    @Test
    public void testLogin_Success() throws Exception {
        String password = "password123";
        Member member = new Member();
        member.setName("username");
        member.setId(1L);
        when(memberSetting.findName(password)).thenReturn(Optional.of(member));

        mockMvc.perform(post("/login")
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("../static/morbus"))
                .andExpect(model().attributeExists("memberId"))
                .andExpect(model().attributeExists("member"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        String password = "password123";
        when(memberSetting.findName(password)).thenReturn(Optional.empty());

        mockMvc.perform(post("/login")
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("../static/morbus"))
                .andExpect(model().attributeDoesNotExist("memberId"))
                .andExpect(model().attributeDoesNotExist("member"));
    }

    @Test
    public void testSignUp_Success() throws Exception {
        String password = "password123";
        mockMvc.perform(post("/signUp")
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("../static/morbus"));
    }

    @Test
    public void testSignUp_Failure() throws Exception {
        String password = "password123";
        when(memberSetting.join(any(Member.class))).thenThrow(new IllegalStateException("Username already exists"));

        mockMvc.perform(post("/signUp")
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(post("/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("morbus.html"));
    }
}
