package BattleshipGame.battleshipmodel;

import java.util.Random;

// Player class contains a default implementation for a player.
// This class could represent a simple human or artificial player.
public class Player {

    // Instance variables.
    private int OCEAN_SIZE = 10; //number of rows & columns in the ocean
    private int FLEET_SIZE = 5; //number of ships in the fleet
    private Coordinate[][] ocean;
    protected Coordinate[][] target;
    private Ship[] fleet;
    public String name;
    
    //constants representing ship in either direction
    private enum Direction {
        HORIZONTAL, VERTICAL;

        public Direction toggle(){
            return this.equals(HORIZONTAL) ? VERTICAL : HORIZONTAL;
        }
    }

    // Constructor.    
    public Player(String name) {
        this.name = name;
        this.ocean = new Coordinate[OCEAN_SIZE][OCEAN_SIZE];
        this.target = new Coordinate[OCEAN_SIZE][OCEAN_SIZE];
        this.fleet = new Ship[FLEET_SIZE];
        for (int row = 0; row < OCEAN_SIZE; row++) {
            for (int col = 0; col < OCEAN_SIZE; col++) {
                this.ocean[row][col] = new Coordinate();
            }
        }
    } 
    
    // Method setTarget(): Mutator method.     
    public void setTarget(Coordinate[][] target){
        this.target = target;
    }
    
    // Method getOcean(): Accessor method.    
    public Coordinate[][] getOcean() {
        return ocean;
    }
    
    // Method to check if surrounding cells are free for ship placement
    public boolean evaluateCell(int x, int y, int length, Direction dir)
    {
        
        boolean empty = true;
        for(int i = 0; i < length; i++)
        {
            if(dir == Direction.HORIZONTAL)
            {
                if(ocean[x][y].hasShip())
                {
                    empty = false;
                }
                x++;
            }
            else
            {
                if(ocean[x][y].hasShip())
                {
                    empty = false;
                }
                y++;
            }
        }
        return empty;
    }

    //places fleet of 5 ships at random locations and orientations
    public void placeFleet() {
        fleet[0] = new Ship("Aircraft Carrier", 5);
        fleet[1] = new Ship("Battleship", 4);
        fleet[2] = new Ship("Cruiser", 3);
        fleet[3] = new Ship("Submarine", 3);
        fleet[4] = new Ship("Destroyer", 2);

        //make sure all 5 ships are placed
        Direction dir = Direction.HORIZONTAL;
        for(int n = 0; n < fleet.length; n++)
        {
            Random rando = new Random();
            int x = rando.nextInt(OCEAN_SIZE);
            int y = rando.nextInt(OCEAN_SIZE);
            boolean empty = false;
            if(dir == Direction.HORIZONTAL){
                do
                {
                    while((x + fleet[n].getLength()) >= OCEAN_SIZE)
                    {
                        x = rando.nextInt(OCEAN_SIZE);
                    }
                    empty = evaluateCell(x, y, fleet[n].getLength(), dir);
                    if(!empty)
                    {
                        x = rando.nextInt(OCEAN_SIZE);
                    }
                } while (!empty);
                for(int i = 0; i < fleet[n].getLength(); i++)
                {
                    ocean[x][y].setShip(fleet[n]);
                    x++;
                }  
            } 
            else if(dir == Direction.VERTICAL){
                do
                {
                    while((y + fleet[n].getLength()) >= OCEAN_SIZE)
                    {
                        y = rando.nextInt(OCEAN_SIZE);
                    }
                    empty = evaluateCell(x, y, fleet[n].getLength(), dir);
                    if(!empty)
                    {
                        y = rando.nextInt(OCEAN_SIZE);
                    }
                } while (!empty);
                for(int i = 0; i < fleet[n].getLength(); i++)
                {
                    ocean[x][y].setShip(fleet[n]);
                    y++;
                }
            }
        dir = dir.toggle();
        }
        
    }
    
    //prints the target grid
    public void printTarget() {
        System.out.println("    0   1   2   3   4   5   6   7   8   9");
        System.out.println ("  +---+---+---+---+---+---+---+---+---+---+");
        for (int row = 0; row < OCEAN_SIZE; row++) {
            System.out.print(row + " |");
            for (int col = 0; col < OCEAN_SIZE; col++) {
                System.out.print (" " + target[row][col].printTarget() +" |");
            }
            System.out.println ("\n  +---+---+---+---+---+---+---+---+---+---+");
        }
    }

    //prints the ocean grid
    public void printOcean() {
        System.out.println("    0   1   2   3   4   5   6   7   8   9");
        System.out.println ("  +---+---+---+---+---+---+---+---+---+---+");
        for (int row = 0; row < OCEAN_SIZE; row++) {
            System.out.print(row + " |");
            for (int col = 0; col < OCEAN_SIZE; col++) {
                System.out.print (" " + ocean[row][col].printOcean() +" |");       
            }
            System.out.println ("\n  +---+---+---+---+---+---+---+---+---+---+");
        }
    }
    
    //chooses an unguessed coordinate at random
    public void takeGuess() {
        Random guess = new Random();
        int x, y;
        do
        {
            x = guess.nextInt(OCEAN_SIZE);
            y = guess.nextInt(OCEAN_SIZE);
        } while (target[x][y].isHit() == true);
        target[x][y].tryHit();
        
    }
    
    //returns true if all 17 ship spaces have been hit - need to fix this
    public boolean hasWon() {
        int count=0;
        boolean gameOver = false;
        for (int row = 0; row < OCEAN_SIZE; row++) {
            for (int col = 0; col < OCEAN_SIZE; col++) {
                if (target[row][col].hasShip() && target[row][col].isHit()) {
                    count++;
                    if (count == 17) {
                        gameOver = true;
                    }
                }
            }  
        }
        return gameOver;
        
    }
    
}