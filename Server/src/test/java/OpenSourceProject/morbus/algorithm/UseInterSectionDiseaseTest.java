package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.repository.IntersectionDiseaseRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseInterSectionDiseaseTest {


    private final IntersectionDiseaseRepository intersectionDiseaseRepository = new IntersectionDiseaseRepository();

    @Test
    public void 중복질병확인(){
        Disease disease =new Disease("name","hospital","self","brief","description");
        intersectionDiseaseRepository.addDisease(disease);
        Disease disease2 =new Disease("name","hospital","self","brief","description");
        intersectionDiseaseRepository.addDisease(disease2);
        Assertions.assertThat(intersectionDiseaseRepository.getDuplicatedDisease().size()).isEqualTo(1);
        Disease disease3 = new Disease("name2","hospital","self","brief","description");
        intersectionDiseaseRepository.addDisease(disease3);
        Disease disease4 = new Disease("name2","hospital","self","brief","description");
        intersectionDiseaseRepository.addDisease(disease4);
        Assertions.assertThat(intersectionDiseaseRepository.getDuplicatedDisease().size()).isEqualTo(2);
        Disease disease5 = new Disease("name2","hospital","self","brief","description");
        intersectionDiseaseRepository.addDisease(disease5);
        Assertions.assertThat(intersectionDiseaseRepository.getDuplicatedDisease().size()).isEqualTo(1);
        Disease disease6 = new Disease("name3","hospital","self","brief","description");
        Disease disease7 = new Disease("name3","hospital","self","brief","description");
        Disease disease8 = new Disease("name3","hospital","self","brief","description");
        intersectionDiseaseRepository.addDisease(disease6);
        intersectionDiseaseRepository.addDisease(disease7);
        intersectionDiseaseRepository.addDisease(disease8);
        Assertions.assertThat(intersectionDiseaseRepository.getDuplicatedDisease().size()).isEqualTo(2);



    }
}
