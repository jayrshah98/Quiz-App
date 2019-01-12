package com.example.jay.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    String NameHolder;
    TextView Name;
    Button LogOUT;

    private static final int REQUEST_CODE = 1;

    public static final String SHARED = "shared_highscore";
    public static final String KEY_HIGHSCORE = "keyHighscore";



    private TextView textViewHighscore;
    private int highscore;

    TextView textViewScore;
    private int final_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LogOUT = findViewById(R.id.button1);
        Name = findViewById(R.id.textView_name);

        Intent intent = getIntent();


        NameHolder = intent.getStringExtra(MainActivity.UserName);


        Name.setText("Hi " + NameHolder);


        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();

                Toast.makeText(Main2Activity.this, "Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });
        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore();

        textViewScore = findViewById(R.id.text_view_yourscore);

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(Main2Activity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.high, 0);
                updateScore(score);
                if (score > highscore) {
                    updateHighscore(score);

                }
            }
        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highest score: " + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highest score: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }


    private void updateScore(int scoreNew) {
        final_score = scoreNew;
        textViewScore.setText("Your score: " + final_score);

    }


}

