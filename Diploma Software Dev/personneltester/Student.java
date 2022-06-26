package personneltester;

/**
 *
 * @author JAMES
 */
public class Student extends Personnel {
    private String course;
    
    public Student (String personName, int streetNo, String streetName, String courseName) {
        super(personName, streetNo, streetName);
        course = courseName;
    }
    public String getCourse(){
        return course;
    }
}
