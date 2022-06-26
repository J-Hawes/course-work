package testsimulator;

public class TrueFalseQuestion extends Question {
    
    private boolean correctAnswer;
    private boolean chosenAnswer = true;
    
    public TrueFalseQuestion(String questionID, int chapterNumber, 
            String questionText, boolean correctAnswer) {
        super(questionID, chapterNumber, questionText);
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
    
    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean getChosenAnswer () {
        return chosenAnswer;
    }

    public void setChosenAnswer (boolean chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
   
    
}
