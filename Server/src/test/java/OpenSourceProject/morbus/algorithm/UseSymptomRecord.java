package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Symptom;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class UseSymptomRecord {

    @Autowired
    SymptomRecordService symptomRecordService;
    @Autowired
    HttpSession session;


    @Test
    public void test() {
        symptomRecordService.saveSymptom("abc",session);
        Assertions.assertThat(symptomRecordService.getAllRecords(33L).size()).isEqualTo(0);
    }

    @Test
    public void 리코드(){
        SymptomRecord symptomRecord = new SymptomRecord();
        Symptom symptom = new Symptom();

        symptomRecord.setSymptom("ac");
        Assertions.assertThat(symptomRecord.getSymptom()).isEqualTo("ac");
        symptomRecord.setId(12L);
        Assertions.assertThat(symptomRecord.getId()).isEqualTo(12L);
        symptomRecord.setRecordKey(13L);
        Assertions.assertThat(symptomRecord.getRecordKey()).isEqualTo(13L);
        symptomRecord.setTimestamp(LocalDateTime.now());
        Assertions.assertThat(symptomRecord.getTimestamp()).isNotNull();
    }
}
