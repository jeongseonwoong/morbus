package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.VOclass.Symptom;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

public class SymptomSetting {

    public Object JsonSetting() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        ClassPathResource resource = new ClassPathResource("static/data/SymptomList.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        String s = "";
        StringBuilder sb = new StringBuilder();
        while((s = br.readLine()) != null){
            sb.append(s);
        }
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(sb.toString());
        return parser.parse(sb.toString());
    }

    private ArrayList<String> toArr(JSONArray jsonArray) {
        ArrayList<String> list= new ArrayList<>();
        if(jsonArray!=null)
        {
            for (Object o : jsonArray) {
                list.add((String) o);
            }
        }
        return list;
    }


    public @ResponseBody ArrayList<Symptom> setSymptom() throws IOException, ParseException {
        Object obj = JsonSetting();
        JSONArray dateArray = (JSONArray) obj ;
        ArrayList<Symptom> symptomArrayList = new ArrayList<>();
        for (Object o : dateArray) {
            Symptom symptom = new Symptom();
            JSONObject ele = (JSONObject) o;
            String strSym = (String) ele.get("name");
            JSONArray objDis = (JSONArray) ele.get("related_conditions");
            JSONArray keyWords =(JSONArray) ele.get("keyword");
            ArrayList<String> keyWordsArr = toArr(keyWords);
            ArrayList<String> list = toArr(objDis);
            symptom.set(strSym, list,keyWordsArr);
            symptomArrayList.add(symptom);
        }

        return symptomArrayList;
    }

}
