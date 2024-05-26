package OpenSourceProject.morbus.VOclass;

import java.util.*;

public class Symptom {

  private String name;
  private final ArrayList<Disease> ReDisease = new ArrayList<Disease>();
  public Set<String>keywords = new HashSet<String>();

  private void setName(String inSym)
  {
    name=inSym;
  }

  private void setReDisease(ArrayList<Disease> reDisease)
  {
      ReDisease.addAll(reDisease);
  }

  private void setKeywords(ArrayList<String> array)
  {
      keywords.addAll(array);
  }

  public ArrayList<String> getKeywords() {return new ArrayList<>(keywords);}


  public void set(String inSym, ArrayList<Disease> relateDisease, ArrayList<String> keyword)
  {
    setName(inSym);
    setReDisease(relateDisease);
    setKeywords(keyword);
  }

  public String getName()
  {
    return name;
  }

  public ArrayList<Disease> getReDisease() {
    return ReDisease;
  }


  public Map<String,List<String>> getKeywordMap() {
        Map<String,List<String>>map=new HashMap<>();
        map.put(getName(),getKeywords());
        return map;
    }
}
