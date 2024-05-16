package OpenSourceProject.morbus.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SymptomRecordService {
    @Autowired
    private SymptomRecordRepository symptomRecordRepository;

    public List<SymptomRecord> getAllRecords() {
        return symptomRecordRepository.findAll();
    }
}
