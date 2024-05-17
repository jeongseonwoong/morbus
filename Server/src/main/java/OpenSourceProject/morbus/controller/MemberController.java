package OpenSourceProject.morbus.controller;

import OpenSourceProject.VOclass.Member;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberSetting memberSetting;

    @Autowired
    public MemberController(MemberSetting memberSetting) {
        this.memberSetting = memberSetting;
    }

    @GetMapping("signUp")
    public String signUp(Model model) {
        return "signUp";
    }

    @PostMapping("login")
    public String LogIn() {
        return "login";
    }

    @PostMapping("signUp")
    public String SignUp(@RequestParam("password") String password ) {
        System.out.println(password);
        Member member = new Member();
        member.setName(password);

        memberSetting.join(member);

        return "../static/morbus";
    }
}
