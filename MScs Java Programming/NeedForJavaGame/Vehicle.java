package NeedForJavaGame;

/**
* Class that handles the vehicle settings of the game
*
* @author James Hawes
* @version 1.0.0
*/
public class Vehicle
{
    private String type;
    private int boostSpeed;
    private int maxFuel;
    private int maxDamage;
    private int currentFuel;
    private int currentDamage;
    private int[] location;
    
    /**
    * Default empty constructor that creates an object of the class Vehicle
    *
    */
    public Vehicle()
    {
        Random random = new Random(0,2);
        this.location = new int[2];
        this.location[0] = 0;
        this.location[1] = random.getRandom();

    }

    /**
    * Non default constructor that creates and object of the class Vehicle
    *
    * @param type           The name of the Vehicle type as a String
    * @param boostSpeed     The boost speed of the vehicle as an int value
    * @param maxFuel        The maximum fuel capacity as an int value
    * @param maxDamage      The maximum damage capacity as an int value
    */
    public Vehicle(String type, int boostSpeed, int maxFuel, int maxDamage)
    {
        Random random = new Random(0,2);
        this.type = type;
        this.boostSpeed = boostSpeed;
        this.maxFuel = maxFuel;
        this.maxDamage = maxDamage;
        this.currentFuel = 0;
        this.currentDamage = 0;
        this.location = new int[2];
        this.location[0] = random.getRandom();
        this.location[1] = 0;
    }

    /**
    * Method to display the current state of the object
    *
    * @return Current state of the object as a String
    */
    public String display()
    {
        return ("Vehicle: " + type + ", Boost Speed: " + boostSpeed
            + "\nCurrent Fuel:\t " + currentFuel + 
            ", Maximum Fuel Capacity:  " + maxFuel + "\nCurrent Damage:\t  "
            + currentDamage + ", Maximum Damage Allowed:  " + maxDamage);
    }

    /**
    * Accessor method to get the boost speed
    *
    * @return  Boost speed of the vehicle as an int value
    */
    public int getBoostSpeed()
    {
        return boostSpeed;
    }

    /**
    * Accessor method to get the current damage
    *
    * @return  Current damage of the vehicle as an int value
    */
    public int getCurrentDamage()
    {
        return currentDamage;
    }

    /**
    * Accessor method to get the current fuel
    *
    * @return  Current fuel of the vehicle as an int value
    */
    public int getCurrentFuel()
    {
        return currentFuel;
    }

     /**
    * Accessor method to get the player location
    *
    * @return  Location of the player's vehicle as an int array [y,x]
    */
    public int[] getLocation()
    {
        return location;
    }

    /**
    * Accessor method to get maximum damage capacity
    *
    * @return  Maximum damage capacity of the vehicle as an int value
    */
    public int getMaxDamage()
    {
        return maxDamage;
    }

    /**
    * Accessor method to get maximum fuel capacity
    *
    * @return  Maximum fuel capacity of the vehicle as an int value
    */
    public int getMaxFuel()
    {
        return maxFuel;
    }

    /**
    * Accessor method to get vehicle type
    *
    * @return  A String representation of the vehicle type
    */
    public String getVehicleType()
    {
        return type;
    }

    /**
    * Mutator method to set the boost speed value
    *
    * @param boostSpeed  Boost speed as an int value
    */
    public void setBoostSpeed(int boostSpeed)
    {
        this.boostSpeed = boostSpeed;
    }

    /**
    * Mutator method to set the current damage value
    *
    * @param damage  Current damage as an int value
    */
    public void setCurrentDamage(int damage)
    {
        this.currentDamage = damage;
    }

    /**
    * Mutator method to set the current fuel value
    *
    * @param fuel  Current fuel as an int value
    */
    public void setCurrentFuel(int fuel)
    {
        if(fuel > this.maxFuel)
        {
            this.currentFuel = this.maxFuel;
        }
        else
        {
            this.currentFuel = fuel;
        }
        
    }

    /**
    * Mutator method to set the maximum damage value
    *
    * @param maxDamage  Maximum damage as an int value
    */
    public void setMaxDamage(int maxDamage)
    {
        this.maxDamage = maxDamage;
    }

    /**
    * Mutator method to set the maximum fuel value
    *
    * @param maxFuel  Maximum fuel as an int value
    */
    public void setMaxFuel(int maxFuel)
    {
        this.maxFuel = maxFuel;
    }

    /**
    * Mutator method to set the player location
    *
    * @param y  The Y axis location of the player as an int value
    * @param x  The X axis location of the player as an int value
    */
    public void setLocation(int y, int x)
    {
        this.location[0] = y;
        this.location[1] = x;
    }

    /**
    * Mutator method to set the Starting fuel value cast as int
    *
    * @param fuel  Starting fuel multiplier as a double value
    */
    public void setStartingFuel(double fuel)
    {
        this.currentFuel = (int)(maxFuel * fuel);
    }

    /**
    * Mutator method to set the current vehicle type
    *
    * @param type  Current vehicle type as a String
    */
    public void setVehicleType(String type)
    {
        this.type = type;
    }
}