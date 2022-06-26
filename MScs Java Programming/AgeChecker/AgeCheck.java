package AgeChecker;

import java.util.Scanner;

public class AgeCheck
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter an age in whole numbers : ");
        int age = Integer.parseInt(console.nextLine());
        System.out.println("Please enter a gender : ");
        String gender = console.nextLine();
        Person person = new Person(age, gender);
        console.close();
        if ((age >= 0) && (age < 13))
            {
                System.out.println(person.Display() + "\nThis person is a Minor");
            }
        else if ((age >= 13) && (age < 18))
            {
                System.out.println(person.Display() + "\nThis person is a Teenager");
            }
        else if (age >= 18)
        {
            System.out.println(person.Display() + "\nThis person is an Adult");
        }
        else
            {
                System.out.println(person.Display() + "\nThis person is not yet born");
            }
    }
}
