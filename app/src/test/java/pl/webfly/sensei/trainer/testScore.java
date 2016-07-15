package pl.webfly.sensei.trainer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbukowski on 2016-06-14
 * <p/>
 * Package: com.example.jbukowski.tutorial.trainer
 * Project: Tutorial
 */

@RunWith(Parameterized.class)
public class testScore {

    private final int totalQuestion;
    private final int answeredQuestion;
    private final float expectedResult;

    public testScore(int totalQuestion, int answeredQuestion, float expectedResult) {
        this.totalQuestion = totalQuestion;
        this.answeredQuestion = answeredQuestion;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection scores() {
        return Arrays.asList(new Object[][]{
                {10, 5, 50},
                {9, 3, (float) 33.33},
                {10, 0, 0},
                {0, 0, 0},
                {1, 1, 100},
        });
    }

    @Test
    public void percentageScoreIsAboveZero() {
        //given
        Score score = new Score(totalQuestion, answeredQuestion);

        //when
        float percentageScore = score.getPercentageScore();

        //then
        assertEquals(expectedResult, percentageScore, 0.01);
    }
}
