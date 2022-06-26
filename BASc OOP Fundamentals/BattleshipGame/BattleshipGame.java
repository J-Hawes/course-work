package BattleshipGame;

import BattleshipGame.battleshipmodel.*;

// The BattleshipGame class contains the driver implementation
// for the Battleship game.
public class BattleshipGame {
    
    // Driver method.
    public static void main(String[] args) {
        
        //create a new HumanPlayers object called player1
        Player player1 = new HumanPlayer("Player1");
        //create a new ArtificialPlayer object called player2
        Player player2 = new Player("Computer");
        
        //call placeFleet() method for both players
        player1.placeFleet();
        player2.placeFleet();
        
        //call the setTarget() method for both players
        player1.setTarget(player2.getOcean());
        player2.setTarget(player1.getOcean());
        
        //print the welcome message
        System.out.println("Welcome to the Battleship Game.");
        System.out.println("Target Grid (opponent) : ");
        
        //call printTarget() method for player1
        player1.printTarget();
        
        System.out.println("Ocean Grid (you) : ");
        
        //call printOcean method for player1
        player1.printOcean();
        
        //create game loop
        int turn = 1;
        boolean gameOver = false;
        do
        {
            player1.takeGuess();
            player2.takeGuess();
            gameOver = (player1.hasWon() || player2.hasWon());
            System.out.println("Target Grid (opponent) : ");
            player1.printTarget();
            System.out.println("Ocean Grid (you) : ");
            player1.printOcean();
            System.out.println("Turn " + turn + "...");
            if(!gameOver)
            {
                System.out.println("Nobody has won yet. Keep playing...");
            }
            turn += 1;
        } while (!gameOver);
        if (player1.hasWon()){
            System.out.println("Player1 Wins!");
        }
        else if (player2.hasWon()){
            System.out.println("Player2 Wins!");
        }
        //print farewell message
        System.out.println("Thanks for playing!"); 
    }
}