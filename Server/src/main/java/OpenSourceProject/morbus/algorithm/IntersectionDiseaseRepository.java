package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.VOclass.Disease;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class IntersectionDiseaseRepository {
    private final Map<Disease,Integer> intersectionDisease = new HashMap<Disease,Integer>();

    public void addDisease(Disease disease)
    {
        intersectionDisease.put(disease,intersectionDisease.get(disease)+1);
    }

    public Map<Disease,Integer> getDuplicatedDisease()
    {
        return intersectionDisease;
    }
}
