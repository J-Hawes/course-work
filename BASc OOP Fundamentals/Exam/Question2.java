package Exam;

/**
 *
 * @author James
 */
import java.util.Scanner;


public class Question2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Two Numbers ");
        System.out.println("Press enter after each number : ");
        int n1, n2, n3;
        n1 = keyboard.nextInt();
        n2 = keyboard.nextInt();
        n3 = n1 * n2;
        System.out.println("The sum of the numbers entered is : " + n3);
        keyboard.close();
    }
    
}
