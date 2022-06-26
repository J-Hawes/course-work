package employee;

public abstract class Employee {
    //instance variables
    private String firstName; //First name of employee
    private String lastName; //Surname of employee
    private String taxFileNumber; //Tax file number of employee
    
    protected Employee(String firstName, String lastName, 
            String taxFileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxFileNumber = taxFileNumber;
    }
    
    //method to set tax file number
    protected void setTaxFileNumber(String taxFileNumber) {
        this.taxFileNumber = taxFileNumber;
    }
    
    //method to retreive the tax file number
    protected String getTaxFileNumber() {
        return taxFileNumber;
    }
    
    //Abstract method to be overridden in child classes
    public abstract double Earnings(); 

    //prints first name, surname and tax file number of employee
    @Override
    public String toString() {
        
        return "Employee: " + getFirstName() +
                " " + getLastName() + "\n" + 
                "Tax File Number: " + getTaxFileNumber() + "\n" ;
    }
    
    //method to set first name of employee
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    //method to retreive the first name of the employee
    protected String getFirstName() {
        return firstName;
    }
    
    //method to set surname of employee
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    //method to retreive the surname of the employee
    protected String getLastName() {
        return lastName;
    }
    
    public static void main(String[] args) throws Exception{
        
        EarningsTesting.main(args);
    }
    
}
