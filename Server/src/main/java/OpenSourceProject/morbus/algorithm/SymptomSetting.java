package OpenSourceProject.morbus.algorithm;
import OpenSourceProject.morbus.VOclass.Symptom;
import OpenSourceProject.morbus.repository.SymptomRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SymptomSetting{

    private final SymptomRepository symptomRepository;

    public SymptomSetting(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public List<Symptom> findAllSymptom(){return symptomRepository.findAll();}

    public Optional<Symptom> findSymptomByName(String symptom){return symptomRepository.findByName(symptom);}
}