package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.repository.DiseaseRepository;
import OpenSourceProject.morbus.repository.MemoryDiseaseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;


public class DiseaseSettingTest {

    DiseaseRepository diseaseRepository = new MemoryDiseaseRepository();

    public DiseaseSettingTest() throws IOException, org.json.simple.parser.ParseException {
    }


    @Test
    void findDisease() throws IOException, ParseException, org.json.simple.parser.ParseException {
        Assertions.assertThat(diseaseRepository.findAll()).isEqualTo(diseaseRepository.findAll());
    }

    @Test
    void findByName() {
///        diseaseRepository.findByName("감기");
        Assertions.assertThat(diseaseRepository.findByName("감기").get().getName()).isEqualTo("감기");
    }
}