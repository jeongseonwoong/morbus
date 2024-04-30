package OpenSourceProject.VOclass;

public class Disease {

        //info, prevent, solution
    private final String name;
    private final String briefInfo;
    private final String description;



    public Disease(String name, String briefInfo, String description)
    {
       this.name = name;
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

}
