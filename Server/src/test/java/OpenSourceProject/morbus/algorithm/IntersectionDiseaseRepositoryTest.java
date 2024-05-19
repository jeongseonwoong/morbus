package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Disease;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class IntersectionDiseaseRepositoryTest {

    private final Map<String,Integer> diseaseCount = new HashMap<String,Integer>();
    private final Map<Disease,Integer> intersectionDisease = new HashMap<Disease,Integer>();

    @Test
    void addDisease() {
        //given

        Disease disease=new Disease("name","hospital","info1","info2");
        //when
        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease.getName()))
        {
            diseaseCount.put(disease.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease.getName(),diseaseCount.get(disease.getName())+1);
        }

        Disease disease2=new Disease("name","hospital","info1","info2");

        if(diseaseCount.isEmpty() || !diseaseCount.containsKey(disease.getName()))
        {
            diseaseCount.put(disease.getName(),1);
        }
        else
        {
            diseaseCount.replace(disease.getName(),diseaseCount.get(disease.getName())+1);
        }
        Assertions.assertThat(diseaseCount.get(disease.getName())).isEqualTo(2);

    }

    @Test
    void getDuplicatedDisease() {
    }
}