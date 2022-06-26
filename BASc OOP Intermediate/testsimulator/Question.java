package testsimulator;

public abstract class Question {
    
    private String questionID;
    private int chapterNumber;
    private String questionText;
    
    public Question (String questionID, int chapterNumber, String questionText){
        this.questionID = questionID;
        this.chapterNumber = chapterNumber;
        this.questionText = questionText;
    }
    
    public abstract boolean isAnswerCorrect();
    
    public String getQuestionID() {
        return questionID;
    }
    
    public int getChapterNumber() {
        return chapterNumber;
    }
    
    public String getQuestionText() {
        return questionText;
    }
}
