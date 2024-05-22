package OpenSourceProject.morbus.repository;
import OpenSourceProject.morbus.VOclass.Symptom;
import java.util.List;
import java.util.Optional;

public abstract class SymptomRepository extends RepositorySetting {
    public abstract Optional<Symptom> findByName(String disease);
    public abstract List<Symptom> findAll();

}
