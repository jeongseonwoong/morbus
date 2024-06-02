package OpenSourceProject.morbus.voclassTest;

import OpenSourceProject.morbus.VOclass.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IntegrateVOclassTest {

    @Test
    public void diseaseTest()
    {
        Disease disease = new Disease("name","hos","self","b","d");
        Assertions.assertThat(disease.getSelfTreatment()).isEqualTo("self");
        Assertions.assertThat(disease.getHospital()).isEqualTo("hos");
        Assertions.assertThat(disease.getDescription()).isEqualTo("d");
        Assertions.assertThat(disease.getBriefInfo()).isEqualTo("b");
    }

    @Test
    public void UrlTest()
    {
        Url url = new Url();
        url.setUrl("http://www.google.com");
        Assertions.assertThat(url.getUrl()).isEqualTo("http://www.google.com");
    }

    @Test
    public void SearchTextTest()
    {
        SearchText searchText = new SearchText();
        searchText.setSearchText("hos");
        Assertions.assertThat(searchText.getSearchText()).isEqualTo("hos");
        Assertions.assertThat(searchText.toString()).isEqualTo("hos");
    }

    @Test
    public void SymptomDiseasePairTest()
    {
        ArrayList<Disease> arr = new ArrayList<>();
        Disease disease = new Disease("name","hos","self","b","d");
        arr.add(disease);
        SymptomDiseasePair symptomDiseasePair = new SymptomDiseasePair("sym",arr);
        Assertions.assertThat(symptomDiseasePair.first()).isEqualTo("sym");
        Assertions.assertThat(symptomDiseasePair.second()).isEqualTo(arr);
    }

    @Test
    public void symptomTest()
    {
        Symptom symptom = new Symptom();
        Disease disease = new Disease("s","h","self","b","d");
        ArrayList<Disease> arr1 =  new ArrayList<Disease>();
        arr1.add(disease);

        ArrayList<String> arr2 = new ArrayList<>();
        arr2.add("a");
        symptom.set("s",arr1,arr2);
        List<String> list =  symptom.getKeywords();
        Assertions.assertThat(list.size()).isEqualTo(1);
        Assertions.assertThat(symptom.getKeywordMap().containsKey("s")).isTrue();
    }

}
