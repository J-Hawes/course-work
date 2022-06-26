package test.part3;

public class Car extends Vehicle {
    
    public Car (String owner, String regNum, Location location) {
        super(owner, regNum, location);
    }
    
    @Override
    public double calcRegfee() {
        double regFee = 0;
        Location regLocation = super.getLocation();
        if (regLocation == Location.INNER_METRO) {
            regFee = 800.00;
        }
        if (regLocation == Location.OUTER_METRO) {
            regFee = 750.00;
        }
        if (regLocation == Location.REGIONAL) {
            regFee = 690.00;
        }
        return regFee;
    }
}