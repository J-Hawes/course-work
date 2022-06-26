import java.util.Scanner;
import java.util.Random;

public class Guess{

    private static int random = (1 + new Random().nextInt(100 - 1 + 1));

    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please guess a number between 1 and 100 :");
        int guess = Integer.parseInt(console.nextLine());
        System.out.println("Do you think your guess was :");
        System.out.println("[H]igher\n[E]qual\n[L]ower\nCompared to the number?");
        char bet = Character.toUpperCase(console.next().charAt(0));
        System.out.println(bet);
        switch(bet)
        {
            case 'H':
            if (guess > random)
            {
                System.out.println("Correct! The number was " + 
                    random + " and your guess was " + guess);
            }
            else
            {
                System.out.println("Incorrect! The number was " + 
                    random + " and your guess was " + guess);
            }
            break;

            case 'E':
            if (guess == random)
            {
                System.out.println("Correct! The number was " + 
                    random + " and your guess was " + guess);
            }
            else
            {
                System.out.println("Incorrect! The number was " + 
                    random + " and your guess was " + guess);
            }
            break;

            case 'L':
            if (guess < random)
            {
                System.out.println("Correct! The number was " + 
                    random + " and your guess was " + guess);
            }
            else
            {
                System.out.println("Incorrect! The number was " + 
                    random + " and your guess was " + guess);
            }
            break;

            default:
            System.out.println("Not a valid guess");
            break;
        }
    }
}