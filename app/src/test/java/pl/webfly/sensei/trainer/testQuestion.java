package pl.webfly.sensei.trainer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.webfly.sensei.trainer.Exceptions.QuestionIsNotAnsweredException;
import pl.webfly.sensei.trainer.Exceptions.AnswerOutOfBoundsException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class testQuestion {

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
    public void shouldThrowExceptionWhenGivenAnswerIsOutOfAllowedRange() throws Exception {
        //given
        Question question = new QuestionPredict(2, randomizer);

        //expected
        thrown.expect(AnswerOutOfBoundsException.class);

        //then
        question.setAnswer(5);
    }

    @Test
    public void shouldReturnTrueWhenGivenAnswerIsCorrect() throws Exception {
        //given
        Question question = new QuestionPredict(5, randomizer);

        //when
        question.setAnswer(CORRECT_ANSWER);

        //then
        assertTrue(question.isCorrect());
        assertEquals(CORRECT_ANSWER, question.getCorrectAnswer());
    }

    @Test
    public void shouldReturnFalseWhenGivenAnswerIsIncorrect() throws Exception {
        //given
        Question question = new QuestionPredict(5, randomizer);

        //when
        question.setAnswer(INCORRECT_ANSWER);

        //then
        assertFalse(question.isCorrect());
    }

    @Test
    public void shouldThrowExceptionWhenCallIsCorrectMethodOnNotAnsweredQuestion() throws Exception {
        //given
        Question question = new QuestionPredict(5, randomizer);

        //expected
        thrown.expect(QuestionIsNotAnsweredException.class);

        //when
        question.isCorrect();
    }


    @Test
    public void shouldReturnFalseWhenCallIsAnsweredMethodOnNotAnsweredQuestion() throws Exception {
        //given
        Question question = new QuestionPredict(5, randomizer);

        //then
        assertFalse(question.isAnswered());
    }

    @Test
    public void shouldReturnTrueWhenCallIsAnsweredMethodOnAnsweredQuestion() throws Exception {
        //given
        Question question = new QuestionPredict(5, randomizer);

        //when
        question.setAnswer(2);

        //then
        assertTrue(question.isAnswered());
    }
}