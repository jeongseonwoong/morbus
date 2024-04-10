package OpenSourceProject.morbus.algorithm;

import java.util.HashMap;
import java.util.Map;

public class FindDisease {

    private final Map<String, Symptom> symptomList=new HashMap<String,Symptom>();

    private Symptom findSymptom(String symptomName)
    {
        return symptomList.get(symptomName);
    }


    public Symptom selectSymptom(String symptomName)
    {
        return findSymptom(symptomName);
    }


}
