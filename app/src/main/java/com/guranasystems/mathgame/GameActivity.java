package com.guranasystems.mathgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearNormal;
    LinearLayout linearHard;

    TextView textViewFirstEasy;
    TextView textViewSecondEasy;
    Button buttonEasyOne;
    Button buttonEasyTwo;
    Button buttonEasyThree;

    TextView textViewFirstNormal;
    TextView textViewSecondNormal;
    EditText editTextNormal;

    TextView textViewFirstHard;
    TextView textViewSecondHard;
    EditText editTextHard;

    static final String SCORE = "score";
    static final String LEVEL = "level";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int score;
    int level;
    int answerLevelOne;
    int answerLevelTwo;
    int answerLevelThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewFirstEasy = (TextView) findViewById(R.id.first_easy);
        textViewSecondEasy = (TextView) findViewById(R.id.second_easy);
        buttonEasyOne = (Button) findViewById(R.id.buttonAnswerOne);
        buttonEasyTwo = (Button) findViewById(R.id.buttonAnswerTwo);
        buttonEasyThree = (Button) findViewById(R.id.buttonAnswerThree);

        buttonEasyOne.setOnClickListener(this);
        buttonEasyTwo.setOnClickListener(this);
        buttonEasyThree.setOnClickListener(this);

        linearNormal = (LinearLayout) findViewById(R.id.linear_normal);
        linearHard = (LinearLayout) findViewById(R.id.linear_hard);

        newGame();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get saved score and level
        sharedPreferences = getSharedPreferences(GameActivity.SCORE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        score = sharedPreferences.getInt(GameActivity.SCORE, 0);
        level = sharedPreferences.getInt(GameActivity.LEVEL, 1);

        if (level == 1) {
            linearNormal.setVisibility(View.INVISIBLE);
        }
        if (level < 3) {
            linearHard.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save current score and level
        editor.putInt(GameActivity.SCORE, score);
        editor.putInt(GameActivity.LEVEL, level);
        editor.apply();
    }

    void newGame() {
        Random randInt = new Random();

        int firstNumber = randInt.nextInt(20);
        int secondNumber = randInt.nextInt(20);
        int thirdNumber = randInt.nextInt(20);

        firstNumber++;
        secondNumber++;
        thirdNumber++;

        setupLevelOne(firstNumber, secondNumber, thirdNumber);
        if (level > 1) {
            linearNormal.setVisibility(View.VISIBLE);
            setupLevelTwo(firstNumber, secondNumber, thirdNumber);
        }
        if (level == 3) {
            linearHard.setVisibility(View.VISIBLE);
            setupLevelThree(firstNumber, secondNumber, thirdNumber);
        }
    }

    void setupLevelOne(int firstNumber, int secondNumber, int thirdNumber) {
        Random randInt = new Random();
        int displayAnswers = randInt.nextInt(3);

        // Save correct answers
        answerLevelOne = firstNumber + secondNumber;
        answerLevelTwo = firstNumber - secondNumber;
        answerLevelThree = firstNumber * secondNumber;

        // Convert numbers to text
        String first = "" + firstNumber;
        String second = "" + secondNumber;
        String guessOne = "" + (firstNumber + secondNumber);
        String guessTwo = "" + (secondNumber + thirdNumber);
        String guessThree = "" + (firstNumber + thirdNumber);

        // Display numbers to add up
        textViewFirstEasy.setText(first);
        textViewSecondEasy.setText(second);

        // Display alternatives on buttons
        switch (displayAnswers) {
            case 0:
                buttonEasyOne.setText(guessOne);
                buttonEasyTwo.setText(guessTwo);
                buttonEasyThree.setText(guessThree);
                break;
            case 1:
                buttonEasyOne.setText(guessTwo);
                buttonEasyTwo.setText(guessThree);
                buttonEasyThree.setText(guessOne);
                break;
            case 2:
                buttonEasyOne.setText(guessThree);
                buttonEasyTwo.setText(guessOne);
                buttonEasyThree.setText(guessTwo);
                break;
        }
    }

    void setupLevelTwo(int firstNumber, int secondNumber, int thirdNumber) {

    }

    void setupLevelThree(int firstNumber, int secondNumber, int thirdNumber) {

    }

    @Override
    public void onClick(View v) {
        int answerGiven = 0;

        switch (v.getId()) {
            case R.id.buttonAnswerOne:
                answerGiven = Integer.parseInt("" + buttonEasyOne.getText());
                break;
            case R.id.buttonAnswerTwo:
                answerGiven = Integer.parseInt("" + buttonEasyTwo.getText());
                break;
            case R.id.buttonAnswerThree:
                answerGiven = Integer.parseInt("" + buttonEasyThree.getText());
                break;
        }

        // Check if correct answer
        if (answerGiven == answerLevelOne) {
            score++;
            if (level == 1) {
                level = 2;
            }
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sorry", Toast.LENGTH_SHORT).show();
        }

        // New game
        newGame();
    }
}
