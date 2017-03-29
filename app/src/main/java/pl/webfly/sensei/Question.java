package pl.webfly.sensei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import pl.webfly.sensei.trainer.Trainer;
import pl.webfly.sensei.trainer.TrainerParams;
import java.util.HashMap;
import java.util.Map;

public class Question extends AppCompatActivity implements View.OnClickListener {

    private static final Map<Integer, Integer> buttonsLayoutMap;
    static {
        buttonsLayoutMap = new HashMap<>();
        buttonsLayoutMap.put(2, R.layout.buttons_2);
        buttonsLayoutMap.put(4, R.layout.buttons_4);
        buttonsLayoutMap.put(9, R.layout.buttons_9);
    };

    private static final Map<Integer, Integer> answersMap;
    static {
        answersMap = new HashMap<>();
        answersMap.put(R.id.answer_0, 0);
        answersMap.put(R.id.answer_1, 1);
        answersMap.put(R.id.answer_2, 2);
        answersMap.put(R.id.answer_3, 3);
        answersMap.put(R.id.answer_4, 4);
        answersMap.put(R.id.answer_5, 5);
        answersMap.put(R.id.answer_6, 6);
        answersMap.put(R.id.answer_7, 7);
        answersMap.put(R.id.answer_8, 8);
    };


    private Trainer trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            setContentView(R.layout.question);

            Intent intent = getIntent();
            TrainerParams params = intent.getParcelableExtra("trainerParams");
            trainer = new Trainer(params);

            ViewGroup inclusionViewGroup = (ViewGroup) findViewById(R.id.inclusionlayout);

            addButtonsLayout(params, inclusionViewGroup);

            createButtonsWithListeners(params);

            updateQuestionCounters();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createButtonsWithListeners(TrainerParams params) {

        Button buttons[] = new Button[params.getNumberOfReplies()];

        for (Object o : answersMap.entrySet()) {
            Map.Entry thisEntry = (Map.Entry) o;
            int numberOfReplies = params.getNumberOfReplies();
            int answerButtonNumber = (int) thisEntry.getValue();
            if (answerButtonNumber <  numberOfReplies) {
                buttons[answerButtonNumber] = (Button) findViewById((Integer) thisEntry.getKey());
                buttons[answerButtonNumber].setOnClickListener(this);
            }
        }
    }

    private void addButtonsLayout(TrainerParams params, ViewGroup inclusionViewGroup) throws Exception {

        if (!buttonsLayoutMap.containsKey(params.getNumberOfReplies())) {
            throw new Exception("There is no layout for given number of replies. Given: " + params.getNumberOfReplies());
        }
        View child1 = LayoutInflater.from(this).inflate(buttonsLayoutMap.get(params.getNumberOfReplies()), null);
        inclusionViewGroup.addView(child1);
    }

    @Override
    public void onClick(View v) {
        try {
            updateQuestionCounters();

            if (!answersMap.containsKey(v.getId())) {
                throw new Exception("There is defined answer for given button id. Given: " + v.getId());
            }
            trainer.getCurrentQuestion().setAnswer(answersMap.get(v.getId()));

            Integer answerButtonIdForGivenAnswerValue = getAnswerButtonIdForGivenAnswerValue(trainer.getCurrentQuestion().getCorrectAnswer());
            Button correct = (Button) findViewById(answerButtonIdForGivenAnswerValue);
            Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
            correct.startAnimation(fadeout);


            if (trainer.isFinished()) {
                Intent intent = new Intent(this, Finish.class);
                intent.putExtra("scores", trainer.getScores());

                // wgrane activity nie działa jak nalezy (breakpointy nie łapią)
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        setContentView(R.layout.finish); //where <next> is you target activity :)
//
//                    }
//                }, 2000);

                startActivity(intent);
                return;
            } else {
                trainer.moveToNextQuestion();
            }
            updateQuestionCounters();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateQuestionCounters() throws Exception {
        String textQuestionProgress = Integer.toString(trainer.getCurrentQuestionNr()) + "/" + Integer.toString(trainer.getTotalQuestionQnt());
        TextView questionProgress = (TextView) findViewById(R.id.questionProgress);
        questionProgress.setText(textQuestionProgress);
    }

    private static Integer getAnswerButtonIdForGivenAnswerValue(int answerValue) {
        for (Object o : answersMap.entrySet()) {
            Map.Entry thisEntry = (Map.Entry) o;
            if ((Integer) thisEntry.getValue() == answerValue) {
                return (Integer) thisEntry.getKey();
            }
        }
        return null;
    }
}
