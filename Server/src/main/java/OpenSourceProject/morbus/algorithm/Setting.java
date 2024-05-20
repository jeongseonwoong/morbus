package OpenSourceProject.morbus.algorithm;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;

public abstract class Setting {
    protected Object JsonSetting(String path) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        ClassPathResource resource = new ClassPathResource(path);
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



    ArrayList<String> toArr(JSONArray jsonArray)
    {
        ArrayList<String> list= new ArrayList<>();
        if(jsonArray!=null)
        {
            for (Object o : jsonArray) {
                list.add((String) o);
            }
        }
        return list;
    }
}
