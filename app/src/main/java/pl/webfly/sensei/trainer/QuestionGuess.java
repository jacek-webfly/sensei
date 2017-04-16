package pl.webfly.sensei.trainer;

public class QuestionGuess extends Question {
    public QuestionGuess(int numberOfReplies, Randomizer randomizer) {
        super(numberOfReplies, randomizer);
        generateCorrectAnswer(numberOfReplies);
    }
}
