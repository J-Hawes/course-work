package RollingProject;

import java.util.Scanner;
public class Input
{
    public Input()
    {
        
    }

    public char acceptCharInput(String message, int position)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine().charAt(position);
    }

    public double acceptDoubleInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return Double.parseDouble(console.nextLine());
    }

    public int acceptIntegerInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(console.nextLine());
    }

      public String acceptStringInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine();
    }
}
