package employee;

import java.text.DecimalFormat;

public class PartTimeInstructor extends Employee {
    
    public PartTimeInstructor(String firstName, String lastName, 
            String taxFileNumber, double hourlyRateLecture, double hoursLectured, 
            double hourlyRateMarking, double hoursMarked) throws Exception {
        super(firstName, lastName, taxFileNumber);
        
        //Exception handling if value is a negative or zero
        if (hourlyRateLecture <= 0 ) {
            throw new Exception ("Hourly rate for Lecturning must be above zero!");
        }
        if (hoursLectured <= 0 ) {
            throw new Exception ("Hours worked for Lecturing must be more than zero!");
        }
        if (hourlyRateMarking <= 0 ) {
            throw new Exception ("Hourly rate for Marking must be above zero!");
        }
        if (hoursMarked <= 0 ) {
            throw new Exception ("Hours worked for Marking must be more than zero!");
        }
        
        this.hourlyRateLecture = hourlyRateLecture;
        this.hoursLectured = hoursLectured;
        this.hourlyRateMarking = hourlyRateMarking;
        this.hoursMarking = hoursMarked;
    }
    
    //instance variables
    private double hourlyRateLecture;
    private double hoursLectured;
    private double hourlyRateMarking;
    private double hoursMarking;
    DecimalFormat Currency = new DecimalFormat("$###,###.##");
    
    //method to set the hourly rate for lecturing
    protected void setRateLecturing(double hourlyRateLecture) {
        this.hourlyRateLecture = hourlyRateLecture;
    }
    
    //method to retreive the hourly rate for lecturing
    protected double getRateLecturing() {
        return hourlyRateLecture;
    }
    //method to set the amount of hours spent lecturing
    protected void setHoursLecturing(double hoursLectured) {
        this.hoursLectured = hoursLectured;
    }
    
    //method to retreive the amount of hours spent lecturing
    protected double getHoursLecturing() {
        return hoursLectured;
    }
    
    //method to set the hourly rate for marking
    protected void setRateMarking(double hourlyRateMarking) {
        this.hourlyRateMarking = hourlyRateMarking;
    }
    
    //method to retreive the hourly rate for marking
    protected double getRateMarking() {
        return hourlyRateMarking;
    }
    
    //method to set the amount of hours spent marking    
    protected void setHoursMarking(double hoursMarking) {
        this.hoursMarking = hoursMarking;
    }
    
    //method to retreive the amount of hours spent marking
    protected double getHoursMarking() {
        return hoursMarking;
    }
    
    /* 
    Overridden method from parent class
    Sums the combined Earnings based on the Hours worked x Hourly rate
    */
    @Override
    public double Earnings() {
        return (hourlyRateLecture * hoursLectured) + 
            (hourlyRateMarking * hoursMarking);
    }
    
    //overridden method from parent class to include new data in print out
    @Override
    public String toString() {
        
        return "Part-time Instructor: " + super.getFirstName() +
                " " + super.getLastName() + "\n" + 
                "Tax File Number: " + super.getTaxFileNumber() + "\n" + 
                "Earnings: " + (Currency.format(Earnings()));
    }
}
