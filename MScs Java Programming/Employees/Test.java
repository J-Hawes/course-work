package Employees;

import java.io.IOException;

public class Test
{
    public static void main(String[] args)
    {
        FileIO test = new FileIO();
        try
        {
            test.loadFile();
        }
        catch (IOException ex)
        {
            System.out.println("Error: Could not open file. Exiting ...");
            System.exit(0);
        }
            System.out.println("Loaded list of employees :");
            for (int i = 0; i < test.getLength(); i++)
            {
                System.out.println("Employee[" + i + "] " 
                + test.getEmployee(i).getName() + " : current hourly rate $"
                + test.getEmployee(i).getHourlyRate() + ": currently skilled in : "
                + test.getEmployee(i).getSkill());
            }
    }
}
