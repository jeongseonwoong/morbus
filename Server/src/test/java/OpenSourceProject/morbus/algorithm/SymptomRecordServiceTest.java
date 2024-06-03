package OpenSourceProject.morbus.algorithm;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class SymptomRecordServiceTest {

    @Autowired
    final SymptomRecordService symptomRecordService;

    SymptomRecordServiceTest(SymptomRecordService symptomRecordService) {
        this.symptomRecordService = symptomRecordService;
    }

}