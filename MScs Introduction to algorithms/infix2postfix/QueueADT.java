package infix2postfix;

public class QueueADT
{
    private int size;
    private StackADT first = new StackADT();
    private StackADT last = new StackADT();

    public QueueADT()
    {
        size = 0;
    }

    // Method which add a value to the back of the queue
    public void enqueue(String st)
    {
        StackADT temp = new StackADT();
        temp.push(st);
        if (!isEmpty()) //add to back of queue
        {
            last.head.next = temp.head;
        }
        else //add to front of queue
        {
            first = temp;
        }
        last = temp;
        size++;
    }

    // Method which removes and returns a value from the front of the queue
    public String dequeue()
    {
        String temp = null;
        if(!isEmpty())
        {
            temp = first.head.data;
            first.head = first.head.next;
            size--;
            if(isEmpty()) //If first is NULL, last should also be NULL
            {
                last = null;
            }
        }
        return temp;
    }

    // Method which determines whether the queue is empty
    public boolean isEmpty()
    {
        return (size == 0);
    }

    // Method which returns the value at the front of the queue
    public String queueFront()
    {
        return first.head.data;
    }

    // Method that prints the queue
    public void print()
    {
        if(!isEmpty())
        {
            StackADT temp = first;
            while(temp.head != null)
            {
                System.out.print(temp.head.data);
                temp.head = temp.head.next;
            }
            System.out.println();
        }
        else
        {
            System.out.println("Queue is empty");
        }
    }
}