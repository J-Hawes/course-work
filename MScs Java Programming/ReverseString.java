import java.util.Scanner;

public class ReverseString
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a string :");
        String reverse = "";
        char[] input = console.nextLine().toCharArray();
        for (int i = input.length-1; i >= 0; i--)
        {
            if (Character.isUpperCase(input[i]))
            {
                reverse = reverse + Character.toLowerCase(input[i]);
            }
            else
            {
                reverse = reverse + Character.toUpperCase(input[i]);
            }
        }
        System.out.println(reverse);
        console.close();
    }
}