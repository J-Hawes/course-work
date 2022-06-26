package NeedForJavaGame;

/**
* Class that handles the validation of user input
*
* @author James Hawes
* @version 1.0.0
*/
public class Validation
{
    /**
    * Default empty constructor that creates an object of the class Validation
    *
    */
    public Validation()
    {
        
    }

    /**
    * Method to eveluate the Length of a String is not zero
    *
    * @param value  A String needing validation
    * @return       Boolean value true if valid, false if not
    */
    public boolean isBlank(String value)
    {
        return (value.trim().length() == 0) ? true : false;
    }

    /**
    * Method to eveluate the length of a String within a given range
    *
    * @param value  A String needing validation
    * @param min    Minimum int range
    * @param max    Maximum int range
    * @return       Boolean value true if valid, false if not
    */
    public boolean isLength(String value, int min, int max)
    {
        return ((value.trim().length() >= min) 
            && (value.trim().length() <= max));
    }
}