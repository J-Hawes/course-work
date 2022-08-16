package CircularLinkedList;

public class CircularLinkedList
{
    private Node head = null;
    private Node tail = null;

    public CircularLinkedList()
    {

    }

    public void addNode(int value)
    {
        Node newNode = new Node(value);
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            tail.nextNode = newNode;
        }
        tail = newNode;
        tail.nextNode = head;
    }

    public int average(CircularLinkedList cll)
    {
        int value = 0;
        int count = 0;
        Node currentNode = head;
        if(head != null)
        {
            do
            {
                value += currentNode.value;
                currentNode = currentNode.nextNode;
                count++;
            } while (currentNode != head);
        }
        value = value / count;
        return value;
    }

    public static void main(String[] args)
    {
        CircularLinkedList test = new CircularLinkedList();
        test.addNode(9);
        test.addNode(5);
        test.addNode(2);
        test.addNode(7);
        test.addNode(3);
        System.out.println("Average value is : " + test.average(test));
    }
}
