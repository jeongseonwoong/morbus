package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Disease;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionDiseaseRepositoryTest {

    @Test
    void addDisease() {
        Map<String,Integer> diseaseCount = new HashMap<String,Integer>();
        Disease disease = new Disease("감기","내과","아무거나","걸리면 아프다","너무 아프다");
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease.getName()))
        {
            diseaseCount.put(disease.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease.getName(),diseaseCount.get(disease.getName())+1);
        }
        Assertions.assertThat(diseaseCount.get(disease.getName())).isEqualTo(1);
        Disease disease2 = new Disease("독감","내과","아무거나","걸리면 아프다","너무 아프다");
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease2.getName()))
        {
            diseaseCount.put(disease2.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease2.getName(),diseaseCount.get(disease2.getName())+1);
        }
        Assertions.assertThat(diseaseCount.get(disease.getName())).isEqualTo(1);
        Disease disease3 = new Disease("감기","내과","아무거나","걸리면 아프다","너무 아프다");
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease3.getName()))
        {
            diseaseCount.put(disease3.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease3.getName(),diseaseCount.get(disease3.getName())+1);
        }
        Assertions.assertThat(diseaseCount.get(disease.getName())).isEqualTo(2);
    }

    @Test
    void getDuplicatedDisease() {
        Map<String,Integer> diseaseCount = new HashMap<String,Integer>();
        Disease disease = new Disease("감기","내과","아무거나","걸리면 아프다","너무 아프다");
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease.getName()))
        {
            diseaseCount.put(disease.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease.getName(),diseaseCount.get(disease.getName())+1);
        }
        Disease disease2 = new Disease("독감","내과","아무거나","걸리면 아프다","너무 아프다");
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease2.getName()))
        {
            diseaseCount.put(disease2.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease2.getName(),diseaseCount.get(disease2.getName())+1);
        }
        Disease disease3 = new Disease("감기","내과","아무거나","걸리면 아프다","너무 아프다");
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease3.getName()))
        {
            diseaseCount.put(disease3.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease3.getName(),diseaseCount.get(disease3.getName())+1);
        }

        Map<String,Integer> intersectionDisease = new HashMap<String,Integer>();
        int max=0;
        for (String key : diseaseCount.keySet()) {
            Integer value = diseaseCount.get(key);
            if(value>max)
            {
                max=value;
                intersectionDisease.clear();
            }
            if(value==max)
                intersectionDisease.put(key, value);
        }

        Assertions.assertThat(intersectionDisease.get("감기")).isEqualTo(2);
    }
}