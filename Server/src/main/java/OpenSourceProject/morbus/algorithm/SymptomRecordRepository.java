package OpenSourceProject.morbus.algorithm;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SymptomRecordRepository extends JpaRepository<SymptomRecord, Long> {
    List<SymptomRecord> findByRecordKey(Long recordkey);
}