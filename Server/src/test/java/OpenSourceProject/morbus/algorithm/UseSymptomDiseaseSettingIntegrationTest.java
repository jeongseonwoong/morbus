package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.repository.DiseaseRepository;
import OpenSourceProject.morbus.repository.RepositorySetting;
import OpenSourceProject.morbus.repository.SymptomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UseSymptomDiseaseSettingIntegrationTest {
    @Autowired
    SymptomSetting symptomSetting;
    @Autowired
    DiseaseSetting diseaseSetting;

    @Autowired
    DiseaseRepository diseaseRepository;


    @Test
    public void 증상서비스_작동확인(){
        Assertions.assertThat(symptomSetting.findAllSymptom().size()).isEqualTo(29);
        Assertions.assertThat(symptomSetting.findSymptomByName("기침").get().getName()).isEqualTo("기침");
    }

    @Test
    public void 질병서비스_작동확인(){
        Assertions.assertThat(diseaseSetting.findByName("감기").get().getName()).isEqualTo("감기");
        Assertions.assertThat(diseaseSetting.findDisease().size()).isEqualTo(63);
        Assertions.assertThat(diseaseRepository.toArr(null)).isEmpty();
    }


}

