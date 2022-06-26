package RollingProject;

public class Student 
{
    private String name;
    private String address;
    private String phoneNo;
    private String email;

    public Student()
    {
        name = "default";
        address = "default";
        phoneNo = "default";
        email = "default";
    } 

    public Student(String newName, String newAddress,
        String newPhoneNo, String newEmail)
    {
        this.name = newName;
        this.address = newAddress;
        this.phoneNo = newPhoneNo;
        this.email = newEmail;

    }

    public String display()
    {
        return "Student Name : " + name + "\nAddress : " + address + 
        "\nPhone Number : " + phoneNo + "\nEmail : " + email;
    }

    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }   

}
