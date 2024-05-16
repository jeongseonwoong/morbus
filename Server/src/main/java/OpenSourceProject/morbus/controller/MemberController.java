package OpenSourceProject.morbus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("login")
    public String LogIn() {
        return "login";
    }

    @GetMapping("signUp")
    public String SignUp() {
        return "signUp";
    }
}
