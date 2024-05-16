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
}