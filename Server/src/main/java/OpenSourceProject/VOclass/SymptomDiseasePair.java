package OpenSourceProject.VOclass;

import java.util.ArrayList;

public class SymptomDiseasePair {
    private String symName;
    private ArrayList<String> arrayList;

    public SymptomDiseasePair(String sym, ArrayList<String> arr)
    {
        symName=sym;
        arrayList=arr;
    }

    public String first()
    {
        return symName;
    }

    public ArrayList<String> second()
    {
        return arrayList;
    }

}
