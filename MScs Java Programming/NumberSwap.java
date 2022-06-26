public class NumberSwap
{
    //Define the fields here
    private int numOne, numTwo;
    
    public static void main(String[] args)
    {
        NumberSwap objSwap = new NumberSwap();
        objSwap.swapNumbers();
    }

    public void swapNumbers()
    {
        numOne = 10;
        numTwo = 20;
        System.out.println("Before Swap:");
        System.out.println("numOne = " + numOne + " numTwo = " + numTwo);
        int numTemp = numOne;
        numOne = numTwo;
        numTwo = numTemp;
        System.out.println("After Swap:");
        System.out.println("numOne = " + numOne + " numTwo = " + numTwo);
    }
}