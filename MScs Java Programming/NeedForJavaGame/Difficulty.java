package NeedForJavaGame;

/**
* Class that handles the difficulty settings of the game
*
* @author James Hawes
* @version 1.0.0
*/
public class Difficulty
{
    private String name;
    private int highway;
    private double fuel;
    private int obstacles;

    /**
    * Default empty constructor that creates an object of the class Difficulty
    *
    */
    public Difficulty()
    {

    }

    /**
    * Non default constructor that creates an object of the class Difficulty
    *
    * @param name       The name of the Difficulty as a String
    * @param min        The minimum length of the highway as an int value
    * @param max        The maximum length of the highway as an int value
    * @param fuel       The amount of fuel as a double value
    * @param obstacles  The number of obstacles as an int value
    */
    public Difficulty(String name, int min, int max, 
        double fuel, int obstacles)
    {
        Random random = new Random(min, max);
        this.name = name;
        this.highway = random.getRandom();
        this.fuel = fuel;
        this.obstacles = obstacles;
    }

    /**
    * Method to display the current state of the object
    *
    * @return Current state of the object as a String
    */
    public String display()
    {
        return ("Difficulty : " + name + "\n"
        + "Highway Length: " + highway + "\n" 
        + "Starting Fuel: " + fuel * 100 + "%\n"
        + "Number of obstacles: " + obstacles);
    }

    /**
    * Accessor method to get difficulty name
    *
    * @return  A String representation of the select difficulty name
    */
    public String getDifficultyName()
    {
        return name;
    }

    /**
    * Accessor method to get starting fuel
    *
    * @return  Starting fuel value as double value
    */
    public double getStartingFuel()
    {
        return fuel;
    }

    /**
    * Accessor method to get highway length
    *
    * @return  Highway length as int value
    */
    public int getHighwayLength()
    {
        return highway; 
    }

    /**
    * Accessor method to get number of obstacles
    *
    * @return  Number of obstacles as an int value
    */
    public int getObstacles()
    {
        return obstacles; 
    }

    /**
    * Mutator method to set the Difficulty name
    *
    * @param name  Diffculty name as a String
    */
    public void setDifficultyName(String name)
    {
        this.name = name;
    }
    
    /**
    * Mutator method to set the starting fuel value
    *
    * @param fuel  Starting fuel as a double value
    */
    public void setStartingFuel(double fuel)
    {
        this.fuel = fuel;
    }

    /**
    * Mutator method to set the random highway length
    *
    * @param min   Minimum highway length as an int value
    * @param max   Maximum highway length as an int value
    */
    public void setHighwayLength(int min, int max)
    {
        Random random = new Random(min, max);
        this.highway = random.getRandom();
    }

    /**
    * Mutator method to set the number of obstacles
    *
    * @param obstacles Number of obstacles as an int value
    */
    public void setObstacles(int obstacles)
    {
        this.obstacles = obstacles;
    }
}