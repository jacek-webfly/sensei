package pl.webfly.sensei.trainer;

public class QuestionPredict extends Question {

    private final int numberOfReplies;

    public QuestionPredict(int numberOfReplies, Randomizer randomizer) {
        super(numberOfReplies, randomizer);
        this.numberOfReplies = numberOfReplies;
    }

    public void setAnswer(int answer) throws Exception {
        generateCorrectAnswer(numberOfReplies);
        super.setAnswer(answer);
    }
}
