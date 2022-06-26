package NeedForJavaGame;

import java.io.IOException;
import java.util.ArrayList;

/**
* Class that handles the main method and game
*
* @author James Hawes
* @version 1.0.0
*/
public class Game
{
    /**ANSI Escape code for red font in console*/
    public static final String ANSI_RED = "\u001B[31m";
    /**ANSI Escape code for yellow font in console*/
    public static final String ANSI_YELLOW = "\u001b[33m";
    /**ANSI Escape code to Reset the console colours to default*/
    public static final String ANSI_RESET = "\u001B[0m";
    private Difficulty difficulty;
    private String name;
    private Vehicle vehicle;
    private String[][] highway;
    private String message;

    /**
    * Default empty constructor that creates an object of the class Game
    *
    */
    public Game()
    {
        

    }

    /**
    * Non default constructor that creates an object of the class Game
    *
    * @param name           The name of the player as a String value
    * @param difficulty     The player difficulty as an object of {@link Difficulty}
    * @param vehicle        The player vehicle as an object of {@link Vehicle}
    */
    public Game(String name, Difficulty difficulty, Vehicle vehicle)
    {
        this.name = name;
        this.difficulty = difficulty;
        this.vehicle = vehicle;
    }
    
    /**
    * Method to prompt and accept input from user to assign player name
    *
    * @return           A String representation of the user name input
    */
    public String choosePlayerName()
    {
        Input input = new Input();
        Validation valid = new Validation();
        boolean isValid = false;
        System.out.println("Welcome ____!?");
        while (!isValid)
        {
            name = (input.acceptStringInput("Please enter your name:"));
            if (valid.isBlank(name))
            {
                  System.err.println(ANSI_RED + 
                  "Name can not be blank.\nTry again"
                  + ANSI_RESET);
            }
            else if (valid.isLength(name, 3, 12))
            {
                isValid = true;
            }
            else
            {
                System.err.println(ANSI_RED +
                "Name must be between 3 and 12 characters long."
                + ANSI_RESET);
            }
        }
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        return name;
    }

     /**
     * Method to compare various Game Over scenarios
     *
     * 
     * @return          Boolean value true if Game Over conditions are met
     */
    public boolean checkGameOver()
    {
        if(vehicle.getCurrentDamage() >= vehicle.getMaxDamage())
        {
            message = "Game Over, you totalled the car!";
            System.out.println(ANSI_RED + message + ANSI_RESET);
            return true;
        }
        else if(vehicle.getCurrentFuel() <= 0)
        {
            message = "You ran out of fuel..Game Over!";
            System.out.println(ANSI_RED + message + ANSI_RESET);
            return true;
        }
        else if (vehicle.getLocation()[1] == highway[0].length - 1)
        {
            message = " .__.\n(|  |)\tCongratulations!\n (  ) \t" 
                + name + "\n _)(_ \tYou Win";
            System.out.println(ANSI_YELLOW + message + ANSI_RESET);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
    * Method to display the current state of the object
    *
    * @return Current state of the object as a String
    */
    public String display()
    {
        String output = "Player Name : " + name + "\n" +
            difficulty.display() + "\n" + vehicle.display() + "\n";
        for(int i = 0; i < highway.length; i++)
        {
            for(int j = 0; j < highway[i].length; j++)
            {
                output += (highway[i][j]);
            }
            output += "\n";
        }    
        output += "\nOutput message: " + message + "\n";
        return output;
    }

    /**
     * Method to compare and eveluate obstacle pickups.
     *
     * @param road      Road segment for comparison as a String value
     * @return          An int array of values [fuel, damage]
     */
    public int[] compareRoad(String road)
    {
        int[] result = {0,0};
        switch (road)
        {
            case " B ":
            result[1] = 20;
            break;

            case " F ":
            result[0] = 10;
            break;

            case " O ":
            result[1] = 60;
            break;

            case " S ":
            result[1] = 45;
            break;
        }
        return result;


    }

    /**
    * Accessor method to get the player difficulty
    *
    * @return           Player {@link Difficulty} object
    */
    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    /**
    * Accessor method to get the generated highway
    *
    * @return       Generated highway as a 2 dimensional String array
    */
    public String[][] getHighway()
    {
        return highway;
    }

    /**
    * Accessor method to get a specific highway segment
    *
    * @param y      Location on y axis as int value
    * @param x      Location on x axis as int value
    * @return       Highway segment as a string value
    */
    public String getHighwaySegment(int y, int x)
    {
        return highway[y][x];
    }

    /**
    * Accessor method to get the message written to file
    *
    * @return       Message to be printed to file as a String
    */
    public String getMessage()
    {
        return message;
    }
    
    /**
    * Accessor method to get the player name
    *
    * @return           Player name as a String value
    */
    public String getName()
    {
        return name;
    }

    /**
    * Accessor method to get the player vehicle
    *
    * @return           Player {@link Vehicle} object
    */
    public Vehicle getVehicle()
    {
        return vehicle;
    }

    /**
     * Method to being the program.
     *
     * @param args      An array of Strings representing command line arguments.
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.startGame();
    }

    /**
     * Method to control players movement from selected options.
     *
     * @param ch        Player's choice as a charater value in upper case
     * @param x         Player's location on the x axis
     * @param y         Player's location on the y axis
     */
    public void movement(char ch, int y, int x)
    {
        switch(ch)
        {
            case 'B': //Boost
            if (x + vehicle.getBoostSpeed() < highway[1].length)
            {
                int fuel = 0;
                int damage = 0;
                for (int i = x; i < (x + vehicle.getBoostSpeed()); i++)
                {
                    fuel = fuel + compareRoad(highway[y][i + 1])[0];
                    damage = damage + compareRoad(highway[y][i + 1])[1];
                    setHighwaySegment(y, i + 1, "   ");
                }
                vehicle.setLocation(y, x + vehicle.getBoostSpeed());
                vehicle.setCurrentFuel(vehicle.getCurrentFuel() - 
                    (vehicle.getBoostSpeed() * 3) + fuel);
                vehicle.setCurrentDamage(vehicle.getCurrentDamage() + damage);
            }
            else
            {
                System.err.println(ANSI_RED + 
                    "Can not boost off the highway" + ANSI_RESET);
            }
            break;

            case 'D': //Down
            vehicle.setLocation(y + 1, x + 1);
            vehicle.setCurrentFuel(vehicle.getCurrentFuel() - 2
                + compareRoad(highway[y + 1][x + 1])[0]);
            vehicle.setCurrentDamage(vehicle.getCurrentDamage()
                + compareRoad(highway[y + 1][x + 1])[1]);
            break;

            case 'F': //Forward
            vehicle.setLocation(y, x + 1);
            vehicle.setCurrentFuel(vehicle.getCurrentFuel() - 1
                + compareRoad(highway[y][x + 1])[0]);
            vehicle.setCurrentDamage(vehicle.getCurrentDamage()
                + compareRoad(highway[y][x + 1])[1]);
            break;
            
            case 'Q': //Quit
            System.exit(0);

            case 'U': //Up
            vehicle.setLocation(y - 1, x + 1);
            vehicle.setCurrentFuel(vehicle.getCurrentFuel() - 2
                + compareRoad(highway[y - 1][x + 1])[0]);
            vehicle.setCurrentDamage(vehicle.getCurrentDamage()
                + compareRoad(highway[y - 1][x + 1])[1]);
            break;

            default:
            System.err.println(ANSI_RED + 
                "Please enter a correct choice.\nTry again"
                + ANSI_RESET);
            break;
        }
    }

    /**
     * Method to read the file and parse values to ArrayList of Vehicle.
     *
     * @return         Array list of {@link Vehicle} objects
     */
    public ArrayList<Vehicle> readVehicleFile()
    {
        FileIO file = new FileIO();
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try
        {
            
            String[] contents = file.loadFile().split("\\n");
            for (int i = 0; i < contents.length; i++)
            {
                String[] temp = contents[i].split(",");
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleType(temp[0]);
                vehicle.setBoostSpeed(Integer.parseInt(temp[1]));
                vehicle.setMaxFuel(Integer.parseInt(temp[2]));
                vehicle.setMaxDamage(Integer.parseInt(temp[3]));
                vehicleList.add(vehicle);
            }
        }
        catch (IOException ex)
        {
            System.err.println(ANSI_RED +
            "Error: Could not open file. Exiting ..."
            + ANSI_RESET);
            System.exit(0);
        }
        System.out.println("Vehicle list loaded!");
        return vehicleList;
    }

    /**
    * Method to prompt and accept input from user to assign difficulty level
    *
    * @return           The choosen {@link Difficulty} object
    */
    public Difficulty selectDifficulty()
    {
        System.out.println("Now...please choose a difficulty: ");
        System.out.println("Select (1) for an easy ride, a full tank of fuel");
        System.out.println("Select (2) for a moderate course with 80% fuel");
        System.out.println("Select (3) for challenge with half the fuel");
        int choice = 0;
        boolean isValid = false;
        Input input = new Input();
        while(!isValid)
        {
            try
            {
                choice = (input.acceptIntegerInput("So what will it be " 
                        + name +"?"));
            }
            catch (NumberFormatException ex)
            {
                System.err.println(ANSI_RED + 
                "Please enter a number only!"
                + ANSI_RESET);
                continue;
            }
            switch (choice)
            {
                case 1:
                difficulty = new Difficulty("Easy",10,15,1,12);
                isValid = true;
                break;

                case 2:
                difficulty = new Difficulty("Moderate",15,30,0.8,24);
                isValid = true;
                break;

                case 3:
                difficulty = new Difficulty("Hard",30,50,0.5,45);
                isValid = true;
                break;

                default:
                System.err.println(ANSI_RED + 
                "Please make a choice between 1, 2 and 3 only!"
                + ANSI_RESET);
                break;
            }
        }
        return difficulty;
    }

    /**
    * Method to prompt and accept input from user to assign vehicle type
    * 
    * @return           The choosen {@link Vehicle} object
    */
    public Vehicle selectVehicle()
    {
        ArrayList<Vehicle> vehicleList = new ArrayList<>(readVehicleFile());
        System.out.println("\nPlease select a numbered vehicle from below :");
        //format the output
        System.out.println("Numer\t\tType\t\tBoost Speed\tMaximum Fuel"+
            "\tMaximum Damage Capacity\n");
            for (int i = 0; i < vehicleList.size(); i++)
            {
                System.out.print("Vehicle (" + (i+1) + ") \t");
                System.out.printf("%-17s",
                vehicleList.get(i).getVehicleType());
                System.out.print("\t" 
                + vehicleList.get(i).getBoostSpeed() + "\t\t" 
                + vehicleList.get(i).getMaxFuel());
                System.out.printf("%20s", vehicleList.get(i).getMaxDamage());
                System.out.print("\n");
            }
        int choice = 0;
        boolean isValid = false;
        Input input = new Input();
        while(!isValid)
        {
            try
            {
                choice = (input.acceptIntegerInput("So what will it be " 
                        + name +"?"));
            }
            catch (NumberFormatException ex)
            {
                System.err.println(ANSI_RED + 
                "Please enter a number only!"
                + ANSI_RESET);
                continue;
            }
            if (choice >= 1 && choice <= 3)
            {
                choice = choice - 1;
                vehicle = new Vehicle(vehicleList.get(choice).getVehicleType(),
                vehicleList.get(choice).getBoostSpeed(), 
                vehicleList.get(choice).getMaxFuel(),
                vehicleList.get(choice).getMaxDamage());
                vehicle.setStartingFuel(difficulty.getStartingFuel());
                isValid = true;
            }
            else
            {
                System.err.println(ANSI_RED + 
                "Please make a choice between 1, 2 and 3 only!"
                + ANSI_RESET);
            }
        }
        return vehicle;
    }

    /**
    * Mutator method to set the player difficulty
    *
    * @param difficulty         Requires a Difficulty object
    */
    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }
    
    /**
    * Mutator method to set the highway
    *
    * @param highway     Highway as a 2 dimensional String array [lane][road]  
    */
    public void setHighway(String[][] highway)
    {
        this.highway = highway;
    }

    /**
    * Mutator method to set the highway
    *
    * @param y          Location on y axis as int value
    * @param x          Location on x axis as int value
    * @param segment    String representation of road segment
    */
    public void setHighwaySegment(int y, int x, String segment)
    {
        this.highway[y][x] = segment;
    }

    /**
    * mutator method to set the message written to file
    *
    * @param message       Message to be printed to file as a String
    */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
    * Mutator method to set the player name
    *
    * @param name           Player name as a String value
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
    * Mutator method to set the player vehicle
    *
    * @param vehicle            Requires a {@link Vehicle} object
    */
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    /**
    * Method to begin the game
    *
    */
    public void startGame()
    {
        //create player
        String name = this.choosePlayerName();
        System.out.println("\nWelcome " +name + "!"); 
        this.setDifficulty(this.selectDifficulty());
        this.setVehicle(this.selectVehicle());
        //create game board
        GameBoard gameboard = new GameBoard();
        gameboard.generateObstacles(this.difficulty.getObstacles());
        gameboard.generateHighway(this.difficulty.getHighwayLength());
        this.setHighway(gameboard.getHighway());
        //play game
        boolean gameOver = false;
        while(!gameOver)
        {
            System.out.println(this.vehicle.display());
            int y = this.vehicle.getLocation()[0];
            int x = this.vehicle.getLocation()[1];
            //set the lane first which is on the y axis (y, x)
            this.setHighwaySegment(y, x, ANSI_RED + 
                gameboard.PLAYER_ICON + ANSI_RESET);
            System.out.println(gameboard.viewport(x));
            //clear player icon ready for next iteration to simulate movement
            this.setHighwaySegment(y, x, gameboard.EMPTY_ROAD);
            Input console = new Input();
            Validation valid = new Validation();
            boolean isValid = false;
            String choice = "";
            System.out.println("Player: "+ ANSI_RED + "@" + 
                ANSI_RESET + "\tF: Fuel pick up(+10)");
            System.out.println("Damage:\t\tB: Roadblock(+20)\tS: " + 
                "Tyre Spikes(+45)\tO: Open Manhole(+60)");
            while (!isValid)
            {
                choice = (console.acceptStringInput("" + 
                    "Enter (F) to move forward, " + "(U) to swerve up, " + 
                    "(D) to swerve down, (B) to boost, (Q) to quit"));
                if (valid.isBlank(choice))
                {
                    System.err.println(ANSI_RED + 
                    "Please enter your choice.\nTry again"
                    + ANSI_RESET);
                }
                //validate if player can move up or down
                else if (choice.toUpperCase().charAt(0) == 'D' 
                && this.vehicle.getLocation()[0] == 2 || 
                choice.toUpperCase().charAt(0) == 'U' 
                && this.vehicle.getLocation()[0] == 0)  
                {
                    System.err.println(ANSI_RED +
                    "Can not drive off the highway, please pick another direction"
                    + ANSI_RESET);
                }
                //validate if matches only the available options
                else if (choice.matches("^[BbDdFfQqUu]"))
                {
                    isValid = true;
                }
                else
                {
                    System.err.println(ANSI_RED + 
                    "Please enter your choice from the options available."
                    + "\nTry again"
                    + ANSI_RESET);
                }
            }
            char ch = choice.toUpperCase().charAt(0);
            this.movement(ch, y, x);
            gameOver = this.checkGameOver();
        }
        this.setHighwaySegment(this.vehicle.getLocation()[0], 
            this.vehicle.getLocation()[1], ANSI_RED + 
            gameboard.PLAYER_ICON + ANSI_RESET);
        System.out.println(gameboard.viewport(this.vehicle.getLocation()[1]));
        String totalDistance = "\nYou travelled " 
            + (this.vehicle.getLocation()[1] + 1) + " sections of highway";
        System.out.println(totalDistance);
        this.setMessage(this.getMessage() + totalDistance);
        this.writeFile(this.message);
    }

    /**
     * Method to write message to file upon game completion.
     *
     * @param message       The content to be written to the file as a String
     */
    public void writeFile(String message)
    {
        FileIO file = new FileIO();
        try
        {
            file.writeFile(message);
        }
        catch (IOException ex)
        {
            System.err.println(ANSI_RED +
            "Error: Could not open file. Exiting ..."
            + ANSI_RESET);
            System.exit(0);
        }
        System.out.println("Successfully written to file!");
    }
}