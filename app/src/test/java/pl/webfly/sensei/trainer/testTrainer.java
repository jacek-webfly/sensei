package pl.webfly.sensei.trainer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class testTrainer extends testAbstract {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createTrainer() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(20, 5, TrainerParams.TrainingTypes.GUESS));

        //when
        List<Question> questions = test.getQuestions();

        //then
        assertEquals(questions.size(), 20);
    }

    @Test
    public void testGeneratedScore() throws Exception {
        //given
        Trainer test =  new Trainer(new TrainerParams(2, 2, TrainerParams.TrainingTypes.GUESS));

        //when
        Score scores = test.getScores();

        //then
        assertEquals(scores.getPercentageScore(), 0, 0);
    }

    @Test
    public void testTrainerCasePredict() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(2, 2, TrainerParams.TrainingTypes.GUESS));

        //when
        Question currentQuestion = test.getCurrentQuestion();
        setCorrectAnswer(currentQuestion, 1);
        currentQuestion.setAnswer(1);

        //then
        assertEquals(2, test.getTotalQuestionQnt());
        assertFalse(test.isFinished());
        assertEquals(1, test.getCurrentQuestionNr());
        Score scores = test.getScores();
        assertEquals(100, scores.getPercentageScore(), 0);


        //when
        test.moveToNextQuestion();
        currentQuestion = test.getCurrentQuestion();
        setCorrectAnswer(currentQuestion, 1);
        currentQuestion.setAnswer(0);

        //then
        assertTrue(test.isFinished());
        assertEquals(2, test.getCurrentQuestionNr());
        scores = test.getScores();
        assertEquals(50, scores.getPercentageScore(), 0);
    }
    @Test
    public void testTrainerCaseGuess() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(2, 2, TrainerParams.TrainingTypes.PREDICT));

        //when
        Question currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(1);

        //then
        assertEquals(2, test.getTotalQuestionQnt());
        assertFalse(test.isFinished());
        assertEquals(1, test.getCurrentQuestionNr());

        //when
        test.moveToNextQuestion();
        currentQuestion = test.getCurrentQuestion();
        currentQuestion.setAnswer(0);

        //then
        assertTrue(test.isFinished());
        assertEquals(2, test.getCurrentQuestionNr());
    }

    @Test(expected = Exception.class)
    public void testTrainerCaseQuestionOverBound() throws Exception {
        //given
        Trainer test = new Trainer(new TrainerParams(1, 2, TrainerParams.TrainingTypes.GUESS));

        //when
        Question currentQuestion = test.getCurrentQuestion();
        setCorrectAnswer(currentQuestion, 1);
        currentQuestion.setAnswer(1);

        //then
        assertEquals(1, test.getTotalQuestionQnt());
        assertTrue(test.isFinished());
        assertEquals(1, test.getCurrentQuestionNr());
        Score scores = test.getScores();
        assertEquals(100, scores.getPercentageScore(), 0);

        //when
        test.moveToNextQuestion();

        //then
        thrown.expect(Exception.class);
    }
}

