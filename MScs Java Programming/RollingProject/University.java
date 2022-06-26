package RollingProject;

import java.io.IOException;
import java.util.ArrayList;

public class University
{
    private ArrayList<Enrolment> enrolments;
    private final String INPUT_FILE = "students.txt";
    private final String OUTPUT_FILE = "output.txt";

    public University()
    {
        enrolments = new ArrayList<Enrolment>();
    }

    public University(ArrayList<Enrolment> enrolment)
    {
        this.enrolments = enrolment;
    }

    public void addEnrolment(Enrolment enrolment)
    {
        enrolments.add(enrolment);
    }
    
    public String Display()
    {
        String display = "Details : \n";
        for (int i = 0; i < enrolments.size(); i++)
        {
            display += enrolments.get(i).display() + "\n";
        }
        return display;
    }

    public ArrayList<Enrolment> getEnrolments()
    {
        return enrolments;
    }

    public int getEnrolmentSize()
    {
        return enrolments.size();
    }

    public Enrolment getSpecificEnrolment(int index)
    {
        return enrolments.get(index);
    }

    public void inputStudentDetails(Enrolment enrolment)
    {
        Input input = new Input();
        Validation valid = new Validation();
        String name = "";
        boolean isValid = false;
        do
        {
            name = (input.acceptStringInput("Please enter a students name :"));
            if (valid.isBlank(name))
                {
                    System.out.println("Name can not be blank.\nTry again\n");
                }
            else
                {
                    isValid = true;
                }
        } while(isValid == false);

        String address = "";
        isValid = false;
        do
        {
            address = (input.acceptStringInput("Please enter a students address :"));
            if (valid.isBlank(address))
                {
                    System.out.println("Address can not be blank.\nTry again\n");
                }
            else
                {
                    isValid = true;
                }
        } while(isValid == false);

        String phone = "";
        isValid = false;
        do
        {
            phone = (input.acceptStringInput("Please enter a students phone no :"));
            if (valid.isBlank(phone))
                {
                    System.out.println("Phone number can not be blank.\nTry again\n");
                }
            else
                {
                    isValid = true;
                }
        } while(isValid == false);

        String email = "";
        isValid = false;
        do
        {
            email = (input.acceptStringInput("Please enter a students email :"));
            if (valid.isBlank(email))
                {
                    System.out.println("Email can not be blank.\nTry again\n");
                }
            else
                {
                    isValid = true;
                }
        } while(isValid == false);

        enrolment.createStudent(name, address, phone, email);
        System.out.println("The student has been created.");

    }

    public void inputUnitDetails(Enrolment enrolment)
    {
        Input input = new Input();
        int noUnits = 0;
        try
        {
            noUnits = input.acceptIntegerInput("Please enter the number of units to enrol");
            enrolment.setUnitsSize(noUnits);
            for(int i = 0; i < noUnits; i++)
            {
                inputUnitDetailsOnce(noUnits-1, enrolment);
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Please enter a number only");
        }
        
    }
    
    public void inputUnitDetailsOnce(int index, Enrolment enrolment)
    {
        Input input = new Input();
        Validation valid = new Validation();
        String unitCode = "";
        boolean isValid = false;
        do
        {
            unitCode = (input.acceptStringInput("Please enter a unit code :"));
            if (valid.lengthWithinRange(unitCode, 7, 7))
                {
                    isValid = true;
                }
            else
                {
                    System.out.println("Unit code must be 7 characters long.\n");
                }
        } while(isValid == false);

        String unitDesc = "";
        isValid = false;
        do
        {
            unitDesc = (input.acceptStringInput("Please enter a unit description :"));
            if (valid.isBlank(unitDesc))
                {
                    System.out.println("Unit description can not be blank.\nTry again\n");
                }
            else if (valid.lengthWithinRange(unitDesc, 0, 25))
                {
                    isValid = true;
                }
            else
                {
                    System.out.println("Unit description must be more than 25 characters long.\n");
                }
        } while(isValid == false);

        String creditPoints = "";
        int credit = 0;
        do
        {
            creditPoints = (input.acceptStringInput("Please enter the unit credit points :"));
            try
                {
                    credit = Integer.parseInt(creditPoints);
                    isValid = true;
                }
            catch (NumberFormatException ex)
                {
                    System.out.println("Please enter a valid credit point number");
                    isValid = false;
                }
        } while(isValid == false);
        enrolment.setSpecificUnit(index, unitCode, unitDesc, credit);
        System.out.println("Unit successfully created");

    }

    public static void main(String[] args)
    {
        University uni = new University();
        uni.startProgram();
    }

    private void readFile()
    {
        FileIO file = new FileIO(INPUT_FILE);
        try
        {
            String[] contents = file.readFile().split("\\n");
            for(int i = 0; i < contents.length; i++)
            {
                String[] input = contents[i].split(",");
                Enrolment enrolment = new Enrolment();
                enrolment.setDate(input[0]);
                enrolment.createStudent(input[1], input[2], input[3], input[4]);

                String[] unitInput = input[5].split(";");
                enrolment.setUnitsSize(unitInput.length);
                for(int j = 0; j < unitInput.length; j++)
                {
                    String[] units = unitInput[j].split("-");
                    enrolment.setSpecificUnit(j, units[0],units[1],Integer.parseInt(units[2]));
                }
                enrolments.add(enrolment);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Error reading file...exiting");
            System.exit(0);
        }
    }

    public void removeEnrolment(int index)
    {
        if (index <= 0 && index > enrolments.size())
        {
            enrolments.remove(index);
        }
    }

    public void setEnrolments(ArrayList<Enrolment> enrolment)
    {
        this.enrolments = enrolment;
    }

    public void setSpecificEnrolment(int index, Enrolment enrolment)
    {
        if (enrolments.size() >= index)
        {
            enrolments.set(index, enrolment);
        }
    }

    public void startProgram()
    {
        readFile();
        Input input = new Input();
        Validation valid = new Validation();
        Enrolment enrolment = new Enrolment();
        int choice = 0;
        boolean proceed = false;
        while(!proceed)
        {
            try
            {
                choice = input.acceptIntegerInput("Please make a choice :\n[1] To enrol a new student\n[2] To exit");
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter either 1 or 2 only");
            }
            if(choice == 1 || choice ==2)
            {
                proceed = true;
            }
        } 
        switch (choice)
        {
            case 1:
            String date = input.acceptStringInput("Please enter a date:\nLeave blank for today's date");
            if (valid.isBlank(date))
            {
                System.out.println("Assigning today's date");
            }
            else
            {
                enrolment.setDate(date);
                
            }
            inputStudentDetails(enrolment);
            inputUnitDetails(enrolment);
            enrolments.add(enrolment);
            System.out.println("\nSuccessfully enrolled\n" + enrolment.display());
            break;

            case 2: 
            break;
        }
        writeFile();
    }

    public void writeFile()
    {
        FileIO file = new FileIO(OUTPUT_FILE);
        String output = "";
        for(int i = 0; i < enrolments.size(); i++)
        {
            output += enrolments.get(i).display() + "\r\n\n";
        }
        try
        {
            file.writeFile(output);
        }
        catch (IOException ex)
        {
            System.out.println("Error reading file...exiting");
            System.exit(0);
        }
    }
}
