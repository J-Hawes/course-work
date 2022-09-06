package infix2postfix;

public class StackADT
{
    // StackNode class to create nodes
    public static class StackNode
    {
        String data;
        StackNode next;
    }

    StackNode head = new StackNode(); // Initialise a global head reference

    public StackADT()
    {
        head = null;
    }

    // Method that determines if the stack is empty
    public boolean isEmpty()
    {
        return head == null;
    }
    
    // Method that pops and returns value off the stack
    public String pop()
    {
        String temp = null;
        if(!isEmpty())
        {
            temp =  head.data;
            head = (head).next;
        }
        else
        {
            System.out.println("Stack is empty");
        }
        return temp;
    }

    // Method that prints the stack
    public void print()
    {
        if(!isEmpty())
        {
            StackNode temp = head;
            while(temp != null)
            {
                System.out.print(temp.data);
                temp = temp.next;
            }
            System.out.println();
        }
        else
        {
            System.out.println("Stack is empty");
        }
    }

    // Method that pushes a value onto the stack
    public void push(String st)
    {
        StackNode temp = new StackNode();
        temp.data = st;
        if(!isEmpty())
        {
            temp.next = head;
        }
        head = temp;
    }

    // Method that returns the top value of the stack without popping the stack
    public String stackTop()
    {
        if(!isEmpty())
        {
            return head.data;
        }
        else
        {
            System.out.println("Stack is empty");
            return null;
        }
    }
}