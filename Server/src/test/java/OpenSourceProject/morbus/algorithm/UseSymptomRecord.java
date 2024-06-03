package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Symptom;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class UseSymptomRecord {

    @Autowired
    SymptomRecordService symptomRecordService;
    @Autowired
    HttpSession session;


    @Test
    public void test() {
        Long id = symptomRecordService.saveSymptom("qwe",session);
        Assertions.assertThat(symptomRecordService.getAllRecords(id).size()).isEqualTo(1);
    }

    @Test
    public void 리코드(){
        SymptomRecord symptomRecord = new SymptomRecord();
        Symptom symptom = new Symptom();

        symptomRecord.setSymptom("ac");
        symptomRecord.setId(21L);
        Assertions.assertThat(symptomRecord.getId()).isEqualTo(21L);
        symptomRecord.setId(12L);
        Assertions.assertThat(symptomRecord.getId()).isEqualTo(12L);
        symptomRecord.setRecordKey(13L);
        Assertions.assertThat(symptomRecord.getRecordKey()).isEqualTo(13L);
        symptomRecord.setTimestamp(LocalDateTime.now());
        Assertions.assertThat(symptomRecord.getTimestamp()).isNotNull();
    }
}
