package BattleshipGame.battleshipmodel;

import java.util.Scanner;
@SuppressWarnings("resource") //Surpress scanner not closed warning.
// Class HumanPlayer contains modified implementation for a human-controlled player.
public class HumanPlayer extends Player {

    // Constructor.
    public HumanPlayer(String name) {
        super(name);
    }

    public int playerInput()
    {
        Scanner console = new Scanner(System.in);
        boolean proceed = false;
        int input = -1;
        do
        {
            String response = console.nextLine();
            if(response.trim().length() == 0)
            {
                System.err.println("Please Enter a number between 0 and 9 only.");
                proceed = false;
            }
            else if (response.toLowerCase().charAt(0) == 'x')
            {
                System.out.println("Thanks for playing. Exiting...");
                System.exit(0);
            }
            else
            {
                try{
                    input = Integer.parseInt(response);
                }
                catch (NumberFormatException ex)
                {
                    System.err.println("Please Enter a number between 0 and 9 only.");
                    proceed = false;
                }
                if(input >= 0 && input <= 9)
                {
                    proceed = true;
                }
                else
                {
                    System.err.println("Please Enter a number between 0 and 9 only.");
                    proceed = false;
                }
            }
        }  while(proceed == false);
        return input;
    }
    
    @Override
    public void takeGuess() {
        System.out.print("\nGuess a row (or 'x' for exit): ");
        int x = playerInput();
        System.out.print("\nGuess a column (or 'x' for exit): ");
        int y = playerInput();
        target[x][y].tryHit();
    }           
}