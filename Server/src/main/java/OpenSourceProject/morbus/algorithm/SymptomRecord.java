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
    // 생성자
    public SymptomRecord() {
        // 기본 생성자
    }

    // Getter 및 Setter 메서드
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
