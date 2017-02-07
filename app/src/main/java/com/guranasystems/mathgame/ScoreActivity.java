package com.guranasystems.mathgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewScore;
    TextView textViewLevel;
    int score;
    int level;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Button buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(this);

        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewLevel = (TextView) findViewById(R.id.textViewLevel);

        // Get saved score and level
        sharedPreferences = getSharedPreferences(GameActivity.SCORE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        score = sharedPreferences.getInt(GameActivity.SCORE, 0);
        level = sharedPreferences.getInt(GameActivity.LEVEL, 1);

        // Set text
        textViewScore.setText(String.format(
                Locale.getDefault(),
                getResources().getString(R.string.points),
                score));
        textViewLevel.setText(String.format(
                Locale.getDefault(),
                getResources().getString(R.string.level),
                level));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save current score and level
        editor.putInt(GameActivity.SCORE, score);
        editor.putInt(GameActivity.LEVEL, level);
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        // Reset score and level
        score = 0;
        level = 1;

        // Save new status
        editor.putInt(GameActivity.SCORE, score);
        editor.putInt(GameActivity.LEVEL, level);
        editor.apply();

        // Update text
        textViewScore.setText(String.format(
                Locale.getDefault(),
                getResources().getString(R.string.points),
                score));
        textViewLevel.setText(String.format(
                Locale.getDefault(),
                getResources().getString(R.string.level),
                level));
    }
}
