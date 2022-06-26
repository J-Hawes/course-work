package Exam;

public class StudentTester {
    public static void main(String[] args) {

        Student john = new Student("John", 1, 3.20);
        
        Student bob = new Student();
        bob.setName("Bob");
        bob.setId(2);
        bob.setGpa(2.47);
        
        System.out.println(bob.getName() + " " + bob.getId() + " " + bob.getGpa());
        
        System.out.println(john.getName() + " " + john.getId() + " " + john.getGpa());

        System.out.println(bob.equals(john));
        System.out.println(john.equals(john));
    }
}
