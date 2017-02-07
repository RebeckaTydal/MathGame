package com.guranasystems.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        Button buttonScore = (Button) findViewById(R.id.buttonScore);
        Button buttonHelp = (Button) findViewById(R.id.buttonHelp);

        buttonPlay.setOnClickListener(this);
        buttonScore.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonPlay:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonScore:
                intent = new Intent(this, ScoreActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonHelp:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
