import java.util.*;
public class ReverseStack
{
    private Stack<String> stack = new Stack<>();

    public ReverseStack()
    {
        stack = new Stack<String>();
        Random rng = new Random();
        for(int i = 0; i < 5; i++)
        {
            String x = "" + rng.nextInt(100);
            stack.push(x);
        }
    }

    public void display()
    {
        for(String item : stack)
        {
            System.out.print(item + ", ");
        }
    }
    
    public void insert(String s)
    {
        if(stack.size() == 0)
        {
            stack.push(s);
        }
        else
        {
            String st = stack.pop();
            insert(s);
            stack.push(st);
        }
    }

    public void reverse()
    {
        if(stack.size() > 0)
        {
            String st = stack.pop();
            reverse();
            insert(st);
        }
    }

    public static void main(String[] args)
    {
        ReverseStack test = new ReverseStack();
        System.out.println("Orginal stack is : ");
        test.display();
        test.reverse();
        System.out.println("\nReverse stack is : ");
        test.display();
    }
}
