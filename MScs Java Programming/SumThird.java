import java.util.Scanner;

public class SumThird
{
    public int SumOfThird(int a, int b)
    {
        int result = 0;
        for (int i=a+2; i<b; i+=3)
        {
            result = result + i;
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        SumThird objSumThird = new SumThird();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter an number for the starting point :");
        int a = console.nextInt();
        System.out.println("Please enter a number for the end point :");
        int b = console.nextInt();
        System.out.println("The total sum of every 3rd number is : " + objSumThird.SumOfThird(a, b));
        console.close();
    }
}
