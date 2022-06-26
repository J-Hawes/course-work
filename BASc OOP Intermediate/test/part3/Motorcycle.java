package test.part3;

public class Motorcycle extends Vehicle {
    
    private int engCapacity;

    public Motorcycle(String owner, String regNum, Location location, int engCapacity) {
        super(owner, regNum, location);
        this.engCapacity = engCapacity;

    }

    @Override
    public double calcRegfee() {
        double regFee = 0;
        Location regLocation = super.getLocation();
        if (engCapacity < 100)
        {
            if (regLocation == Location.INNER_METRO) {
                regFee = 250.00;
            }
            else if (regLocation == Location.OUTER_METRO) {
                regFee = 230.00;
            }
            else if (regLocation == Location.REGIONAL) {
                regFee = 205.00;
            }
        }
        else
        {
            if (regLocation == Location.INNER_METRO) {
                regFee = 500.00;
            }
            else if (regLocation == Location.OUTER_METRO) {
                regFee = 465.00;
            }
            else if (regLocation == Location.REGIONAL) {
                regFee = 420.00;
            }
        }
        return regFee;
    }

    public int getEngCapacity()
    {
        return engCapacity;
    }

    public void setEngCapacity(int engine)
    {
        this.engCapacity = engine;
    }

    @Override
    public String toString() {
        
        return "Name: " + getOwner() + "\n" +
                "Registration number: " + getRegNum() + "\n" + 
                "Location: " + getLocation() + "\n"  + 
                "Engine size: " + getEngCapacity() + "cc\n";
    }
}
