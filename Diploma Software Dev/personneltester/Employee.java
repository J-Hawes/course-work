package personneltester;

/**
 *
 * @author JAMES
 */
public class Employee extends Personnel {
    private String jobPosition;
    
    public Employee (String personName, int streetNo, String streetName, String job){
        super(personName, streetNo, streetName);
        jobPosition = job;
    }
    public String getJobPosition(){
        return jobPosition;
    }
}
