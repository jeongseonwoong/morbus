package OpenSourceProject.morbus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MorbusController {

    @GetMapping("Symptom")
    public String Symptom(Model model)
    {
        model.addAttribute("data", "안녕하세요 Morbus입니다.");
        return "Symptom";
    }



}
