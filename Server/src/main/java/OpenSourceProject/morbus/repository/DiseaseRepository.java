package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Disease;

import java.util.List;
import java.util.Optional;

public abstract class DiseaseRepository extends RepositorySetting {
    public abstract Optional<Disease> findByName(String disease);
    public abstract List<Disease> findAll();
}
