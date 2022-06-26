package test.part3;

public abstract class Vehicle {
    //instance variables
    private String owner;
    private String regNum;
    private Location location;
    
    protected enum Location {
        INNER_METRO, OUTER_METRO, REGIONAL
    }
    
    protected Vehicle(String owner, String regNum, Location location) {
        this.owner = owner;
        this.regNum = regNum;
        this.location = location;
    }

    //Abstract method to be overridden in child classes
    public abstract double calcRegfee(); 
    
    protected String getOwner() {
        return owner;
    }
    
    protected String getRegNum() {
        return regNum;
    }
    
    protected Location getLocation() {
        return location;
    }
    
    protected void setOwner(String owner) {
        this.owner = owner;
    }
    
    protected void setRegNum(String reg) {
        this.regNum = reg;
    }
    
    protected void setLocation(Location location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        
        return "Name: " + getOwner() + "\n" +
                "Registration number: " + getRegNum() + "\n" + 
                "Location: " + getLocation() + "\n" ;
    }
    
}
