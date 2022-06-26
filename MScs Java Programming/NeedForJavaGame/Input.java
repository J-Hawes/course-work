package NeedForJavaGame;

import java.util.Scanner;
/**
* Class that handles the input of int and String values
*
* @author James Hawes
* @version 1.0.0
*/
public class Input
{
    
    /**
    * Default empty constructor that creates an object of the class Input
    *
    */
    public Input()
    {
        
    }

    /**
    * Method to accept user input for int values
    *
    * @param message    A String message to print to console
    * @return           Returns an int representation of the users input
    */
    public int acceptIntegerInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(console.nextLine());
    }

    /**
    * Method to accept use input for String values
    *
    * @param message    A String message to print to console
    * @return           Returns a String representation of the users input
    */
    public String acceptStringInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine();
    }
}