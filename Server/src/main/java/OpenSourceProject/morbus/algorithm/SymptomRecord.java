package OpenSourceProject.morbus.algorithm;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SymptomRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symptom;
    private LocalDateTime timestamp;
    private Long recordKey;
    public Long getRecordKey() {
        return recordKey;
    }
    public void setRecordKey(Long recordKey) {
        this.recordKey = recordKey;
    }
    public SymptomRecord() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSymptom() {
        return symptom;
    }
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}