package pl.webfly.sensei.trainer;

/**
 * Created by jbukowski on 2016-07-22
 * <p/>
 * Package: pl.webfly.sensei.trainer
 * Project: sensei
 */
public class QuestionPredict extends Question {

    private final int numberOfReplies;

    public QuestionPredict(int numberOfReplies) {
        super(numberOfReplies);
        this.numberOfReplies = numberOfReplies;
    }

    public void setAnswer(int answer) throws Exception {
        generateCorrectAnswer(numberOfReplies);
        if (answer > (replies.length - 1)) {
            throw new Exception("Wrong answer was given");
        }
        givenAnswer = answer;
    }

}
