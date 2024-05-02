package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.VOclass.Disease;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DiseaseSetting implements Setting {

    public Object JsonSetting() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        ClassPathResource resource = new ClassPathResource("static/data/DiseaseList.json");
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
