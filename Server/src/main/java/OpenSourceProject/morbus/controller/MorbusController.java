package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.algorithm.SymptomSetting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MorbusController {

    @GetMapping("Symptom")
    public String Symptom(Model model, Model model2)
    {
        model.addAttribute("data", "안녕하세요 Morbus입니다.");
        SymptomSetting symptomSetting=new SymptomSetting();
        model2.addAttribute("symptomList",symptomSetting.setSymptom());
        return "Symptom";
    }





}
