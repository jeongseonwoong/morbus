package OpenSourceProject.VOclass;

public class Disease {

        //info, prevent, solution
    private String name;
    private String briefInfo;
    private String description;


    public Disease(String inDis)
    {
        name=inDis;
    }

    public String getName()
    {
        return name;
    }

    public String getBriefInfo()
    {
        return briefInfo;
    }

    public String getDescription()
    {
     return description;
    }

}
