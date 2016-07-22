package pl.webfly.sensei.trainer;

import java.util.Random;

/**
 * Created by jbukowski on 2016-06-14
 *
 * Package: ${PACKAGE_NAME}
 * Project: Tutorial
 *
 */
public class Question {

    private int correctAnswer;
    protected int givenAnswer = -1;
    protected boolean[] replies;

    public Question(int numberOfReplies) {
        generateReplies(numberOfReplies);

    }

    protected void generateCorrectAnswer(int numberOfReplies) {
        Random rn = new Random();
        correctAnswer = rn.nextInt(numberOfReplies);
    }

    private void generateReplies(int numberOfReplies) {
        replies = new boolean[numberOfReplies];
        for (int i = 1; i <= numberOfReplies; i++) {
            replies[i - 1] = true;
        }
    }

    public boolean isCorrect() throws Exception {
        if (givenAnswer == -1) {
            throw new Exception("No answer was given");
        }
        return givenAnswer == correctAnswer;
    }

    public boolean isAnswered() {
        return givenAnswer != -1;
    }

    public void setAnswer(int answer) throws Exception {
        if (answer > (replies.length - 1)) {
            throw new Exception("Wrong answer was given");
        }
        givenAnswer = answer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
