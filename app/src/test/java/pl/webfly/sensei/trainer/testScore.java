package pl.webfly.sensei.trainer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class testScore {

    private final int totalQuestion;
    private final int numberOfCorrectAnswers;
    private final float expectedResult;

    public testScore(int numberOfAnsweredQuestions, int numberOfCorrectAnswers, float expectedResult) {
        this.totalQuestion = numberOfAnsweredQuestions;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection scores() {
        return Arrays.asList(new Object[][]{
                {10, 5, 50},
                {9, 3, (float) 33.3},
                {9, 6, (float) 66.7},
                {4, 3, 75},
                {10, 0, 0},
                {0, 0, 0},
                {1, 1, 100},
        });
    }

    @Test
    public void percentageScoreIsAboveZero() {
        //given
        Score score = new Score(totalQuestion, numberOfCorrectAnswers);

        //when
        float percentageScore = score.getPercentageScore();

        //then
        assertEquals(expectedResult, percentageScore, 0);
    }
}