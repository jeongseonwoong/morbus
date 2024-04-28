package OpenSourceProject.VOclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Symptom {

  private String name;
  private ArrayList<Disease> ReDisease = new ArrayList<Disease>();
  public Set<String>keywords = new HashSet<String>();

  private void setName(String inSym)
  {
    name=inSym;
  }

  private void setReDisease(ArrayList<String> array)
  {
      for(int i=0;i<array.size();i++)
      {
        Disease disease = new Disease(array.get(i));
        ReDisease.add(disease);
      }

  }

  private void setKeywords(ArrayList<String> array)
  {
      keywords.addAll(array);
  }

  public void set(String inSym, ArrayList<String> array, ArrayList<String> keyword)
  {
    setName(inSym);
    setReDisease(array);
    setKeywords(keyword);
  }

  public String getName()
  {
    return name;
  }

  public ArrayList<Disease> getReDisease() {
    return ReDisease;
  }
}
