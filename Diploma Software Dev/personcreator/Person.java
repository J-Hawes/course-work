package personcreator;

public class Person {
    private static int personIdNbr = 0;
    private String name;
    
    public Person (String name) {
        ++personIdNbr;
        this.name = name;
    }
    
    public static int getPersonIdNbr () {
        return personIdNbr;
    }
    
    public String getName () {
        return name;
    }
}
