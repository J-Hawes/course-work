import java.util.*;

public class SortStack
{
    private Stack<Integer> stack;

    public SortStack()
    {
        stack = new Stack<Integer>();
        Random rng = new Random();
        for(int i = 0; i < 10; i++)
        {
            stack.push(rng.nextInt(100));
        }
    }

    public void display()
    {
        System.out.println(Arrays.toString(stack.toArray()));
    }

    public Stack<Integer> getStack()
    {
        return stack;
    }

    public static void main(String[] args)
    {
        SortStack sorted = new SortStack();
        System.out.println("Stack before reverse:");
        sorted.display();
        sorted.sort(sorted.stack);
        System.out.println("Stack after sort:");
        sorted.display();
    }

    public void sort(Stack<Integer> stack)
    {
        Stack<Integer> tempStack = new Stack<Integer>();
        while(!stack.isEmpty())
        {
            int temp = stack.pop();
            while(!tempStack.isEmpty() && tempStack.peek() > temp)
            {
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        this.stack = tempStack;
    }
}