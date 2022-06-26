package Employees;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileIO
{
    private final String FILE = "employees.txt";
    private ArrayList<Employees> employees;

    public FileIO()
    {
        this.employees = new ArrayList<Employees>();
    }

    public int getLength()
    {
        return employees.size();
    }

    public Employees getEmployee(int index)
    {
        return employees.get(index);
    }

    public void loadFile() throws IOException
    {
        FileReader filereader = new FileReader(FILE);
        BufferedReader buffer = new BufferedReader(filereader);
        String line = null;
        while ((line = buffer.readLine()) != null)
        {
            String[] temp = line.split(",");
            String name = temp[0];
            double rate = Double.parseDouble(temp[1]);
            String skill = temp[2];
            employees.add(new Employees(name, rate, skill));
        }
        buffer.close();
    }

}
