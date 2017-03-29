package pl.webfly.sensei.trainer;

import java.lang.reflect.Field;

/**
 * Created by jbukowski on 2016-06-15
 *
 * Package: com.example.jbukowski.tutorial.trainer
 * Project: Tutorial
 */
public class testAbstract {
    protected void setCorrectAnswer (Question question, int correctAnswer) throws NoSuchFieldException, IllegalAccessException {
        Class questionClass = question.getClass();
        try {
            Field f = questionClass.getDeclaredField("correctAnswer");
            f.setAccessible(true);
            f.set(question, correctAnswer);
        }
        catch (Exception e) {
            Field f = questionClass.getSuperclass().getDeclaredField("correctAnswer");
            f.setAccessible(true);
            f.set(question, correctAnswer);
        }

    }

}
