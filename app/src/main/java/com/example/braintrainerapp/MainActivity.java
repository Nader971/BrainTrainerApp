package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton, answerButton0, answerButton1, answerButton2, answerButton3, playAgainButton;
    TextView questionsTextView, scoreTextView, timeTextView, correctTextView;
    ConstraintLayout goConstraintLayout, gameConstraintLayout;
    Random random = new Random();
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfRightAnswer, scoreOfCorrect, totalScore;
    CountDownTimer countDownTimer;
    boolean gameState = false;


    public void start(View view) {

        gameState = true;
        goConstraintLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void questionsCreat() {

        int a = random.nextInt(50);
        int b = random.nextInt(50);

        questionsTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfRightAnswer = random.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {

            if (i == locationOfRightAnswer) {

                answers.add(a+b);

            } else {
                int wrongAnswer = random.nextInt(100);

                while (wrongAnswer == (a+b)) {
                    wrongAnswer = random.nextInt(100);
                }

                answers.add(wrongAnswer);

            }

        }

        answerButton0.setText(Integer.toString(answers.get(0)));
        answerButton1.setText(Integer.toString(answers.get(1)));
        answerButton2.setText(Integer.toString(answers.get(2)));
        answerButton3.setText(Integer.toString(answers.get(3)));

    }

    public void timeCount() {

        if (gameState) {
            countDownTimer = new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long l) {
                    timeTextView.setText(String.valueOf(l / 1000) + "s");
                }

                @Override
                public void onFinish() {
                    correctTextView.setText("Done!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    gameState = false;
                }
            }.start();
        }
    }

    public void playAgain(View view) {

        playAgainButton.setVisibility(View.INVISIBLE);
        gameState = true;
        if (gameState){
            scoreOfCorrect = 0;
            totalScore = 0;
            scoreTextView.setText(Integer.toString(scoreOfCorrect)+"/"+Integer.toString(totalScore));
            correctTextView.setText("");
            questionsCreat();
            timeCount();
        }

    }

    public void chooseAnswer(View view) {

        if(gameState) {
            totalScore++;


            if (view.getTag().toString().equals(Integer.toString(locationOfRightAnswer))) {
                correctTextView.setText("Correct:)!");
                scoreOfCorrect++;

            } else {
                correctTextView.setText("Wrong:(");
            }

            scoreTextView.setText(Integer.toString(scoreOfCorrect) + "/" + Integer.toString(totalScore));
            questionsCreat();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        answerButton0 = findViewById(R.id.answerButton0);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        playAgainButton = findViewById(R.id.playAgainButton);

        questionsTextView = findViewById(R.id.qeustionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.timerTextView);
        correctTextView = findViewById(R.id.correctTextView);
        goConstraintLayout = findViewById(R.id.goConstraintLayout);

        gameConstraintLayout = findViewById(R.id.gameConstraintLayout);

        goButton.setVisibility(View.VISIBLE);
        goConstraintLayout.setVisibility(View.INVISIBLE);


    }
}