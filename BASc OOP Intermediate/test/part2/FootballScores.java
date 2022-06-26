package test.part2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

public class FootballScores {
    private final static int NUM_ROUNDS = 5;
    private final static int MAX_SHOTS = 10;
    private final static String TEXT_FILE = "text-scores.txt";
    
    public static void main (String[] args) throws FileNotFoundException, IOException {
        writeTextScores();
        printTextScores();
    }
    
    public static void writeTextScores() throws FileNotFoundException {
        PrintWriter print = new PrintWriter(TEXT_FILE);
        int rounds = (1 + new Random().nextInt(NUM_ROUNDS - 1 + 1));
        for (int i = 1; i < rounds+1; i++)
        {
            int homeGoals = (1 + new Random().nextInt(MAX_SHOTS));
            int homeBehinds = (1 + new Random().nextInt(MAX_SHOTS));
            int awayGoals = (1 + new Random().nextInt(MAX_SHOTS ));
            int awayBehinds = (1 + new Random().nextInt(MAX_SHOTS));
            print.println(i+","+homeGoals+","+homeBehinds+","+awayGoals+","+awayBehinds); 
        }
        print.close();
    }
    
    public static void printTextScores() throws IOException {
        FileReader fileReader = new FileReader(TEXT_FILE);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] temp = line.split(",");
            int roundNumber = Integer.parseInt(temp[0]);
            int homeGoals = Integer.parseInt(temp[1]);
            int homeBehinds = Integer.parseInt(temp[2]);
            int awayGoals = Integer.parseInt(temp[3]);
            int awayBehinds = Integer.parseInt(temp[4]);
            FootballGame test = new FootballGame(roundNumber, homeGoals, homeBehinds, awayGoals, awayBehinds);
            System.out.println(test.summarise());
        }
        bufferedReader.close();
    }
}
