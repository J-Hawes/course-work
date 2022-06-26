package testsimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TestSummary {
    public static final String FILENAME_SUMMARY = "test-summary.txt";
    private final int TEST_REPORT_FIELDS = 4;
    private int[] chapterQuestionsAnswered = new int[8];
    private int[] chapterQuestionsCorrect = new int[8];

    public TestSummary()
    {
        
    }

    public void summarisePerformance()
    {
        try
        {
            FileReader summary = new FileReader(FILENAME_SUMMARY);
            BufferedReader summaryBuffer = new BufferedReader(summary);
            String line = null;
            while ((line = summaryBuffer.readLine()) != null) {
                FileReader results = new FileReader(line);
                BufferedReader resultsBuffer = new BufferedReader(results);
                String line2 = null;
                while ((line2 = resultsBuffer.readLine()) != null) {
                    String[] report = new String[TEST_REPORT_FIELDS];
                    report = line2.split(",");
                    int index = Arrays.asList(QuestionBank.CHAPTERS).indexOf(Integer.parseInt(report[1]));
                    chapterQuestionsAnswered[index] += 1;
                    if(report[2].equals(report[3]))
                    {
                        chapterQuestionsCorrect[index] += 1;
                    }
                }
                results.close();
            }
            summary.close();
        }
        catch (IOException ex)
        {
            System.out.println("Error: Could not open file. Exiting ...");
            System.exit(0);
        }
    }

    public void reportPerformance()
    {
        float percent = 0;
        int sumAnswered = 0;
        int sumCorrect = 0;
        System.out.println(" Chapter  Correct Answered  Percent");
        System.out.println("-------- -------- -------- --------");
        for (int i = 0; i < QuestionBank.CHAPTERS.length; i++)
        {
            if (chapterQuestionsAnswered[i] != 0)
            {
                percent = (100 * chapterQuestionsCorrect[i]) / chapterQuestionsAnswered[i];
            }
            System.out.printf("%8s%9d%9s%9.2f%n", QuestionBank.CHAPTERS[i], 
                chapterQuestionsAnswered[i], chapterQuestionsCorrect[i], percent);
            sumAnswered += chapterQuestionsAnswered[i];
            sumCorrect += chapterQuestionsCorrect[i];
        }
        float totalPercent = (100 * sumCorrect) / sumAnswered;
        System.out.println("-------- -------- -------- --------");
        System.out.printf("%8s%9d%9s%9.2f%n", "Total", sumAnswered, sumCorrect, totalPercent);
    }

}
