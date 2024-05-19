package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.morbus.VOclass.Disease;
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
import java.util.ArrayList;

@Service
public class DiseaseSetting extends Setting {

    public @ResponseBody ArrayList<Disease> strToDisease(ArrayList<String> relatedCondition ) throws Exception {
        ArrayList<Disease> diseaseArrayList = new ArrayList<>();
        Object obj = JsonSetting();

        JSONArray dateArray = (JSONArray) obj;
        dateArray.stream().forEach(a -> {
            JSONObject ele = (JSONObject) a;

            //제이슨 파일로부터 값 가져오기
            String DiseaseName= (String) ele.get("name");
            relatedCondition.forEach(str->{
                if(DiseaseName.equals(str))
                {
                    String hospital= (String) ele.get("hospital");
                    String selfTreatment= (String) ele.get("self_treatment");
                    String briefInfo= (String) ele.get("brief-explanation");
                    String detailInfo= (String) ele.get("detail-explanation");
                    Disease disease = new Disease(DiseaseName,hospital,selfTreatment,briefInfo,detailInfo);
                    diseaseArrayList.add(disease);
                }
            });
        });
        return diseaseArrayList;
    }

    public ArrayList<Disease> setDisease() throws IOException, ParseException {
        ArrayList<Disease> diseaseArrayList = new ArrayList<>();
        Object obj = JsonSetting();
        JSONArray dateArray = (JSONArray) obj ;
        dateArray.stream().forEach(o -> {
            JSONObject ele = (JSONObject) o;

            //제이슨 파일로부터 값 가져오기
            String DiseaseName= (String) ele.get("name");
            String hospital=(String) ele.get("hospital");
            String selfTreatment=(String) ele.get("self_treatment");
            String briefInfo= (String) ele.get("brief-explanation");
            String detailInfo= (String) ele.get("detail-explanation");
            Disease disease =new Disease(DiseaseName,hospital,selfTreatment,briefInfo,detailInfo);
            diseaseArrayList.add(disease);
        });
        return diseaseArrayList;
    }



}
