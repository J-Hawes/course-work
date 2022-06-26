package testsimulator;

import java.util.ArrayList;

public interface QuestionFinder {
    
    public default boolean containsQuestion (String questionID, 
        ArrayList<Question> questions) {
        
        for(Question q : questions) {
            if(q != null && q.getQuestionID().equals(questionID)) {
                return true;
            }
        }
        return false;
    }
}
