public class ArraySwapping <T>
{
    public ArraySwapping()
    {

    }

    public void swap(T[] array)
    {
        T temp = array[0];
        array[0] = array[1];
        array[1] = temp;
    }

    public void printArray(T[] array)
    {
        for (int i = 0; i< array.length; i++)
        {
            System.out.println("Position [" + i +"]" + array[i].toString());
        }
    }

    public static void main(String[] args)
    {
        ArraySwapping<Integer> swap = new ArraySwapping<>();
        Integer[] array = {10, 30, 20, 40};
        System.out.print("Original array ");
        swap.printArray(array);swap.swap(array);
        System.out.print("After swapping ");
        swap.printArray(array);
    }   
}
