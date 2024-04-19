package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.VOclass.Symptom;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

public class SymptomSetting {

    private Object JsonSetting()throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("src/main/resources/DiseaseList.json");
        return parser.parse(reader);
    }

    private ArrayList<String> toArr(JSONArray jsonArray) throws JSONException {
        ArrayList<String> list= new ArrayList<>();
        if(jsonArray!=null)
        {
            for (Object o : jsonArray) {
                list.add((String) o);
            }
        }
        return list;
    }


    public @ResponseBody ArrayList<Symptom> setSymptom() throws IOException, ParseException, JSONException {

        Object obj = JsonSetting();
        JSONArray dateArray = (JSONArray) obj ;

        ArrayList<Symptom> symptomArrayList = new ArrayList<>();
        for (Object o : dateArray) {
            Symptom symptom = new Symptom();
            JSONObject ele = (JSONObject) o;
            String strSym = (String) ele.get("name");
            JSONArray objDis = (JSONArray) ele.get("related_conditions");

            ArrayList<String> list = toArr(objDis);
            symptom.set(strSym, list);
            symptomArrayList.add(symptom);
        }

        return symptomArrayList;
    }

}
