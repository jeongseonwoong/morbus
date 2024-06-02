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
    }
}
