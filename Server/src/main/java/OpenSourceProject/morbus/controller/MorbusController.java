package OpenSourceProject.morbus.controller;

import OpenSourceProject.VOclass.Disease;
import OpenSourceProject.VOclass.Symptom;
import OpenSourceProject.VOclass.SymptomDiseasePair;
import OpenSourceProject.morbus.algorithm.DiseaseSetting;
import OpenSourceProject.morbus.algorithm.IntersectionDisease;
import OpenSourceProject.morbus.algorithm.IntersectionDiseaseRepository;
import OpenSourceProject.morbus.algorithm.SymptomSetting;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class MorbusController {

    private final ArrayList<Symptom>symptomArrayList;//증상 배열
    private final HashMap<String, Symptom> findSym = new HashMap<>();//증상 Hash Map(검색 시 사용)
    private final ArrayList<Disease>diseaseArrayList;
    private final HashMap<String, Disease> findDise = new HashMap<>();

    //생성자 내에서 증상 배열 초기화
    MorbusController() throws Exception {
        SymptomSetting symptomSetting = new SymptomSetting();
        symptomArrayList = symptomSetting.setSymptom();
        for(Symptom symptom:symptomArrayList)
        {
            findSym.put(symptom.getName(),symptom);
        }

        DiseaseSetting diseaseSetting= new DiseaseSetting();
        diseaseArrayList=diseaseSetting.setDisease();
        for(Disease disease: diseaseArrayList)
        {
            findDise.put(disease.getName(),disease);
        }
    }

    @GetMapping("morbus") //홈페이지 로고 클릭시 메인 홈페이지로 이동하는 컨트롤러
    public String toMainPage()
    {
        return "morbus";
    }

    @GetMapping("Symptom") // 메인 홈페이지에서 질병자가진단 페이지로 넘어가는 컨트롤러
    public String Symptom(Model model2){
        model2.addAttribute("SymList",symptomArrayList);
        return "selectSymptom";
    }

    @GetMapping("Symptom_record")//메인 홈페이지에서 증상 기록지 페이지로 넘어가는 컨트롤러
    public String symptom_record(Model model,Model model2) throws IOException, ParseException {
        //data processing

        return "Symptom_record";
    }


    @PostMapping("RelateDisease")//관련된 질병들을 표시해주는 페이지로 넘어가는 컨트롤러
    public String relateDisease(@RequestParam(value = "Symptom") String[] symName, Model model)
    {
        //선택한 증상들과 관련된 질병을 찾는 알고리즘
        ArrayList<SymptomDiseasePair> diseaseList = new ArrayList<SymptomDiseasePair>();

        //중복된 질병을 저장하는 저장소
        IntersectionDiseaseRepository intersectionDisease = new IntersectionDiseaseRepository();

        for(String str : symName)
        {
            Symptom foundSymptom = findSym.get(str);
            SymptomDiseasePair symptomDiseasePair= new SymptomDiseasePair(str,foundSymptom.getReDisease());
            diseaseList.add(symptomDiseasePair);
        }
        model.addAttribute("ReDisease",diseaseList);
        return "RelateDisease";
    }

    @GetMapping("selectSymptom")//찾고자 하는 증상을 입력받는 컨트롤러
    public String searchSym(@RequestParam(value="searchText") String searchText, Model model, Model model2)
    {
        //증상 Hash_Map 에서 입력받은 증상과 연관이 있는 질병 찾는 알고리즘
        model2.addAttribute("SymList",symptomArrayList);
        if(findSym.containsKey(searchText))
        {
            model.addAttribute("searchText",searchText);
            return "selectSymptom";
        }
        else
        {
            for (Symptom symptom : symptomArrayList)
            {
                if (symptom.keywords.contains(searchText))
                {
                    model.addAttribute("searchText", symptom.getName());
                    return "selectSymptom";
                }
            }
        }
        model.addAttribute("searchText",null);
        return "selectSymptom";
    }

    @GetMapping("diseaseInfo")//질병 정보 페이지로 이동하는 컨트롤러
    public String diseaseInfo(@RequestParam(value="diseaseName")String diseaseName, Model model, Model model2)
    {
        model.addAttribute("diseaseName",diseaseName);
        if(findDise.containsKey(diseaseName))
        {
            Disease disease = findDise.get(diseaseName);
            model2.addAttribute("detailInfo",disease.getDescription());
        }
        return "diseaseInfo";
    }

}
