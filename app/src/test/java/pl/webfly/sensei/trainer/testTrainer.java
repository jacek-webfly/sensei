package pl.webfly.sensei.trainer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class testTrainer {

    public static final int CORRECT_ANSWER = 1;
    public static final int INCORRECT_ANSWER = 0;
    @Mock
    private Randomizer randomizer;

    @Before
    public void setUp() throws Exception {
        when(randomizer.getRandomInt(anyInt())).thenReturn(CORRECT_ANSWER);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateTrainerWithCorrectNumberOfQuestions() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(20, 5, TrainerParams.TrainingTypes.GUESS));

        //when
        List<Question> questions = test.getQuestions();

        //then
        assertEquals(questions.size(), 20);
    }

    @Test
    public void shouldReturnScoreAfterCreateTrainer() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(2, 2, TrainerParams.TrainingTypes.GUESS));

        //when
        Score scores = test.getScores();

        //then
        assertEquals(scores.getPercentageScore(), 0, 0);
    }

    @Test
    public void behaviorTestTrainerCasePredict() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(2, 2, TrainerParams.TrainingTypes.GUESS), randomizer);

        //when
        Question currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(CORRECT_ANSWER);

        //then
        assertEquals(2, test.getTotalQuestionQnt());
        assertFalse(test.isFinished());
        assertEquals(1, test.getCurrentQuestionNr());
        Score scores = test.getScores();
        assertEquals(100, scores.getPercentageScore(), 0);


        //when
        test.moveToNextQuestion();
        currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(INCORRECT_ANSWER);

        //then
        assertTrue(test.isFinished());
        assertEquals(2, test.getCurrentQuestionNr());
        scores = test.getScores();
        assertEquals(50, scores.getPercentageScore(), 0);
    }

    @Test
    public void behaviorTestTrainerCaseGuess() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(3, 2, TrainerParams.TrainingTypes.PREDICT), randomizer);

        //when
        Question currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(CORRECT_ANSWER);

        //then
        assertEquals(3, test.getTotalQuestionQnt());
        assertFalse(test.isFinished());
        assertEquals(1, test.getCurrentQuestionNr());
        Score scores = test.getScores();
        assertEquals(100, scores.getPercentageScore(), 0);

        //when
        test.moveToNextQuestion();
        currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(INCORRECT_ANSWER);

        //then
        assertFalse(test.isFinished());
        assertEquals(2, test.getCurrentQuestionNr());
        scores = test.getScores();
        assertEquals(50, scores.getPercentageScore(), 0);

        //when
        test.moveToNextQuestion();
        currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(CORRECT_ANSWER);

        //then
        assertTrue(test.isFinished());
        assertEquals(3, test.getCurrentQuestionNr());
        scores = test.getScores();
        assertEquals((float)66.7, scores.getPercentageScore(), 0);
    }

    @Test
    public void testTrainerCaseQuestionOverBound() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(1, 2, TrainerParams.TrainingTypes.GUESS), randomizer);

        //when
        Question currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(1);

        //then
        assertEquals(1, test.getTotalQuestionQnt());
        assertTrue(test.isFinished());
        assertEquals(1, test.getCurrentQuestionNr());
        Score scores = test.getScores();
        assertEquals(100, scores.getPercentageScore(), 0);

        //expected
        thrown.expect(Exception.class);

        //when
        test.moveToNextQuestion();
    }
}

