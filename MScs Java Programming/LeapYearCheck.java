import java.util.Scanner;

public class LeapYearCheck
{
    public boolean isLeap(int year)
    {
        boolean leap = true;
        switch (year % 4)
        {
            case 0:
                leap = (year % 400 == 0) || (year % 100 != 0);
                break;
            default:
                leap = false;
                break;
        }
        return leap;
    }

    public static void main(String[] args)
    {
        LeapYearCheck testing = new LeapYearCheck();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a year in the format YYYY");
        int year = console.nextInt();
        System.out.println(testing.isLeap(year));
        console.close();
    }
}
