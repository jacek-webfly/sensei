package pl.webfly.sensei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import pl.webfly.sensei.trainer.TrainerParams;

public class Start extends AppCompatActivity implements View.OnClickListener {

    public static final int QUESTION_LIMIT = 10;
    private final String APP_VERSION = "1.0.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Button predict_easy = (Button) findViewById(R.id.predict_easy);
        predict_easy.setOnClickListener(this);
        Button predict_medium = (Button) findViewById(R.id.predict_medium);
        predict_medium.setOnClickListener(this);
        Button predict_hard = (Button) findViewById(R.id.predict_hard);
        predict_hard.setOnClickListener(this);
        Button guess_easy = (Button) findViewById(R.id.guess_easy);
        guess_easy.setOnClickListener(this);
        Button guess_medium = (Button) findViewById(R.id.guess_medium);
        guess_medium.setOnClickListener(this);
        Button guess_hard = (Button) findViewById(R.id.guess_hard);
        guess_hard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TrainerParams params = new TrainerParams();
        switch (v.getId()) {
            case (R.id.predict_easy):
                params = new TrainerParams(QUESTION_LIMIT, 2, TrainerParams.TrainingTypes.PREDICT);
                break;
            case (R.id.predict_medium):
                params = new TrainerParams(QUESTION_LIMIT, 4, TrainerParams.TrainingTypes.PREDICT);
                break;
            case (R.id.predict_hard):
                params = new TrainerParams(QUESTION_LIMIT, 9, TrainerParams.TrainingTypes.PREDICT);
                break;
            case (R.id.guess_easy):
                params = new TrainerParams(QUESTION_LIMIT, 2, TrainerParams.TrainingTypes.GUESS);
                break;
            case (R.id.guess_medium):
                params = new TrainerParams(QUESTION_LIMIT, 4, TrainerParams.TrainingTypes.GUESS);
                break;
            case (R.id.guess_hard):
                params = new TrainerParams(QUESTION_LIMIT, 9, TrainerParams.TrainingTypes.GUESS);
                break;

        }

        Intent intent = new Intent(this, Question.class);
        intent.putExtra("trainerParams", params);
        startActivity(intent);
    }
}
