package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.Symptom;
import OpenSourceProject.morbus.repository.DiseaseRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

@Service
public class SymptomSetting extends Setting {

    private final DiseaseSetting diseaseSetting;

    public SymptomSetting(DiseaseSetting diseaseSetting) {
        this.diseaseSetting = diseaseSetting;
    }


    public @ResponseBody ArrayList<Symptom> setSymptom() throws Exception {
        Object obj = JsonSetting("static/data/SymptomList.json");
        JSONArray dateArray = (JSONArray) obj ;
        ArrayList<Symptom> symptomArrayList = new ArrayList<>();
        for (Object o : dateArray) {
            JSONObject ele = (JSONObject) o;

            //제이슨 파일로부터 증상 값 가져오기
            String strSym = (String) ele.get("name");
            JSONArray relatedConditions = (JSONArray) ele.get("related_conditions");
            JSONArray keyWords =(JSONArray) ele.get("keyword");

            //가져온 값을 코드에 맞게 변환
            ArrayList<String> keyWordsArr = toArr(keyWords);
            ArrayList<String> list = toArr(relatedConditions);


            //제이슨 파일로부터 질병 값 가져오기
            ArrayList<Disease> diseaseArrayList = diseaseSetting.strToDisease(list);
            diseaseArrayList.stream().forEach(disease -> {
            });
            //가져온 값을 코드에 맞게 변환

            //증상에 값 추가하고 배열에 증상 추가
            Symptom symptom = new Symptom();
            symptom.set(strSym,diseaseArrayList,keyWordsArr);
            symptomArrayList.add(symptom);
        }
        return symptomArrayList;
    }

}