package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.VOclass.Disease;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class IntersectionDiseaseRepository {
    private final Map<Disease,Integer> diseaseCount = new HashMap<Disease,Integer>();
    private final Map<Disease,Integer> intersectionDisease = new HashMap<Disease,Integer>();


    public void addDisease(Disease disease)
    {
        diseaseCount.put(disease,diseaseCount.get(disease)+1);
    }

    public List<Map.Entry<Disease,Integer>> getDuplicatedDisease()
    {
        for (Disease key : diseaseCount.keySet()) {
            Integer value = diseaseCount.get(key);
            if (value >= 2) {
                intersectionDisease.put(key, value);
            }
        }
        List<Map.Entry<Disease,Integer>>duplicateDisease= new LinkedList<>(intersectionDisease.entrySet());
        duplicateDisease.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return duplicateDisease;
    }
}
