package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.VOclass.Disease;
import OpenSourceProject.VOclass.Symptom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DiseaseSetting implements Setting {

    public Object JsonSetting() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("src/main/resources/static/data/DiseaseList.json");
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

    public @ResponseBody ArrayList<Disease> strToDisease(ArrayList<String> relatedCondition ) throws Exception {
        ArrayList<Disease> diseaseArrayList = new ArrayList<>();
        Object obj = JsonSetting();
        JSONArray dateArray = (JSONArray) obj ;
        for (Object o : dateArray) {
            JSONObject ele = (JSONObject) o;

            //제이슨 파일로부터 값 가져오기
            String DiseaseName= (String) ele.get("name");
            for(String str: relatedCondition)
            {
                if(DiseaseName.equals(str))
                {
                    String briefInfo= (String) ele.get("brief-explanation");
                    String detailInfo= (String) ele.get("detail-explanation");
                    Disease disease =new Disease(DiseaseName,briefInfo,detailInfo);
                    diseaseArrayList.add(disease);
                }
            }

        }
        return diseaseArrayList;
    }

    public ArrayList<Disease> setDisease() throws IOException, ParseException {
        ArrayList<Disease> diseaseArrayList = new ArrayList<>();
        Object obj = JsonSetting();
        JSONArray dateArray = (JSONArray) obj ;
        for (Object o : dateArray) {
            JSONObject ele = (JSONObject) o;

            //제이슨 파일로부터 값 가져오기
            String DiseaseName= (String) ele.get("name");
            String briefInfo= (String) ele.get("brief-explanation");
            String detailInfo= (String) ele.get("detail-explanation");
            Disease disease =new Disease(DiseaseName,briefInfo,detailInfo);
            diseaseArrayList.add(disease);

        }

        return diseaseArrayList;
    }



}
