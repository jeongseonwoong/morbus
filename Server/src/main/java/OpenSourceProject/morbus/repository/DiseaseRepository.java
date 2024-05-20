package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.algorithm.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public abstract class DiseaseRepository extends Setting {
    public abstract Optional<Disease> findByName(String disease);
    public abstract List<Disease> findAll();

}
