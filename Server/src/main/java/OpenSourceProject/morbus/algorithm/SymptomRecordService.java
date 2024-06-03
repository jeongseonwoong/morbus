package OpenSourceProject.morbus.algorithm;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SymptomRecordService {
    @Autowired
    private SymptomRecordRepository symptomRecordRepository;

    public Long saveSymptom(String symptom, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        SymptomRecord record = new SymptomRecord();
        record.setSymptom(symptom);
        record.setTimestamp(LocalDateTime.now());
        record.setRecordKey(memberId);
        symptomRecordRepository.save(record);
        return memberId;
    }

    public List<SymptomRecord> getAllRecords(Long memberId) {
        return symptomRecordRepository.findByRecordKey(memberId);
    }

}
