package pl.webfly.sensei.trainer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
}
