package WordGames;

/**
 * @author James Hawes
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

@SuppressWarnings("resource") // Surpress unclosed scanner warning
public class WordGames {
//Contant reference to the file for use accross all methods
    public static final String DICTIONARY = ("Dictionary.txt");
    
    /* This method calculates the points per letter of each word in the 
    dictionary based on the scrabble scoring points guide.
    */    
    
    public static void pointsProblem() throws FileNotFoundException{
    //Open the file for use 
    File file = new File(DICTIONARY);
        if (file.isFile() == false) {
            System.out.println("Error: Could not open file. Exiting ...");
            System.exit(0);
        }
        
    Scanner fileScanner = new Scanner(file);
    while (fileScanner.hasNextLine()) {
        String word = fileScanner.nextLine();
        int result = 0;
        //Loop through each word on character at a time
        for ( int i = 0; i < word.length(); i++ ){
        String letter = Character.toString(word.charAt(i));
        //Assign points based on letters and the points table provided
        switch (letter) {
            case "a": case "e": case "i": case "l": case "n":
            case "o": case "r": case "s": case "t": case "u":
                result += 1;
                break;
            case "d": case "g":
                result += 2;
                break;
            case "b": case "c": case "m": case "p":
                result += 3;
                break;
            case "f": case "h": case "v": case "w": case "y":
                result += 4;
                break;
            case "k":
                result += 5;
                break;
            case "j": case "x":
                result += 8;
                break;
            case "q": case "z":
                result += 10;
                break;
            default:
                break;
            }
        }
        System.out.println(word + " has " + result + " points");
        }
    //Close the file
    fileScanner.close();
    }
    
    /* this method takes a user input as a substring and searchs each word
    scanned from the file as a string to locate if it is either a prefix
    infix or suffix.
    */
    
    public static void substringProblem()throws FileNotFoundException{
        
        //Open the file for use        
        File file = new File(DICTIONARY);
        if (file.isFile() == false) {
            System.out.println("Error: Could not open file. Exiting ...");
            System.exit(0);
        }
        Scanner fileScanner = new Scanner(file);
        System.out.println("Substring problem.");
        System.out.print("Enter a subtring: ");
        
        //read input from the user
        Scanner keyboard = new Scanner(System.in);
        String subString = keyboard.next();   
        //Loop through each word looking for a match
        while (fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            int wordLength = word.length();
            int subStringLength = subString.length();
            String result = word;  
            /*Search string starting from the second character to exclude the
            1st character from the search as the startsWith method already handles this*/
            int search = word.indexOf(subString, 1);
            //Search string to see if substring exists first            
            if (!word.contains(subString)){
                result = (result + " - not found ");
            }
            //Search string to check if the substring is longer than the string
            if (subStringLength > wordLength) {
                result = (result + " - not found ");
            }
            //Check is the substring is at the begining            
            if (word.startsWith(subString)){
                result = (result + " - start ");
            }
            /*Find the middle of the string assuming index is greater than the 1st index
            while also being less then the final index*/
            if (search > 0  && search < wordLength-subStringLength) {
            result = (result + " - middle ");
            
            }                       
            //Check if the substring is at the end            
            if (word.endsWith(subString)) {
                result = (result + " - end ");
            }
            System.out.println(result); //Print the results
        }        
        fileScanner.close(); //Close the file
    }
    
 
    /*this method takes every word scanned in from the file and cross
    refferences it against itself in reverse to determine if the word is a 
    palindrome or not.
    */
    
    public static void palidromeProblem()throws FileNotFoundException{
        //Open the file for use 
        File file = new File(DICTIONARY);
        if (file.isFile() == false) {
            System.out.println("Error: Could not open file. Exiting ...");
            System.exit(0);
        }
        
    Scanner fileScanner = new Scanner(file);
    String result = ""; 
    while (fileScanner.hasNextLine()) {
        String word = fileScanner.nextLine();
        String reverse = "";
        //Rebuild the string in reverse one character at a time
        for ( int i = word.length() -1; i >= 0; i-- )
        reverse = reverse + word.charAt(i);
        //Compare the original string with the reverse string
        if (word.equals(reverse))
            result = result + word + " is a palindrome\n";
            
        else 
            result = result + word + " is not a palidrome\n";
            
        }
    System.out.println(result);
    fileScanner.close(); //Close the file
    }

    //this method prints the main menu and then returns the users selection
    
    public static int getSelection() {
        Scanner keyboard = new Scanner(System.in);
        //Display a text menu
        System.out.println("");
        System.out.println("Welcome to the Word Games program menu.");
        System.out.println("Select from one of the following options.");
        System.out.println("1. Substring problem.");
        System.out.println("2. Points problem.");
        System.out.println("3. Palindome problem.");
        System.out.println("4. Exit.");
        System.out.println("");
        System.out.print("Enter your selection: ");
        int selection = keyboard.nextInt(); //Receive input from user
        if (selection == 4) {
            System.out.println("");
            System.out.println("Goodbye!");
        }
        return selection;
        
    }

    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
        public static void main(String[] args) throws FileNotFoundException {
        
        int selection = getSelection(); //retreive users selection
        System.out.println("");
        while (selection != 4) { //loop until user selects to exit (4)
        switch (selection) 
        {
            case 1 :
                substringProblem();
                break;
            case 2 :
                pointsProblem();
                break;
            case 3 :
                palidromeProblem();
                break;
            default :
                System.out.println("Invalid option. Try Again");
                break;
            }    
        selection = getSelection();
        }
        }
}