package pl.webfly.sensei.trainer;

/**
 * Created by jbukowski on 2016-07-22
 * <p/>
 * Package: pl.webfly.sensei.trainer
 * Project: sensei
 */
public class QuestionGuess extends Question {
    public QuestionGuess(int numberOfReplies) {
        super(numberOfReplies);
        generateCorrectAnswer(numberOfReplies);
    }
}
