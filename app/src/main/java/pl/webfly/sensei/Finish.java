package pl.webfly.sensei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import pl.webfly.sensei.trainer.Score;

/**
 * Created by jbukowski on 2016-06-27
 * <p/>
 * Package: pl.webfly.sensei
 * Project: Tutorial
 */
public class Finish extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        Intent intent = getIntent();
        Score score = (Score) intent.getParcelableExtra("scores");


        TextView scoreText = (TextView) findViewById(R.id.percentageScore);
        scoreText.setText(Float.toString(score.getPercentageScore()) + "% (" + score.getCorrectAnswersQnt() + "/" + score.getAnsweredQuestionQnt() + ")");


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }
}
