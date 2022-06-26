package Exam;

/**
 *
 * @author James
 */
public class Question3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
    int sumEven = 0;
	int sumOdd = 0;
	for (int i = 1; i <= 25; i++) {
            if (i % 2 == 0) {
            sumEven = sumEven + i;
            }
            else
                sumOdd = sumOdd + i;
	}
	System.out.println("The sum of 25 Even numbers between 1 and 25 is : " + sumEven);
        System.out.println("The sum of 25 Odd numbers between 1 and 25 is : " + sumOdd);
    }
    
}
