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
    public SymptomRecord() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}