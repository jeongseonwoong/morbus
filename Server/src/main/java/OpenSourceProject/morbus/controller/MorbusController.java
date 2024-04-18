package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.algorithm.SymptomSetting;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class MorbusController {

    @GetMapping("Symptom")
    public String Symptom(Model model,Model model2) throws JSONException, IOException, ParseException {
        model.addAttribute("data", "안녕하세요 Morbus입니다.");
        SymptomSetting symptomSetting=new SymptomSetting();
        model2.addAttribute("SymList",symptomSetting.setSymptom());
        return "Symptom";
    }







}
