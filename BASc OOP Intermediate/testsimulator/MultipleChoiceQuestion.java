package testsimulator;

public class MultipleChoiceQuestion extends Question {

    private String [] answers = new String[4];
    private char correctAnswer;
    private char chosenAnswer = 'a';

    public MultipleChoiceQuestion(String questionID, int chapterNumber, 
            String questionText, String answer1, String answer2, 
            String answer3, String answer4, char correctAnswer) {
        super(questionID, chapterNumber, questionText);
        this.answers[0] = answer1;
        this.answers[1] = answer2;
        this.answers[2] = answer3;
        this.answers[3] = answer4;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isAnswerCorrect() {
        if (correctAnswer == chosenAnswer) {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String[] getAnswers () {
        return answers;
    }
    
    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public char getChosenAnswer () {
        return chosenAnswer;
    }

    public void setChosenAnswer (char chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
   
   
}
