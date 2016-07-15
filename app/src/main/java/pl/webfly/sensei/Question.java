package pl.webfly.sensei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import pl.webfly.sensei.trainer.Trainer;
import pl.webfly.sensei.trainer.TrainerParams;

import java.util.HashMap;
import java.util.Map;

public class Question extends AppCompatActivity implements View.OnClickListener {

    private final int[] buttons = {
            R.id.answer_0, R.id.answer_1, R.id.answer_2,
            R.id.answer_3, R.id.answer_4, R.id.answer_5,
            R.id.answer_6, R.id.answer_7, R.id.answer_8};

    private Map<Integer, Integer> repliesMap = new HashMap<>();


    private Trainer trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            repliesMap.put(2, 0);
            repliesMap.put(4, 1);
            repliesMap.put(9, 2);
            setContentView(R.layout.question);

            Intent intent = getIntent();
            TrainerParams params = intent.getParcelableExtra("trainerParams");
            trainer = new Trainer(params);

            ViewGroup inclusionViewGroup = (ViewGroup) findViewById(R.id.inclusionlayout);

            if (params.getNumberOfReplies() == 2) {
                View child1 = LayoutInflater.from(this).inflate(R.layout.buttons_2, null);
                inclusionViewGroup.addView(child1);
            }
            if (params.getNumberOfReplies() == 4) {
                View child1 = LayoutInflater.from(this).inflate(R.layout.buttons_4, null);
                inclusionViewGroup.addView(child1);
            }
            if (params.getNumberOfReplies() == 9) {
                View child1 = LayoutInflater.from(this).inflate(R.layout.buttons_9, null);
                inclusionViewGroup.addView(child1);
            }


            if (params.getNumberOfReplies() >= 2) {
                Button b1 = (Button) findViewById(R.id.answer_0);
                b1.setOnClickListener(this);
                Button b2 = (Button) findViewById(R.id.answer_1);
                b2.setOnClickListener(this);
            }
            if (params.getNumberOfReplies() >= 4) {
                Button b3 = (Button) findViewById(R.id.answer_2);
                b3.setOnClickListener(this);
                Button b4 = (Button) findViewById(R.id.answer_3);
                b4.setOnClickListener(this);
            }
            if (params.getNumberOfReplies() >= 9) {
                Button b5 = (Button) findViewById(R.id.answer_4);
                b5.setOnClickListener(this);
                Button b6 = (Button) findViewById(R.id.answer_5);
                b6.setOnClickListener(this);
                Button b7 = (Button) findViewById(R.id.answer_6);
                b7.setOnClickListener(this);
                Button b8 = (Button) findViewById(R.id.answer_7);
                b8.setOnClickListener(this);
                Button b9 = (Button) findViewById(R.id.answer_8);
                b9.setOnClickListener(this);
            }


            updateQuestionCounters();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            updateQuestionCounters();
            switch (v.getId()) {
                case (R.id.answer_0):
                    trainer.getCurrentQuestion().setAnswer(0);
                    break;
                case (R.id.answer_1):
                    trainer.getCurrentQuestion().setAnswer(1);
                    break;
                case (R.id.answer_2):
                    trainer.getCurrentQuestion().setAnswer(2);
                    break;
                case (R.id.answer_3):
                    trainer.getCurrentQuestion().setAnswer(3);
                    break;
                case (R.id.answer_4):
                    trainer.getCurrentQuestion().setAnswer(4);
                    break;
                case (R.id.answer_5):
                    trainer.getCurrentQuestion().setAnswer(5);
                    break;
                case (R.id.answer_6):
                    trainer.getCurrentQuestion().setAnswer(6);
                    break;
                case (R.id.answer_7):
                    trainer.getCurrentQuestion().setAnswer(7);
                    break;
                case (R.id.answer_8):
                    trainer.getCurrentQuestion().setAnswer(8);
                    break;
                default:
                    throw new Exception("There is no answer given");

            }

            int i = trainer.getCurrentQuestion().getCorrectAnswer();
            Button correct = (Button) findViewById(buttons[i]);
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
}
