package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.algorithm.Disease;
import OpenSourceProject.morbus.algorithm.Symptom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SymptomSetting {

    private Scanner scanner;

    public Set<Symptom> setSymptom()
    {
        Set<Symptom> symptomList =new HashSet<Symptom>();
        try
        {
            scanner = new Scanner(new File("SymptomRelatedDisease.txt"));
            while(scanner.hasNextLine())
            {
                String symptomName= scanner.nextLine();;
                Set<Disease>associatingDisease =new HashSet<Disease>();
                while(!scanner.nextLine().equals("\n"))
                {
                    String RelatedDisease = scanner.nextLine();
                    Disease disease = new Disease(RelatedDisease);
                    associatingDisease.add(disease);
                }
                Symptom newSymptom=new Symptom(symptomName,associatingDisease);
                symptomList.add(newSymptom);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        return  symptomList;
    }


}
