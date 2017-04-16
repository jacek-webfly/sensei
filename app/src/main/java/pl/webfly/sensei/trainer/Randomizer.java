package pl.webfly.sensei.trainer;

import java.util.Random;

public class Randomizer {

    private final Random random;

    public Randomizer() {
        random = new Random();
    }

    public int getRandomInt(int max) {
        return getRandomInt(0, max);
    }

    public int getRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value should be less than maximum value");
        }
        return random.nextInt(max - min) + min;
    }
}