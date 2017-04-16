package pl.webfly.sensei.trainer;

import pl.webfly.sensei.trainer.Exceptions.QuestionIsNotAnsweredException;
import pl.webfly.sensei.trainer.Exceptions.AnswerOutOfBoundsException;

abstract public class Question {

    public static final int NOT_ANSWERED_VALUE = -1;
    private final Randomizer randomizer;
    private int correctAnswer;
    protected int givenAnswer = NOT_ANSWERED_VALUE;
    protected boolean[] replies;

    public Question(int numberOfReplies, Randomizer randomizer) {
        this.randomizer = randomizer;
        generateReplies(numberOfReplies);
    }

    protected void generateCorrectAnswer(int numberOfReplies) {
        correctAnswer = randomizer.getRandomInt(numberOfReplies);
    }

    private void generateReplies(int numberOfReplies) {
        replies = new boolean[numberOfReplies];
        for (int i = 0; i < numberOfReplies; i++) {
            replies[i] = true;
        }
    }

    public boolean isCorrect() throws Exception {
        if (givenAnswer == NOT_ANSWERED_VALUE) {
            throw new QuestionIsNotAnsweredException("No answer was given");
        }
        return givenAnswer == correctAnswer;
    }

    public boolean isAnswered() {
        return givenAnswer != NOT_ANSWERED_VALUE;
    }

    public void setAnswer(int answer) throws Exception {
        if (answer > (replies.length - 1)) {
            throw new AnswerOutOfBoundsException("Wrong answer was given");
        }
        givenAnswer = answer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}