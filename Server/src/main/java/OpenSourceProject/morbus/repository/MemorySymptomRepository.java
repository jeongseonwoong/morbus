package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.VOclass.Symptom;
import OpenSourceProject.morbus.algorithm.DiseaseSetting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class MemorySymptomRepository extends SymptomRepository {

    private static Map<String, Symptom> store = new HashMap<>();
    private static long sequence =0L;
    private DiseaseSetting diseaseSetting;

    public MemorySymptomRepository(DiseaseSetting diseaseSetting) throws Exception {
        this.diseaseSetting =diseaseSetting;
        Object obj = JsonSetting("static/data/SymptomList.json");
        JSONArray dateArray = (JSONArray) obj ;
        for (Object o : dateArray) {
            JSONObject ele = (JSONObject) o;

            //제이슨 파일로부터 증상 값 가져오기
            String strSym = (String) ele.get("name");
            JSONArray relatedConditions = (JSONArray) ele.get("related_conditions");
            JSONArray keyWords =(JSONArray) ele.get("keyword");

            //가져온 값을 코드에 맞게 변환
            ArrayList<String> keyWordsArr = toArr(keyWords);
            ArrayList<String> list = toArr(relatedConditions);


            //제이슨 파일로부터 질병 값 가져오기
            ArrayList<Disease> diseaseArrayList = diseaseSetting.strToDisease(list);
            diseaseArrayList.stream().forEach(disease -> {
            });
            //가져온 값을 코드에 맞게 변환

            //증상에 값 추가하고 배열에 증상 추가
            Symptom symptom = new Symptom();
            symptom.set(strSym,diseaseArrayList,keyWordsArr);
            store.put(strSym,symptom);
        }
    }


    @Override
    public Optional<Symptom> findByName(String disease) {
        return Optional.ofNullable(store.get(disease));
    }

    @Override
    public List<Symptom> findAll() {
        return new ArrayList<>(store.values());
    }
}
