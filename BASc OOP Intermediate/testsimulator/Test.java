package testsimulator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Test implements QuestionFinder {
    
    private ArrayList<Question> questions = new ArrayList<>();
    private int correct;

    public Test (int numQuestions, QuestionBank questionBank) {
        selectQuestions(numQuestions, questionBank);
    }
    
    public int getLength() {
        return questions.size();
    }
    
    private void selectQuestions (int numQuestions, QuestionBank questionBank) {
        //randomly select questions, no duplicates
        if(numQuestions > questionBank.getLength())
        {
        System.out.println("The question bank does not have " + numQuestions + " questions.");
        System.out.println("The test will have " + questionBank.getLength() + " questions instead.");
        numQuestions = questionBank.getLength();
        }
        ArrayList<Question> temp = new ArrayList<>();
        for(int i = 0; i < questionBank.getLength(); i++)
        {
        temp.add(questionBank.getQuestion(i));
        }
        for(int j = 0; j < numQuestions; j++)
        {
        int Q = (int)(Math.random() * (temp.size() - 1) + 1);
        questions.add(temp.get(Q));
        temp.remove(Q);
        Collections.shuffle(temp);
        }
    }
    
    public boolean runtest() {
        Scanner keyboard = new Scanner(System.in);
        //Display a text menu
        System.out.println("\nWelcome to your test.");
        System.out.println("The test will have " + getLength() + " questions.");
        System.out.println("You may hit 'q' to quit the test any time, "
                + "but progress or results will not be saved.");
        System.out.println("\nStarting your test now ...");
        correct = 0;
        boolean quit = false;
        boolean progress = false;
        for(int i = 0; i < questions.size(); i++)
        {
            boolean proceed = false;
            do
            {
                if (questions.get(i) instanceof MultipleChoiceQuestion)
                {
                    System.out.println("\nQuestion " + (i+1) + "\n");                   
                    System.out.println(questions.get(i).getQuestionText() + "\n");
                    System.out.println("(a) " + ((MultipleChoiceQuestion)questions.get(i)).getAnswers()[0]);
                    System.out.println("(b) " + ((MultipleChoiceQuestion)questions.get(i)).getAnswers()[1]);
                    System.out.println("(c) " + ((MultipleChoiceQuestion)questions.get(i)).getAnswers()[2]);
                    System.out.println("(d) " + ((MultipleChoiceQuestion)questions.get(i)).getAnswers()[3]);
                    System.out.print("\nEnter your answer: ");
                    char selection = keyboard.nextLine().toLowerCase().charAt(0);
                    switch(selection)
                    {
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        ((MultipleChoiceQuestion)questions.get(i)).setChosenAnswer(selection);
                        if(((MultipleChoiceQuestion)questions.get(i)).isAnswerCorrect())
                        {
                            System.out.println("Feedback: Correct!");
                            correct += 1;
                            proceed = true;
                        }
                        else
                        {
                            System.out.println("Feedback: Incorrect. Correct answer was " + 
                                ((MultipleChoiceQuestion)questions.get(i)).getCorrectAnswer());
                            proceed = true;
                        }
                        break;

                        case 'q':
                        System.out.println("You have quit the test, results not saved.");
                        quit = true;
                        proceed = true;
                        break;

                        default:
                        System.out.println("Please enter a valid choice only");
                        proceed = false;
                        break;
                    }
                }
                else
                {
                    System.out.println("\nQuestion " + (i+1) + "\n"); 
                    System.out.println("\n" + questions.get(i).getQuestionText() + "\n");
                    System.out.println("(a) True");
                    System.out.println("(b) False");
                    System.out.print("\nEnter your answer: ");
                    char selection = keyboard.nextLine().toLowerCase().charAt(0);
                    switch(selection)
                    {
                        case 'a':
                        ((TrueFalseQuestion) questions.get(i)).setChosenAnswer(true);
                        if(((TrueFalseQuestion) questions.get(i)).isAnswerCorrect())
                        {
                            System.out.println("Feedback: Correct!");
                            correct += 1;
                            proceed = true;
                        }
                        else
                        {
                            System.out.println("Feedback: Incorrect. Correct answer was " + 
                                ((TrueFalseQuestion) questions.get(i)).getCorrectAnswer());
                            proceed = true;
                        }
                        break;

                        case 'b':
                        ((TrueFalseQuestion) questions.get(i)).setChosenAnswer(false);
                        if(((TrueFalseQuestion) questions.get(i)).isAnswerCorrect())
                        {
                            System.out.println("Feedback: Correct!");
                            correct += 1;
                            proceed = true;
                        }
                        else
                        {
                            System.out.println("Feedback: Incorrect. Correct answer was " + 
                                ((TrueFalseQuestion) questions.get(i)).getCorrectAnswer());
                            proceed = true;
                        }
                        break;

                        case 'q':
                        System.out.println("You have quit the test, results not saved.");
                        quit = true;
                        proceed = true;
                        break;

                        default:
                        System.out.println("Please enter a valid choice only");
                        proceed = false;
                        break;
                    }
                }
            } while(!proceed);
            if(quit)
            {
                progress = false;
                break;
            }
            else
            {
                progress = true;
            }
        }
        return progress;   
    }
    
    public void showTestSummary () {
        int N = questions.size();
        System.out.println("");
        System.out.println("You answered " + correct + " questions correctly out of " + N + ".");
        float percent = (100 * correct) / N;
        System.out.println("Your score was " + String.format("%.0f%%",percent) + ".");
        
    }
    
    public void saveTestResult () throws IOException {
        /*Prints the following message on success (example provided):
        Test result saved to test-180612-110754.txt.
        Test record added to test-summary.txt.*/
        String testDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd-HHmmss"));
        String file =  "test-" + testDate + ".txt";
        FileWriter writer = new FileWriter(file);
        String output = "";
        for(Question Q : questions)
        {
            output += Q.getQuestionID() + "," + Q.getChapterNumber() + ",";
            if (Q instanceof MultipleChoiceQuestion)
            {
                output += ((MultipleChoiceQuestion)Q).getCorrectAnswer() + "," + 
                    ((MultipleChoiceQuestion)Q).getChosenAnswer();
            }
            else
            {
                output += ((TrueFalseQuestion)Q).getCorrectAnswer() + "," + 
                    ((TrueFalseQuestion)Q).getChosenAnswer();
            }
            output += "\n";
        }
        writer.write(output);
        writer.close();
        System.out.println("\nTeast result saved to " + file);

        FileWriter testSum = new FileWriter(TestSummary.FILENAME_SUMMARY, true);
        testSum.write(file + "\n");
        System.out.print("\nTest record added to " + TestSummary.FILENAME_SUMMARY + "\n");
        testSum.close();
    }
}
