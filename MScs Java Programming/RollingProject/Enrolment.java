package RollingProject;

import java.time.*;


public class Enrolment
{
    private String date;
    private Student student;
    private Unit[] units;

    public Enrolment()
    {
        date = LocalDate.now().toString();
        student = new Student();
        units = new Unit[5];
    }

    public Enrolment(String newDate, Student newStudent, Unit[] newUnits)
    {
        this.date = newDate;
        this.student = newStudent;
        this.units = newUnits;
    }

    public void createStudent(String name, String address, String phone, String email)
    {
        student = new Student(name, address, phone,email);
    }

    public String display()
    {
        String display = "Date : " + date; 
        for (int i = 0; i < units.length; i++)
        {
            display += "\n" + units[i].display();
        } 
        display += "\n" + student.display();
        return display;
    }

    public String getDate()
    {
        return date;
    }

    public Unit getSpecificUnit(int index)
    {
        return units[index];
    }

    public Student getStudent()
    {
        return student;
    }

    public Unit[] getUnits()
    {
        return units;
    }

    public int getUnitsSize()
    {
        return units.length;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setSpecificUnit(int index, String code, String desc, int pts)
    {
        this.units[index] = new Unit(code, desc, pts);
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public void setUnits(Unit[] units)
    {
        this.units = units;
    }

    public void setUnitsSize(int size)
    {
        this.units = new Unit[size];
    }
}
