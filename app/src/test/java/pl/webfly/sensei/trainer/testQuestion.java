package pl.webfly.sensei.trainer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testQuestion extends testAbstract {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = Exception.class)
    public void answerOutOfAllowedRange() throws Exception {
        //given
        Question question = new Question(5);

        //when
        question.setAnswer(5);

        //then
        thrown.expect(Exception.class);
    }

    @Test
    public void answerIsCorrect() throws Exception {
        //given
        Question question = new Question(5);
        setCorrectAnswer(question, 1);

        //when
        question.setAnswer(1);

        //then
        assertTrue(question.isCorrect());
    }

    @Test
    public void answerIsWrong() throws Exception {
        //given
        Question question = new Question(5);
        setCorrectAnswer(question, 1);

        //when
        question.setAnswer(2);

        //then
        assertFalse(question.isCorrect());
    }

    @Test(expected = Exception.class)
    public void isCorrectFailOnNotAnsweredQuestion() throws Exception {
        //given
        Question question = new Question(5);

        //when
        question.isCorrect();

        //then
        thrown.expect(Exception.class);
    }


    @Test
    public void isNotAnswered() throws Exception {
        //given
        Question question = new Question(5);

        //when
        //then
        assertFalse(question.isAnswered());
    }

    @Test
    public void isAnswered() throws Exception {
        //given
        Question question = new Question(5);

        //when
        question.setAnswer(2);

        //then
        assertTrue(question.isAnswered());
    }


}