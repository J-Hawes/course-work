package NeedForJavaGame;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
/**
* Class that handles the reading and writing of files
*
* @author James Hawes
* @version 1.0.0
*/
public class FileIO
{
    private final String INPUT_FILE = "vehicles.txt";
    private final String OUTPUT_FILE = "output.txt";

    /**
    * Default constructor that creates an object of the class FileIO
    *
    */
    public FileIO()
    {
     
    }

    /**
    * Method to read from a file and return a String value
    *
    * @return               File contents as a String value
    * @throws IOException   If any read errors occur
    */
    public String loadFile() throws IOException
    {
        FileReader reader = new FileReader(INPUT_FILE);
        BufferedReader buffer = new BufferedReader(reader);
        String line = null;
        String contents = "";
        while ((line = buffer.readLine()) != null)
        {
            contents += line + "\n";
        }
        buffer.close();
        return contents;
    }

    /**
    * Method to write to a file
    *
    * @param value          Data to be written to file as a String value
    * @throws IOException   If any write errors occur
    */
    public void writeFile(String value) throws IOException
    {
        FileWriter writer = new FileWriter(OUTPUT_FILE);
        writer.write(value);
        writer.close();
    }
}