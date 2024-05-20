package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.Symptom;
import OpenSourceProject.morbus.VOclass.SymptomDiseasePair;
import OpenSourceProject.morbus.algorithm.DiseaseSetting;
import OpenSourceProject.morbus.repository.IntersectionDiseaseRepository;
import OpenSourceProject.morbus.algorithm.SymptomSetting;
import jakarta.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@Controller
public class MorbusController {

    private final ArrayList<Symptom>symptomArrayList;//증상 배열
    private final HashMap<String, Symptom> findSym;//증상 Hash Map(검색 시 사용)
    private final ArrayList<Disease>diseaseArrayList;
    private final HashMap<String, Disease> findDise;
    private final HttpSession session;

    //생성자 내에서 증상 배열 초기화
    @Autowired
    MorbusController(SymptomSetting symptomSetting, DiseaseSetting diseaseSetting, HttpSession session) throws Exception {
        this.session = session;
        findSym = new HashMap<>();
        findDise=new HashMap<>();
        symptomArrayList = symptomSetting.setSymptom();
        for(Symptom symptom:symptomArrayList)
        {
            findSym.put(symptom.getName(),symptom);
        }

        diseaseArrayList=diseaseSetting.setDisease();
        for(Disease disease: diseaseArrayList)
        {
            findDise.put(disease.getName(),disease);
        }
    }


    @GetMapping("/")
    public String mainPage() {

        return "../static/morbus";
    }

    @GetMapping("morbus.html") //홈페이지 로고 클릭시 메인 홈페이지로 이동하는 컨트롤러
    public String toMainPage(Model model,HttpSession session)
    {
        model.addAttribute("member", session.getAttribute("member"));
        System.out.println(session.getAttribute("member"));
        System.out.println("abc");
        return "../static/morbus";
    }



    @GetMapping("Symptom") // 메인 홈페이지에서 질병자가진단 페이지로 넘어가는 컨트롤러
    public String Symptom(Model model2){
        model2.addAttribute("SymList",symptomArrayList);
        return "selectSymptom";
    }

    @GetMapping("MedicineInfo")
    public String MedicineInfo(){
        return "MedicineInfo";
    }

    @GetMapping("Symptom_record")//메인 홈페이지에서 증상 기록지 페이지로 넘어가는 컨트롤러
    public String symptom_record(Model model,Model model2) throws IOException, ParseException {
        //data processing

        return "Symptom_record";
    }


    @PostMapping("RelateDisease")//관련된 질병들을 표시해주는 페이지로 넘어가는 컨트롤러
    public String relateDisease(@RequestParam(value = "Symptom") String[] symName, Model model, Model model2)
    {
        //선택한 증상들과 관련된 질병을 찾는 알고리즘
        ArrayList<SymptomDiseasePair> diseaseList = new ArrayList<SymptomDiseasePair>();

        //중복된 질병을 저장하는 저장소
        IntersectionDiseaseRepository intersectionDisease = new IntersectionDiseaseRepository();

        for(String str : symName)
        {
            //증상
            Symptom foundSymptom = findSym.get(str);
            //증상과 관련된 질병 리스트를 가져온다.
            ArrayList<Disease> relateDisease= foundSymptom.getReDisease();

            //중복되는 질병들을 알기 위해서 중복 저장소에 넣어준다.
            for(Disease disease: relateDisease)
            {
                intersectionDisease.addDisease(disease);
            }

            //증상의 이름과 증상과 관련된 질병 리스트를 묶어준다.
            SymptomDiseasePair symptomDiseasePair= new SymptomDiseasePair(str,relateDisease);

            //묶음들의 리스트를 만든다.
            diseaseList.add(symptomDiseasePair);
        }

        //중복된 질병리스트
        List<Map.Entry<String,Integer>>duplicatedDisease = intersectionDisease.getDuplicatedDisease();
        Map<Disease, Integer> duplicatedDisease2= new HashMap<>();
        for(Map.Entry<String, Integer> map: duplicatedDisease)
        {
            Disease disease = findDise.get(map.getKey());
            duplicatedDisease2.put(disease,map.getValue());
        }
        model.addAttribute("ReDisease",diseaseList);
        model2.addAttribute("DuplicateDisease",duplicatedDisease2);
        return "RelateDisease";
    }

    @PostMapping("selectSymptom")//찾고자 하는 증상을 입력받는 컨트롤러
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

    @PostMapping("diseaseInfo")//질병 정보 페이지로 이동하는 컨트롤러
    public String diseaseInfo(@RequestParam(value="diseaseName")String diseaseName, Model model, Model model2)
    {
        model.addAttribute("diseaseName",diseaseName);
        if(findDise.containsKey(diseaseName))
        {
            Disease disease = findDise.get(diseaseName);
            model2.addAttribute("disease",disease);
        }
        return "diseaseInfo";
    }


}

