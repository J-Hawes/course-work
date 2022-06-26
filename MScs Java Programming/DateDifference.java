import java.util.Scanner;

public class DateDifference
{
    private int totalDays;
    private int totalMonths;
    private int totalYears;
    
    public DateDifference()
    {

    }
    
    public static void main(String[] args)
    {
        DateDifference test = new DateDifference();
        int[] date1 = new int[3];
        int[] date2 = new int[3];
        boolean valid = false;
        while (!valid)
        {
            try
            {
                date1 = test.parseDate(test.getDate("Please enter the first date in the format DD/MM/YYYY:"));
                date2 = test.parseDate(test.getDate("Please enter the second date in the same format: "));
                valid = (test.validateDates(date1) && test.validateDates(date2));
                if(!valid)
                {
                    System.out.println("invalid dates, please try again");
                }
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please ony enter a number");
                valid = false;
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                System.out.println("please only use the / divider");
                valid = false;
            }
        }
        
        if(date1[2] == date2[2] && date1[1] == date2[1])
        {
            test.totalDays = (date2[0] - date1[0]);
            System.out.println("The difference is: "+ test.totalDays + " days");
        }
        test.totalYears = date2[2] - date1[2];
        test.totalMonths = date2[1] - date1[1];
        test.totalDays = date2[0] - date1[0];
        System.out.println("The difference is: " + test.totalYears + " years, " +
            test.totalMonths + " months, and " + test.totalDays + " days.");
        test.totalDays = date2[0] - date1[0];
        test.totalDays += test.calculateYears(date1[2], date2[2]);
        test.totalDays += test.calculateMonths(date1[1], date2[1]);
        System.out.println("Total difference in days is : " + test.totalDays);
    }

    private int calculateMonths(int month1, int month2)
    {
        int total = 0;
        for(int i = month1; i < month2; i++)
        {
            switch(i)
            {
                case 2:
                total += 28;
                break;

                case 1: 
                case 3: 
                case 5: 
                case 7: 
                case 8: 
                case 10: 
                case 12:
                total += 31;
                break;

                default:
                total += 30;
                break;
            }
        }
        return total;
    }

    private int calculateYears(int year1, int year2)
    {
        int total = 0;
        for(int i = year1; i < year2; i++)
        {
            if(isLeapYear(i) == true)
            {
                total += 366;
            }
            else
            {
                total += 365;
            }
            
        }
        return total;
    }

    private String getDate(String message)
    {   
        @SuppressWarnings("resource") //scanner
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine();
    }

    private boolean isLeapYear(int year)
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

    private int[] parseDate(String value)
    {
        String[] input = value.split("/");
        int[] date = new int[3];
        date[0] = Integer.parseInt(input[0]);
        date[1] = Integer.parseInt(input[1]);
        date[2] = Integer.parseInt(input[2]);
        return date;
    }

    private boolean validateDates(int[] date)
    {
        boolean check = false;
        if(date[1] > 12 || date[0] < 1 || date[0] > 31)
        {
            check = false;
        }
        switch(date[1])
        {
            case 2:
            if(isLeapYear(date[2]) && date[0] > 29)
            {
                check = false;
            } 
            else if(!isLeapYear(date[0]) && date[0] > 28)
            {
                check = false;
            }
            break;

            case 4: 
            case 6: 
            case 9: 
            case 11: 
            if(date[0] > 30)
            {
                check = false;
            }
            break;
        }
        return check;
    }
}