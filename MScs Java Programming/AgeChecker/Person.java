package AgeChecker;

public class Person
{
    private int age;
    private String gender;

    public Person()
    {

    }

    public Person(int age, String gender)
    {
        this.age = age;
        this.gender = gender;
    }

    public String Display()
    {
        return ("Age : " + age + "\nGender : " + gender);
    }

    public int getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
