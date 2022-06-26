/**
 *
 * @author JAMES
 */
public class ExceptionTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // code application logic here
        int[] numArray = new int[3];
        try {
            numArray[1] = 5;
            System.out.println("Still inside try - Catch block");
        }
        catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("An ArrayIndexOutOfBounds exception has been caught");
        }
        catch (Exception e) {
            System.out.println("An exception was caught by the general exception. the exception type was " 
                    + e.getClass() + " so you should code an exception type for this"); 
        }
        finally {
            System.out.println("This message is always displayed!");
        }
        System.out.println("Outside try - Catch block!");
    }
}
