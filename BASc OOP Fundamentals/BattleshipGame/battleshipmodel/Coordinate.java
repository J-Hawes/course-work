package BattleshipGame.battleshipmodel;


// Class Coordinate contains all implementation for one coordinate of the ocean
// for any player. Can be references when dealing with a player's ocean or
// the opponent's "target" ocean.
public class Coordinate {
    
    //Instance variables.    
    private final String HIT = "\u001B[31m" + "X" + "\u001b[0m"; //prints a red "X"
    private final String MISS = "\u001b[37m" + "-" + "\u001b[0m"; //prints a white "-"
    private final String UNKNOWN = " ";
    private Ship ship;
    private boolean isHit;  
    
    // Constructor.

    public Coordinate()
    {
        Ship();
    }
    protected void Ship() {
        this.ship = null;
        this.isHit = false;
    }
    
    // Method getShip(): Accessor method.
    protected Ship getShip() {
        return ship;
    }
    // Method setShip(): Mutator method.    
    protected void setShip(Ship ship) {
        this.ship = ship;
    }
    
    //checks if the coordinate has a ship or not, returns false if none
    protected boolean hasShip() {
        if (ship != null) {
            return true;
        }
        return false;
    }
    
    // accessor method for isHit() method
    protected boolean isHit() {
        return isHit;
    }
  
    protected boolean tryHit() {
        isHit = true; 
        return hasShip();
    }

//     printTarget() method uses the following truth table 
//     to determine values of coordinates             isHit()
//                                                True      False
//                            hasShip()   true    HIT       UNKNOWN
//                                        false   MISS      UNKNOWN              
    public String printTarget() {
        if (hasShip() && isHit()) {
            return HIT;
        }
        else if (!hasShip() && isHit()) {
            return MISS;
        }
        else {
            return UNKNOWN;
        }
    }
    
//     printOcean() method uses the following truth table 
//     to determine values of coordinates             isHit()
//                                                True      False
//                            hasShip()   true    HIT       getInitial()
//                                        false   MISS      UNKNOWN   
    public  String printOcean() {
        if (hasShip() && isHit()) {
            return HIT;
        }
        else if (hasShip() && !isHit()) {
            return Character.toString(ship.getInitial());
        }
        else if (!hasShip() && isHit()) {
            return MISS;
        }
        else {
            return UNKNOWN;
        }
    }
    
}