package testsimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionBank implements QuestionFinder {
    
    public static Integer [] CHAPTERS = {8,9,10,11,13,14,15,16};
    private final String MULTIPLE_CHOICE_FILE = "multiple-choice-questions.csv";
    private final String TRUE_FALSE_FILE = "true-false-questions.csv";
    private final int MULTIPLE_CHOICE_FIELDS = 8;
    private final int TRUE_FALSE_FIELDS = 4;
    private ArrayList<Question> questions;
    public int N;

    public QuestionBank()  {
        this.questions = new ArrayList<Question>();
        //needs more validation
        try {
            loadMultipleChoiceQuestions();
            loadTrueFalseQuestions();
        } catch (IOException ex) {
            System.out.println("Error: Could not open file. Exiting ...");
            System.exit(0);
        }
            System.out.println("Loaded all " + N + " questions from "
                    + "the question bank.");
    }
    
    public int getLength() {
        return questions.size();
    }
    
    public Question getQuestion (int index) {
        return questions.get(index);
    }
    
    public void loadMultipleChoiceQuestions() throws IOException {
        FileReader fileReader = new FileReader(MULTIPLE_CHOICE_FILE);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            N = N + 1;
            String[] temp = new String[MULTIPLE_CHOICE_FIELDS];
            temp = line.split(",");
            String questionID = (temp[0]);
            int chapterNumber = Integer.parseInt(temp[1]);
            String questionText = temp[2];
            String answer1 = temp[3];
            String answer2 = temp[4];
            String answer3 = temp[5];
            String answer4 = temp[6];
            char correctAnswer = temp[7].toLowerCase().charAt(0);
            questions.add(new MultipleChoiceQuestion(questionID, chapterNumber, questionText, 
                    answer1, answer2, answer3, answer4, correctAnswer) );
        }
        bufferedReader.close();
        }
    
    public void loadTrueFalseQuestions() throws IOException {
        FileReader fileReader = new FileReader(TRUE_FALSE_FILE);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        
        while ((line = bufferedReader.readLine()) != null) {
            N = N + 1;
            String[] temp2 = new String[TRUE_FALSE_FIELDS];
            temp2 = line.split(",");
            String questionID = (temp2[0]);
            int chapterNumber = Integer.parseInt(temp2[1]);
            String questionText = temp2[2];
            boolean correctAnswer = Boolean.parseBoolean(temp2[3].toLowerCase());
            questions.add(new TrueFalseQuestion(questionID, chapterNumber, questionText, 
                    correctAnswer) );
        }
        bufferedReader.close();
        }

}
