package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("login")
    public String Login(@RequestParam (value = "password")String password, Model model, HttpSession session){

        if(memberSetting.findName(password).isPresent())
        {
            session.setAttribute("member", memberSetting.findName(password).get().getName());
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
}
