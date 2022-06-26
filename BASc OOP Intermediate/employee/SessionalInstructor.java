package employee;

import java.text.DecimalFormat;

public class SessionalInstructor extends Employee {
    
    public SessionalInstructor(String firstName, String lastName, 
            String taxFileNumber, double hourlyRate, double hoursWorked) throws Exception {
        super(firstName, lastName, taxFileNumber);
        
        //Exception handling if value is a negative or zero
        if (hourlyRate <= 0 ) {
            throw new Exception ("Hourly Sessional rate must be above zero!");
        }
        if (hoursWorked <= 0 ) {
            throw new Exception ("Sessional Hours worked must be more than zero!");
        }
        
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    
    //instance variables
    private double hourlyRate;
    private double hoursWorked;
    DecimalFormat Currency = new DecimalFormat("$###,###.##");
    
    //overridden method from parent class
    @Override
    protected void setTaxFileNumber(String TaxFileNumber) {
        super.setTaxFileNumber(TaxFileNumber);
    }
    
    //overridden method from parent class
    @Override
    protected String getTaxFileNumber() {
        return super.getTaxFileNumber();
    }
    
    /*
    Overridden method from parent class
    Calculates the Earnings based on the Hours worked  x Hourly rate
    */
    @Override
    public double Earnings() {
        return (getRate() * getHours());
    } 
    
    //overridden method from parent class to include new data in print out
    @Override
    public String toString() {
        
        return "Sessional Instructor: " + super.getFirstName() +
                " " + super.getLastName() + "\n" + 
                "Tax File Number: " + getTaxFileNumber() + "\n" + 
                "Earnings: " + (Currency.format(Earnings()));
    }
    
    //method to set the hourly rate
    protected void setRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    //method to retreive the hourly rate
    protected double getRate() {
        return hourlyRate;
    }
    
    //method to set the hours worked
    protected void setHours(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    //method to retreive the hours worked
    protected double getHours() {
        return hoursWorked;
    }
    
}
