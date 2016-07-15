package pl.webfly.sensei.trainer;

import java.util.LinkedList;
import java.util.List;

public class Trainer implements TrainerInterface {

    private int questionLimit;
    private int numberOfReplies;
    private int currentQuestionId = 0;
    private List<Question> questions = new LinkedList<>();

    public Trainer(TrainerParams Params) {

        this.questionLimit = Params.getQuestionLimit();
        this.numberOfReplies = Params.getNumberOfReplies();
        generate();
    }

    private void generate() {
        for (int i = 1; i <= questionLimit; i++) {
            Question question = new Question(numberOfReplies);
            questions.add(question);
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean isFinished() {
        return questions.get(questions.size() - 1).isAnswered();
    }

    @Override
    public Score getScores() throws Exception {
        int answeredQuestions = 0;
        int correctAnswers = 0;
        for (Question question : questions) {
            if (!question.isAnswered()) {
                break;
            }
            answeredQuestions++;
            if (question.isCorrect()) {
                correctAnswers++;
            }
        }
        return new Score(answeredQuestions, correctAnswers);
    }

    @Override
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionId);
    }

    @Override
    public void moveToNextQuestion() throws Exception {
        if (currentQuestionId == (questions.size() - 1)) {
            throw new Exception("Test is finished, there is no next question");
        }
        currentQuestionId++;
    }

    @Override
    public int getTotalQuestionQnt() {
        return questions.size();
    }

    @Override
    public int getCurrentQuestionNr() {
        return currentQuestionId + 1;
    }
}
