import java.util.Scanner;

public class RecursiveSearch
{
    private int vowel;
    private int consonant;
    private int symbol;
    private int num;
    private int space;

    public RecursiveSearch()
    {
        vowel = 0;
        consonant = 0;
        symbol = 0;
        num = 0;
        space = 0;
    }

    public void compare(String s, int i)
    {
        if (s.charAt(i) >= 97 && s.charAt(i) <= 122)
        {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o'|| s.charAt(i) == 'u')
            {
                vowel++;
            }
            else
            {
                consonant++;
            } 
        }
        else if (s.charAt(i) >= 48 && s.charAt(i) <= 57)
        {
            num++;
        }
        else if(s.charAt(i) == 32)
        {
            space++;
        }
        else
        {
            symbol++;
        }

        i++;
        
        if(i < s.length())
        {
            compare(s, i);
        }    
    }

    public void display()
    {
        System.out.println("The entered string contains :");
        System.out.println("Vowels : " + vowel);
        System.out.println("Consonants : " + consonant);
        System.out.println("Symbols : " + symbol);
        System.out.println("Numbers : " + num);
        System.out.println("Spaces : " + space);
    }

    @SuppressWarnings("resource") // Surpress unclosed scanner warning
    public static void main(String[] args)
    {
        RecursiveSearch test = new RecursiveSearch();
        
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a String: ");
        String s = console.nextLine().toLowerCase();
        test.compare(s, 0);
        test.display();
    }
}