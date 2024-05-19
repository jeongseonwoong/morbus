package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.VOclass.MyState;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import OpenSourceProject.morbus.repository.JdbcTemplateMemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.parser.Entity;

@Controller
public class MemberController {

    private final MemberSetting memberSetting;
    private final HttpSession httpSession;

    public MemberController(MemberSetting memberSetting, MyState mystate, MyState myState, HttpSession httpSession) {
        this.memberSetting = memberSetting;
        this.httpSession = httpSession;
    }

    @ModelAttribute("member")
    public String member(HttpSession httpSession)
    {
       return (String) httpSession.getAttribute("member");
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
        member(session);
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
