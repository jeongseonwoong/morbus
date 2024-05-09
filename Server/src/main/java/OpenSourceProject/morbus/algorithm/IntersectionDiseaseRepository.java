package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.VOclass.Disease;

import java.util.*;


public class IntersectionDiseaseRepository {
    private final Map<String,Integer> diseaseCount = new HashMap<String,Integer>();
    private final Map<String,Integer> intersectionDisease = new HashMap<String,Integer>();
    private final Map<Disease,Integer> diseaseInt= new HashMap<>();

    public void addDisease(Disease disease)
    {
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease.getName()))
        {
            diseaseCount.put(disease.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease.getName(),diseaseCount.get(disease.getName())+1);
        }
    }

    public List<Map.Entry<String,Integer>> getDuplicatedDisease()
    {
        for (String key : diseaseCount.keySet()) {
            Integer value = diseaseCount.get(key);
            if (value >= 2) {
                intersectionDisease.put(key, value);
            }
        }

        List<Map.Entry<String,Integer>>duplicateDisease= new LinkedList<>(intersectionDisease.entrySet());
        duplicateDisease.sort(Map.Entry.comparingByValue(Comparator.naturalOrder()));
        return duplicateDisease;
    }
}
