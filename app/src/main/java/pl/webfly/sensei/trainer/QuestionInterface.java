package pl.webfly.sensei.trainer;

/**
 * Created by jbukowski on 2016-06-28
 *
 * Package: ${PACKAGE_NAME}
 * Project: Tutorial
 *
 */
public interface QuestionInterface {
    boolean isCorrect() throws Exception;
    boolean isAnswered();

    void setAnswer(int answer) throws Exception;
}
