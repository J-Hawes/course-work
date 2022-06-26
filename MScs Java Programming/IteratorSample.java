import java.util.*;

public class IteratorSample
{
    public static void main(String[] args)
    {
        ArrayList<String []> stList = new ArrayList<>();
        String[] first = {"abc", "efg", "hij"};
        stList.add(first);
        String[] second = {"123", "456", "789"};
        stList.add(second);

        ArraySwapping<String> swap = new ArraySwapping<>();
        Iterator<String[]> iterate = stList.iterator();

        while(iterate.hasNext())
        {
            String[] temp = iterate.next();
            System.out.println("\nOriginal order :\n");
            swap.printArray(temp);
            swap.swap(temp);
            System.out.println("\nNew order after swapping :\n");
            swap.printArray(temp);
        }
    }
}