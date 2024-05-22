package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.repository.DiseaseRepository;
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
import java.util.List;
import java.util.Optional;

public class DiseaseSetting{

    private final DiseaseRepository diseaseRepository;

    public DiseaseSetting(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public @ResponseBody ArrayList<Disease> strToDisease(ArrayList<String> relatedDisease ) throws Exception {
        ArrayList<Disease> diseaseArrayList = new ArrayList<>();

        for(String disease : relatedDisease) {
            if(diseaseRepository.findByName(disease).isPresent()) {
                diseaseArrayList.add(diseaseRepository.findByName(disease).get());
            }
        }
        return diseaseArrayList;
    }


    public List<Disease> findDisease() {return diseaseRepository.findAll();}

    public Optional<Disease> findByName(String disease){return diseaseRepository.findByName(disease);}


}
