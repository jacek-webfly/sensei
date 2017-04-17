package pl.webfly.sensei.trainer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class testRandomizer {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenGivenWrongArguments() throws Exception {
        //given
        Randomizer randomizer = new Randomizer();

        //expected
        thrown.expect(IllegalArgumentException.class);

        //then
        randomizer.getRandomInt(5,5);
    }
    @Test
    public void shouldReturnRandomValueForGivenRange() throws Exception {
        //given
        Randomizer randomizer = new Randomizer();
        int min = 1;
        int max = 5;

        //when
        int randomInt = randomizer.getRandomInt(min, max);

        //then
        assertTrue(randomInt <= max);
        assertTrue(randomInt >= min);
    }
}