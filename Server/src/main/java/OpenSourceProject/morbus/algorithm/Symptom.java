package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.algorithm.Disease;

import java.util.HashSet;
import java.util.Set;

public class Symptom {

  String name= "";
  Set<Disease> associatingDisease =new HashSet<Disease>();
  public Symptom(String SymptomName,Set<Disease> Diseases)
  {
    name=SymptomName;
    this.associatingDisease =Diseases;
  }

}
