package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Disease;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class MemoryDiseaseRepository extends DiseaseRepository {

    private static HashMap<String, Disease> store = new HashMap<>();

   public MemoryDiseaseRepository() throws IOException, ParseException {
       Object obj = JsonSetting("static/data/DiseaseList.json");
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
           store.put(disease.getName(),disease);
       });

   }

    @Override
    public Optional<Disease> findByName(String disease) {
        return Optional.ofNullable(store.get(disease));
    }

    @Override
    public List<Disease> findAll() {
        return new ArrayList<>(store.values());
    }
}
