import java.util.Scanner;

public class WageCalculation
{
    public double calculateWage(double hours)
    {
        double regular = 0;
        double overtime = 0;
        if (hours >= 35)
            {
                regular = 35;
                overtime = hours - 35;
            }
        else
            {
                regular = hours;
            }
        double wages = (regular * 30) + (overtime * 40);
        return wages;
    }

    public static void main(String[] args)
    {
        WageCalculation testing = new WageCalculation();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter hours worked :");
        double hours = console.nextDouble();
        System.out.println("$" + testing.calculateWage(hours));
        console.close();
    }
}
