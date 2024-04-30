package OpenSourceProject.morbus.algorithm;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public interface Setting {
    Object JsonSetting() throws IOException, ParseException;
    ArrayList<String> toArr(JSONArray jsonArray);
}
