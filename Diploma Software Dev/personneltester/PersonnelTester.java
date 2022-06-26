package personneltester;

/**
 *
 * @author JAMES
 */
public class PersonnelTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Student trev = new Student("Trev", 123, "street", "BSc");
        System.out.println(trev.getName() + " is a " + trev.getCourse() + " student");
        
        Employee fred = new Employee("Fred", 123, "street", "Programmer");
        System.out.println(fred.getName() + " works as a " + fred.getJobPosition());
    }
}
