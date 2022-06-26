package RollingProject;

import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;

public class FileIO
{
    private String file;

    public FileIO()
    {
        this.file = "";
    }

    public FileIO(String file)
    {
        this.file = file;
    }

    public String getFileName()
    {
        return file;
    }

    public String readFile() throws IOException
    {
        FileReader reader = new FileReader(file);
        Scanner scan = new Scanner(reader);
        String contents = "";
        while (scan.hasNextLine())
        {
            contents += scan.nextLine() + "\n";
        }
        scan.close();
        return contents;
    }

    public void setFileName(String file)
    {
        this.file = file;
    }

    public void writeFile(String value) throws IOException
    {
        FileWriter writer = new FileWriter(file);
        writer.write(value);
        writer.close();
    }
}