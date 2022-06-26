package Exam;

import java.util.StringTokenizer;
import java.util.Scanner;

/**
 *
 * @author James
 */
public class Question4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String longest = ""; 
        System.out.print("Enter a sentence to parse : ");
        Scanner keyboard = new Scanner(System.in);
        String sentence = keyboard.nextLine();
        StringTokenizer st = new StringTokenizer(sentence);
        String shortest = sentence;
        while (st.hasMoreElements()) {
            String token = st.nextToken();  
            if (longest.length() < token.length()) {
                longest = token;
            }
            if (shortest.length() > token.length()) {
                shortest = token;
            }
            System.out.println(token);
            }
        System.out.println("The longest word is : " + longest);
        System.out.println("The shortest word is : " + shortest);
        keyboard.close();    
    }
    
}
