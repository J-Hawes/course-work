package Book;

public class Author
{
    private String firstName;
    private String lastName;

    public Author()
    {
        firstName = "unknown";
        lastName = "unknown";
    }

    public Author(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void display()
    {
        System.out.println("First Name : " + firstName + "\nLast Name : " + lastName);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }

    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
}
