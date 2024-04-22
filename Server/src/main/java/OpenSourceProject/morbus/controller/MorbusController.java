package OpenSourceProject.morbus.controller;

import OpenSourceProject.VOclass.Disease;
import OpenSourceProject.VOclass.Symptom;
import OpenSourceProject.morbus.algorithm.SymptomSetting;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Controller
public class MorbusController {

    private ArrayList<Symptom>symptomArrayList;
    HashMap<String,Symptom> findSym = new HashMap<>();

    @GetMapping("Symptom")
    public String Symptom(Model model,Model model2) throws JSONException, IOException, ParseException {
        model.addAttribute("data", "안녕하세요 Morbus입니다.");
        SymptomSetting symptomSetting=new SymptomSetting();
        symptomArrayList=symptomSetting.setSymptom();
        for(Symptom symptom:symptomArrayList)
        {
            findSym.put(symptom.get(),symptom);
        }
        model2.addAttribute("SymList",symptomArrayList);
        return "Symptom";
    }

    @GetMapping("Symptom_record")
    public String Symptom_record(Model model,Model model2) throws JSONException, IOException, ParseException {
        //data processing

        return "Symptom_record";
    }

    @PostMapping("ReDis")
    public String submit(@RequestParam(value = "Symptom") String[] symName, Model model)
    {
        ArrayList<ArrayList<Disease>> diseaseList = new ArrayList<ArrayList<Disease>>();
        for(String str : symName)
        {
            Symptom foundSymptom = findSym.get(str);
            diseaseList.add(foundSymptom.getReDisease());
        }
        model.addAttribute("ReDisease",diseaseList);
        return "ReDis";
    }

    @GetMapping("#")
    public String searchSym(@RequestParam(value="findingSym") String findingSym,Model model)
    {
        model.addAttribute("findingSym",findingSym);
        return "Symptom";
    }






}
