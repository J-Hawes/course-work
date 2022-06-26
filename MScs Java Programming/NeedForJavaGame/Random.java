package NeedForJavaGame;
/**
* Class that handles the Random number generation
*
* @author James Hawes
* @version 1.0.0
*/
public class Random {
    
    private int randomNumber;

    /**
    * Default empty constructor that creates an object of the class Random
    *
    */
    public Random()
    {

    }

    /**
    * Non default constructor that creates an object of the class Random
    * assigning the field variable with a random number between the range
    *
    * @param min        The minimum value range as an int value
    * @param max        The maximum value range as an int value
    */
    public Random(int min, int max)
    {
        this.randomNumber = (int)(Math.random() * (max - min + 1) + min);
    }

    /**
    * Method to display the current state of the object
    *
    * @return Current state of the object as a String
    */
    public String display()
    {
        return "" + randomNumber;
    }

    /**
    * Accessor method to get the random number
    *
    * @return     The number generated between the min and max values as an int
    */
    public int getRandom()
    {
        return randomNumber;
    }

    /**
    * Mutator method to set the random number to a new number in a new range
    *
    * @param min        The minimum value range as an int value
    * @param max        The maximum value range as an int value
    */
    public void setRandom(int min, int max)
    {
        this.randomNumber = (int)(Math.random() * (max - min) + min);
    }
}
