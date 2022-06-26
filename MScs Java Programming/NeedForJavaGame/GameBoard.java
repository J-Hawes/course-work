package NeedForJavaGame;

/**
* Class that handles the Player settings of the game
*
* @author James Hawes
* @version 1.0.0
*/
public class GameBoard
{    
    /**String representation of a road way dividing lane*/
    public final String DIVIDING_LANE = " -  -  -  -  -  -  -  -  -  -";
    /**String representing an empty road segment*/
    public final String EMPTY_ROAD = "   ";
    /**String representing the player icon*/
    public final String PLAYER_ICON = " @ ";
    /**String representation of the road boundary*/
    public final String ROAD_BOUNDARY = "=============================";
    private String[] obstacles;
    private String[][] highway;
    
    /**
    * Default empty constructor that creates an object of the class Player
    *
    */
    public GameBoard()
    {

    }

    /**
    * Method to display the current state of the object
    *
    * @return       Current state of the object as a String
    */   
    public String display()
    {
        String output = ROAD_BOUNDARY + "\n";
        for(int i = 0; i < highway[0].length; i++)
        {
            output += highway[0][i];
        }
        output += "\n" + DIVIDING_LANE + "\n";
        for(int i = 0; i < highway[1].length; i++)
        {
            output += highway[1][i];
        }
        output += "\n" + DIVIDING_LANE + "\n";
        for(int i = 0; i < highway[2].length; i++)
        {
            output += highway[2][i];
        }
        output += "\n" + ROAD_BOUNDARY;
        return output;       
    }

    /**
    * Method to generate the playable highway
    *
    * @param highwayLength    Length of the highway as an int value
    * @return highway         Generated highway as a 2 Dimensional String array
    */
    public String[][] generateHighway(int highwayLength)
    {
        highway = new String[3][highwayLength]; 
        for (int i = 0; i < highway.length; i++)
        {
            for(int j = 0; j < highway[i].length; j++)
            {
                highway[i][j] = EMPTY_ROAD;
            }
        }
        int obsCounter = 0;
        for (int i = 0; i < highway.length; i++)
        {
            for(int j = 0; j < highway[i].length; j++) 
            {   
                //33% chance, with first 3 sections blank
                Random random = new Random(1,3);
                if (random.getRandom() == 1 && j > 2 
                    && obsCounter < obstacles.length)
                {
                    highway[i][j] = obstacles[obsCounter];
                    obsCounter++;
                }
                
            }
        }
        return highway;
    }

    /**
    * Method to generate the obstacles
    *
    * @param maxObstacles       Number of obstacles as an int value
    * @return obstacles         Generated obstacles as a String array
    */
    public String[] generateObstacles(int maxObstacles)
    {
        obstacles = new String[maxObstacles];
        for (int i = 0; i < obstacles.length; i++)
        {
            Random random = new Random(0, 100);
            int rand = random.getRandom(); //Probability total 100%
            if (rand >= 0 && rand < 10) //Probability of 10%
            {
                obstacles[i] = " O ";
            }
            else if (rand >= 10 && rand < 30) //Probability of 20%
            {
                obstacles[i] = " S ";
            }
            else if (rand >= 30 && rand < 60) //Probability of 30%
            {
                obstacles[i] = " F ";
            }
            else if (rand >= 60 && rand <= 100) //Probability of 40%
            {
                obstacles[i] = " B ";
            }
        }
        return obstacles;
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
    * Accessor method to get the generated obstacles
    *
    * @return       Generated obstacles as a String array
    */
    public String[] getObstacles()
    {
        return obstacles;
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
    * Mutator method to set the generated obstacles
    *
    * @param  String        array of obstacles
    */
    public void setObstacles(String[] obstacles)
    {
        this.obstacles = obstacles;
    }

    /**
    * Method to display the viewport of 10 highway segments
    *
    * @param index      Starting index of array as an int value
    * @return           Current viewport of highway object as a String
    */   
    public String viewport(int index)
    {   
        //configure length of 10 segments only
        int section = 0;
        int remaining = highway[0].length - index;
        if ((index + 9) < highway[0].length || 
            ((highway[0].length - index) > 10))
        {
            section = index + 10;
        }
        else if (remaining < 10)
        {
            
            section = highway[0].length;
            index = highway[0].length - 10;
        }
        else
        {
            section = remaining;
        }
        //Iterate array to display
        String viewport = ROAD_BOUNDARY + "\n";              
        for(int i = index; i < section; i++)
        {
            viewport += highway[0][i];
        }
        viewport += "\n" + DIVIDING_LANE + "\n";
        for(int i = index; i < section; i++)
        {
            viewport += highway[1][i];
        }
        viewport += "\n" + DIVIDING_LANE + "\n";
        for(int i = index; i < section; i++)
        {
            viewport += highway[2][i];
        }
        viewport += "\n" + ROAD_BOUNDARY;
        
        return viewport;       
    }
} 