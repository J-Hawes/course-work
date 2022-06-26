package Employees;

public class Employees
{
    private String name;
    private double hourlyRate;
    private String skill;

    public Employees()
    {
        name = "unknown";
        hourlyRate = 0.00;
        skill = "unknown";
    }

    public Employees(String name, double rate, String skill)
    {
        this.name = name;
        this.hourlyRate = rate;
        this.skill = skill;
    }

    public double getHourlyRate()
    {
        return hourlyRate;
    }

    public String getName()
    {
        return name;
    }

    public String getSkill()
    {
        return skill;
    }

    public void setHourlyRate(double rate)
    {
        this.hourlyRate = rate;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSkill(String skill)
    {
        this.skill = skill;
    }
}
