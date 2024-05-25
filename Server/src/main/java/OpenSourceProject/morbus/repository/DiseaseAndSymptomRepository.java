package OpenSourceProject.morbus.repository;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public interface DiseaseAndSymptomRepository {
    Object JsonSetting(String path) throws IOException, ParseException;

    ArrayList<String> toArr(JSONArray jsonArray);
}
