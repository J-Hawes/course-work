package RollingProject;

public class Unit
{
    private String unitCode;
    private String unitDescription;
    private int creditPoints;

    public Unit()
    {
        unitCode = "default";
        unitDescription = "default";
        creditPoints = 0;
    }

    public Unit(String newUnitCode, String newUnitDescription, int newCreditPoints)
    {
        this.unitCode = newUnitCode;
        this.unitDescription = newUnitDescription;
        this.creditPoints = newCreditPoints;
    }

    public String display()
    {
        return "Unit code : " + unitCode + "\nUnit Description : " + unitDescription + 
            "\nCredit Points : " + creditPoints;
    }

    public int getCreditPoints()
    {
        return creditPoints;
    }

    public String getUnitCode()
    {
        return unitCode;
    }

    public String getUnitDescription()
    {
        return unitDescription;
    }

    public void setCreditPoints(int newCreditPoints)
    {
        this.creditPoints = newCreditPoints;
    }

    public void setUnitCode(String newUnitCode)
    {
        this.unitCode = newUnitCode;
    }

    public void setUnitDescription(String newUnitDescription)
    {
        this.unitDescription = newUnitDescription;
    }    
}