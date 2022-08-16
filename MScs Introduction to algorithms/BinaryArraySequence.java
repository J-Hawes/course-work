public class BinaryArraySequence
{
    public BinaryArraySequence()
    {

    }

    public int sequence(int[] arr, int len, int t)
    {
        int count = 0;
        int count2 = 0;
        int length = 0;

        for(int i = 0; i < len; i++)
        {
            if(arr[i] == 0)
            {
                count++;
                while (count > t)
                {
                    if(arr[count2] == 0)
                    {
                        count--;
                    }
                    count2++;
                }
            }
            length = Math.max(length, i - count2 + 1);
        }
        return length;
    }
    
    public static void main(String[] args)
    {
        BinaryArraySequence test = new BinaryArraySequence();
        int arr[] = {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0 };
        int t = 2;
        int len = arr.length;
        System.out.println("The length of the longest sequence is : " + test.sequence(arr, len, t)); 
    }
}