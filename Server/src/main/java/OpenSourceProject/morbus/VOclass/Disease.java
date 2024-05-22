package OpenSourceProject.morbus.VOclass;

public class Disease {

        //info, prevent, solution
    private final String name;
    private final String hospital;
    private final String selfTreatment;
    private final String briefInfo;
    private final String description;

    public Disease(String name, String hospital, String selfTreatment, String briefInfo, String description)
    {
       this.name = name;
       this.hospital = hospital;
       this.selfTreatment = selfTreatment;
       this.briefInfo = briefInfo;
       this.description = description;
    }


    public String getName() {return name;}

    public String getBriefInfo()
    {
        return briefInfo;
    }

    public String getDescription()
    {
     return description;
    }

    public String getHospital(){return hospital;}

    public String getSelfTreatment(){return selfTreatment;}

}
