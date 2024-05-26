package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.VOclass.Url;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import OpenSourceProject.morbus.repository.MemberRepository;
import OpenSourceProject.morbus.repository.MemoryMemberRepository;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
public class MemberController {

    private final MemberSetting memberSetting;
    private final ServletRequest httpServletRequest;

    public MemberController(MemberSetting memberSetting, @Qualifier("httpServletRequest") ServletRequest httpServletRequest) {
        this.memberSetting = memberSetting;
        this.httpServletRequest = httpServletRequest;
    }

    @GetMapping("signUp")
    public String signUp(Model model) {
        return "signUp";
    }

    @GetMapping("login")
    public String LogIn() {
        return "login";
    }

    @GetMapping("getMemberRepository")
    public ResponseEntity<List<String>> getMemberRepository() {
        List<String>memberList = new ArrayList<>();
        memberSetting.findMember().stream().forEach(member -> {
            memberList.add(member.getName());
        });
        return ResponseEntity.ok(memberList);
    }


    @PostMapping("login")
    public String Login(@RequestParam (value = "password")String password, Model model, HttpSession session, HttpServletResponse response) throws IOException {

        if(memberSetting.findName(password).isPresent())
        {
            session.setAttribute("member", memberSetting.findName(password).get().getName());
            Optional<Member> memberOpt = memberSetting.findName(password);
            Member member = memberOpt.get();
            session.setAttribute("memberId", (Long) member.getId());
            model.addAttribute("memberId", (Long) member.getId());
        }
        model.addAttribute("member", session.getAttribute("member"));
        return "../static/morbus";
    }

    @PostMapping("signUp")
    public String SignUp(@RequestParam("password") String password ) {
        System.out.println(password);
        Member member = new Member();
        member.setName(password);

        memberSetting.join(member);

        return "../static/morbus";
    }


    @PostMapping("logout")
    public ResponseEntity<Url> LogOut(HttpSession session, HttpServletResponse response) throws Exception {
        // 캐시 무효화
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        session.removeAttribute("member");
        session.invalidate();

        Url url=new Url();
        url.setUrl("morbus.html");
        return ResponseEntity.ok(url);
    }
}

