package OpenSourceProject.VOclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Symptom {

  private String name;
  private final ArrayList<String> ReDisease = new ArrayList<String>();
  public Set<String>keywords = new HashSet<String>();

  private void setName(String inSym)
  {
    name=inSym;
  }

  private void setReDisease(ArrayList<String> reDisease)
  {
      ReDisease.addAll(reDisease);
  }

  private void setKeywords(ArrayList<String> array)
  {
      keywords.addAll(array);
  }

  public void set(String inSym, ArrayList<String> relateDisease, ArrayList<String> keyword)
  {
    setName(inSym);
    setReDisease(relateDisease);
    setKeywords(keyword);
  }

  public String getName()
  {
    return name;
  }

  public ArrayList<String> getReDisease() {
    return ReDisease;
  }
}
