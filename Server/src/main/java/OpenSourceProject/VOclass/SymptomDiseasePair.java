package OpenSourceProject.VOclass;

import java.util.ArrayList;

public class SymptomDiseasePair {
    private String symName;
    private ArrayList<Disease> arrayList;

    public SymptomDiseasePair(String sym, ArrayList<Disease> arr)
    {
        symName=sym;
        arrayList=arr;
    }

    public String first()
    {
        return symName;
    }

    public ArrayList<Disease> second()
    {
        return arrayList;
    }

}
