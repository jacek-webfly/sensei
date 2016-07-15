package pl.webfly.sensei.trainer;

/**
 * Created by jbukowski on 6/10/16.
 */
public interface TrainerInterface {
    boolean isFinished();
    Score getScores() throws Exception;
    QuestionInterface getCurrentQuestion();
    void moveToNextQuestion() throws Exception;
    int getTotalQuestionQnt();
    int getCurrentQuestionNr();
}
