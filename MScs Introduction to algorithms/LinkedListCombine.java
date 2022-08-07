import java.util.*;

public class LinkedListCombine
{
    private LinkedList<Integer> one;
    private LinkedList<Integer> two;

    public LinkedListCombine()
    {
        Random rng = new Random();
        one = new LinkedList<Integer>();
        int x = rng.nextInt(5);
        for(int i = 0; i < x; i++)
        {
            one.addLast(rng.nextInt(100));
        }

        two = new LinkedList<Integer>();
        int y = rng.nextInt(5); 
        for(int i = 0; i < y; i++)
        {
            two.addLast(rng.nextInt(100));
        }
    }

    public void display(LinkedList<Integer> list)
    {
        for (Integer val : list) 
        {
            System.out.print(val + " ");
        }
    }

    public void combine()
    {
        for (Integer val : two) {
            one.addLast(val);
        }
    }

    public static void main(String[] args)
    {
        LinkedListCombine test = new LinkedListCombine();
        System.out.print("List one : ");
        test.display(test.one);
        System.out.println();
        System.out.print("List two : ");
        test.display(test.two);
        System.out.println();
        test.combine();
        System.out.print("Combined : ");
        test.display(test.one);
    }

}