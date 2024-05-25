package OpenSourceProject.morbus.controller;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.SearchText;
import OpenSourceProject.morbus.VOclass.Symptom;
import OpenSourceProject.morbus.VOclass.SymptomDiseasePair;
import OpenSourceProject.morbus.algorithm.DiseaseSetting;
import OpenSourceProject.morbus.repository.IntersectionDiseaseRepository;
import OpenSourceProject.morbus.algorithm.SymptomSetting;
import jakarta.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import OpenSourceProject.morbus.algorithm.SymptomRecord;
import OpenSourceProject.morbus.algorithm.SymptomRecordService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

@Controller
public class MorbusController {

    private final DiseaseSetting diseaseSetting;
    private final SymptomSetting symptomsetting;

    //생성자 내에서 증상 배열 초기화
    @Autowired
    MorbusController(HttpSession session, DiseaseSetting diseaseSetting, SymptomSetting symptomsetting) throws Exception {
        this.diseaseSetting = diseaseSetting;
        this.symptomsetting = symptomsetting;
    }


    @GetMapping("/")
    public String mainPage() {

        return "../static/morbus";
    }

    @GetMapping("morbus.html") //홈페이지 로고 클릭시 메인 홈페이지로 이동하는 컨트롤러
    public String toMainPage(Model model,HttpSession session)
    {
        model.addAttribute("member", session.getAttribute("member"));
        return "../static/morbus";
    }



    @GetMapping("Symptom") // 메인 홈페이지에서 질병자가진단 페이지로 넘어가는 컨트롤러
    public String Symptom(Model model, Model model2){
        model2.addAttribute("SymList",symptomsetting.findAllSymptom());

        return "selectSymptom";
    }

    @GetMapping("MedicineInfo")
    public String MedicineInfo(){
        return "MedicineInfo";
    }

    @GetMapping("getSymptomList")
    public ResponseEntity<List<String>> getSymptomList(){
        System.out.println("avc");
        List<String> list = new ArrayList<>();
        symptomsetting.findAllSymptom().forEach(s -> {
            list.add(s.getName());
        });
        return ResponseEntity.ok(list);
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
            ArrayList<Disease> relateDisease = new ArrayList<>();
            if(symptomsetting.findSymptomByName(str).isPresent())
            {
                 relateDisease.addAll(symptomsetting.findSymptomByName(str).get().getReDisease());
            }

            //중복되는 질병들을 알기 위해서 중복 저장소에 넣어준다.
            relateDisease.forEach(intersectionDisease::addDisease);

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
            Disease disease = diseaseSetting.findByName(map.getKey()).get();
            duplicatedDisease2.put(disease,map.getValue());
        }
        model.addAttribute("ReDisease",diseaseList);
        model2.addAttribute("DuplicateDisease",duplicatedDisease2);
        return "RelateDisease";
    }

    @PostMapping("selectSymptom")//찾고자 하는 증상을 입력받는 컨트롤러
    public ResponseEntity<SearchText> searchSym(@RequestBody SearchText searchText)
    {
        System.out.println(searchText.toString());
        if(symptomsetting.findSymptomByName(searchText.toString()).isPresent())
        {
            return ResponseEntity.ok(searchText);
        }
        else
        {
            for (Symptom symptom : symptomsetting.findAllSymptom())
            {
                if (symptom.keywords.contains(searchText.toString()))
                {
                    searchText.setSearchText(symptom.getName());
                    return ResponseEntity.ok(searchText);
                }
            }
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("diseaseInfo")//질병 정보 페이지로 이동하는 컨트롤러
    public String diseaseInfo(@RequestParam(value="diseaseName")String diseaseName, Model model, Model model2)
    {
        model.addAttribute("diseaseName",diseaseName);
        if(diseaseSetting.findByName(diseaseName).isPresent())
        {
            Disease disease = diseaseSetting.findByName(diseaseName).get();
            model2.addAttribute("disease",disease);
        }
        return "diseaseInfo";
    }

    @Autowired
    private SymptomRecordService symptomRecordService;

    @PostMapping("/Symptom_record")
    public String recordSymptom(@RequestParam("message") String message) {
        if (!message.trim().isEmpty()) {
            symptomRecordService.saveSymptom(message.trim());
        }
        return "redirect:/Symptom_record"; // 채팅 기록 후 페이지 리다이렉션
    }

    @GetMapping("/Symptom_record")
    public String showRecords(Model model) {
        List<SymptomRecord> records = symptomRecordService.getAllRecords();
        model.addAttribute("records", records);
        return "Symptom_record"; // recordList.html로 렌더링
    }
}

