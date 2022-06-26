package employee;

public class EarningsTesting {
    
    public static void main(String[] args) throws Exception {
        
        try {
            FullTimeInstructor emp1 = new FullTimeInstructor("Brittanie", "Boisvert", "123456", 1500 );
            System.out.printf("\n%s \n", emp1);
        }
        catch (Exception e){
            System.out.println("\n** Exception occured in (Employee 1): " + e.getMessage() + " **");
        }
        
        try {
            SessionalInstructor emp2 = new SessionalInstructor("Jamel", "Begay", "456789", -1, 38.0);
            System.out.printf("\n%s \n", emp2);
        }
        
        catch (Exception e){
            System.out.println("\n** Exception occured in (Employee 2): "  + e.getMessage() + " **");
        }
        
        try {
            PartTimeInstructor emp3 = new PartTimeInstructor("Pearle", "Becerra", "987654", 60.0, 20.0, 30.0, 20.0);
            System.out.printf("\n%s \n", emp3);
        }
        catch (Exception e){
            System.out.println("\n** Exception occured in (Employee 3: )" + e.getMessage() + " **");
        }
        
        try {
            FullTimeInstructor emp4 = new FullTimeInstructor("Wally", "Beeks", "123456", 1600);
            System.out.printf("\n%s \n", emp4);
        }
        catch (Exception e){
            System.out.println("\n** Exception occured in (Employee 4): " + e.getMessage() + " **");
        }
        
        try {
            SessionalInstructor emp5 = new SessionalInstructor("Danyell", "Eldin", "456789", 48.0, 38.0);
            System.out.printf("\n%s \n", emp5);
        }
        catch (Exception e){
            System.out.println("\n** Exception occured in (Employee 5): " + e.getMessage() + " **");
        }
        
        try {
            PartTimeInstructor emp6 = new PartTimeInstructor("Edgar", "Junious", "654789", 58.0, 25.0, 28.0, 15);
            System.out.printf("\n%s \n", emp6);
        }
        catch (Exception e){
            System.out.println("\n** Exception occured in (Employee 6): " + e.getMessage() + " **");
        }
    
    }   
}
