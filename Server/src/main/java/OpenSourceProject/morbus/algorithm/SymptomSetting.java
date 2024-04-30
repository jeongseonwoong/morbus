package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.VOclass.Disease;
import OpenSourceProject.VOclass.Symptom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

public class SymptomSetting implements Setting {

    public Object JsonSetting() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("src/main/resources/static/data/SymptomList.json");
        return parser.parse(reader);
    }

    public ArrayList<String> toArr(JSONArray jsonArray) {
        ArrayList<String> list= new ArrayList<>();
        if(jsonArray!=null)
        {
            for (Object o : jsonArray) {
                list.add((String) o);
            }
        }
        return list;
    }


    public @ResponseBody ArrayList<Symptom> setSymptom() throws Exception {
        Object obj = JsonSetting();
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
            DiseaseSetting diseaseSetting =new DiseaseSetting();
            ArrayList<Disease> diseaseArrayList = diseaseSetting.strToDisease(list);

            //가져온 값을 코드에 맞게 변환


            //증상에 값 추가하고 배열에 증상 추가
            Symptom symptom = new Symptom();
            symptom.set(strSym,diseaseArrayList,keyWordsArr);
            symptomArrayList.add(symptom);
        }

        return symptomArrayList;
    }

}
