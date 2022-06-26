package testsimulator;

import java.io.IOException;
import java.util.Scanner;

public class TestSimulator {
    
    //this method prints the main menu and then returns the users selection    
    public static int getSelection() {
    Scanner keyboard = new Scanner(System.in);
    //Display a text menu
    System.out.println("\nWelcome to the TestSimulator program menu.");
    System.out.println("Select from one of the following options.");
    System.out.println("1. New test.");
    System.out.println("2. Test summary.");
    System.out.println("3. Exit.");
    System.out.println("\nEnter your selection: ");
    int selection = keyboard.nextInt(); //need to implement validation
    return selection;    
    }
    
   public static void main(String[] args) {
        QuestionBank milestone = new QuestionBank();
        Test test = new Test(5, milestone); 
        int selection = getSelection(); //retreive users selection
        while (selection != 3) { //loop until user selects to exit (3)
            switch (selection) 
            {
                case 1 :
                    if(test.runtest())
                    {
                        test.showTestSummary();
                        try
                        {
                            test.saveTestResult();
                        }
                        catch (IOException ex)
                        {
                            System.out.println("System encountered an error writing to file!");
                            break;
                        }
                    }
                    break;
                case 2 :
                    TestSummary summary = new TestSummary();
                    summary.summarisePerformance();
                    summary.reportPerformance();
                    break;
                case 3 :
                    System.out.println("\nThank you for using TestSimulator!");
                    System.exit(0);
                default :
                    System.out.println("Invalid option. Try Again");
                    break;
                }    
            selection = getSelection();
            }
        }
    
}
