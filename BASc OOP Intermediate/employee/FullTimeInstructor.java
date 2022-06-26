package employee;

import java.text.DecimalFormat;

public class FullTimeInstructor extends Employee {
    
    public FullTimeInstructor(String firstName, String lastName, 
            String taxFileNumber, double weeklySalary) throws Exception {
        super(firstName, lastName, taxFileNumber);
        
        //Exception handling if value is a negative or zero
        if (weeklySalary <= 0) {
            throw new Exception("Salary must be be above zero!");
        }
        this.weeklySalary = weeklySalary; 
    }
    
    //instance variables
    private double weeklySalary;
    DecimalFormat Currency = new DecimalFormat("$###,###.##");
    
    //overridden method from parent class to return the weekly pay
    @Override
    public double Earnings() {
        return weeklySalary;
    }

    //overridden method from parent class to include new data in print out
    @Override
    public String toString() {
        return "Full-time Instructor: " + super.getFirstName() +
                " " + super.getLastName() + "\n" + 
                "Tax File Number: " + super.getTaxFileNumber() + "\n" + 
                "Earnings: " + (Currency.format(Earnings()));
    }
    
    //method to set the weekly salary of an employee
    protected void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }
    
    //method to retreive the weekly salary of an employee
    protected double getWeeklySalary() {
        return weeklySalary;
    }
}
