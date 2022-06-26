package personcreator;


public class PersonCreator {

    public static void main(String[] args) {
        Person s1 = new Person ("Bill");
        System.out.println(s1.getName() + " has the Id of " + 
                Person.getPersonIdNbr()); 
        Person s2 = new Person("Sarah"); 
        System.out.println(s2.getName() + " has the Id of " + 
                Person.getPersonIdNbr()); 
        Person s3 = new Person("Kate"); 
        System.out.println(s3.getName() + " has the Id of " + 
                Person.getPersonIdNbr()); 
    }
}
