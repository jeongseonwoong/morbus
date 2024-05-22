package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.repository.DiseaseRepository;
import OpenSourceProject.morbus.repository.MemoryDiseaseRepository;
import OpenSourceProject.morbus.repository.MemorySymptomRepository;
import OpenSourceProject.morbus.repository.SymptomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymptomSettingTest {

    DiseaseRepository diseaseRepository = new MemoryDiseaseRepository();
    DiseaseSetting diseaseSetting = new DiseaseSetting(diseaseRepository);
    SymptomRepository symptomRepository = new MemorySymptomRepository(diseaseSetting);

    SymptomSettingTest() throws Exception {
    }


    @Test
    void findSymptomByName() {
        String symptomName="가래";
        Assertions.assertThat(symptomRepository.findByName(symptomName).get().getName()).isEqualTo(symptomName);
        Assertions.assertThat(symptomRepository.findByName(symptomName).get().getReDisease()).isNotNull();

        symptomName="기침";
        Assertions.assertThat(symptomRepository.findByName(symptomName).get().getName()).isEqualTo(symptomName);
        Assertions.assertThat(symptomRepository.findByName(symptomName).get().getReDisease()).isNotNull();

        symptomName="발열";
        Assertions.assertThat(symptomRepository.findByName(symptomName).get().getName()).isEqualTo(symptomName);
        Assertions.assertThat(symptomRepository.findByName(symptomName).get().getReDisease()).isNotNull();

    }


}