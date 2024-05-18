package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import OpenSourceProject.morbus.repository.JdbcTemplateMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberSetting memberSetting;

    public MemberController(MemberSetting memberSetting) {
        this.memberSetting = memberSetting;
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
    public String Login(@RequestParam (value = "password")String password, Model model){
        if(memberSetting.findName(password).isPresent())
        {
            System.out.println(memberSetting.findName(password).get().getName());
            model.addAttribute("member", memberSetting.findName(password).get().getName());
        }
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
