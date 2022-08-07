import java.util.*;

public class QuarterPercentList
{
    private LinkedList<Integer> list;

    public QuarterPercentList()
    {
        Random rng = new Random();
        list = new LinkedList<Integer>();
        int val = rng.nextInt(100-10) + 10;
        for(int i = 0; i < val; i++)
        {
            list.addLast(rng.nextInt(100));
        }
    }

    public void display(LinkedList<Integer> list)
    {
        for (Integer val : list) 
        {
            System.out.print(val + " ");
        }
    }

    public static void main(String[] args)
    {
        QuarterPercentList test = new QuarterPercentList();
        System.out.print("List contains : ");
        test.display(test.list);
        System.out.println();
        System.out.println("The element at 25% of the index is: ");
        test.quarter(test.list);
    }

    public void quarter(LinkedList<Integer> list)
    {
        int quart = (int)(Math.floor(list.size() / 4));
        int val = list.get(quart -1);
        System.out.println(val);
    }
}