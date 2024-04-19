package OpenSourceProject.VOclass;

import OpenSourceProject.VOclass.Disease;

import java.util.ArrayList;

public class Symptom {

  private String name;
  private ArrayList<Disease> ReDisease = new ArrayList<Disease>();

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

  public void set(String inSym, ArrayList<String> array)
  {
    setName(inSym);
    setReDisease(array);
  }

  public String get()
  {
    return name;
  }

  public void printRelated()
  {
    for(Disease disease:ReDisease)
    {
      System.out.print(" " + disease.get() + "\n");
    }
  }

  public ArrayList<Disease> getReDisease() {
    return ReDisease;
  }
}
