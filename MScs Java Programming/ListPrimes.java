import java.util.Scanner;

public class ListPrimes
{
    public boolean isPrime(int n)
    {
        for (int i = 2; i < n; i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true; 
    }

    public String primeList(int n)
    {
        String results = "";
        for (int i = 2; i < n; i++)
        {
            if (isPrime(i))
            {
                results = results + i + ", ";
            }
        }
        return results;
    }

    public static void main(String[] args)
    {
        ListPrimes test = new ListPrimes();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int num = console.nextInt();
        System.out.println("The prime numbers less than " + num + " are :");
        System.out.println(test.primeList(num));
        console.close();
    }
}