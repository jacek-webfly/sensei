package pl.webfly.sensei.trainer;

public interface TrainerInterface {
    boolean isFinished();
    Score getScores() throws Exception;
    Question getCurrentQuestion();
    void moveToNextQuestion() throws Exception;
    int getTotalQuestionQnt();
    int getCurrentQuestionNr();
}
