package com.example.a18_datastoragepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView[] tv_highscores = new TextView[3];
    private TextView tv_score;
    private int actual_score = 0;
    private String[] highscores_name = new String[3];
    private int[] highscores = new int[3];
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_score = findViewById(R.id.tv_score);

        tv_highscores[0] = findViewById(R.id.highscore_1);
        tv_highscores[1] = findViewById(R.id.highscore_2);
        tv_highscores[2] = findViewById(R.id.highscore_3);

        // otteniamo il riferimento allo SharedPreferences
        preferences = getSharedPreferences("file_highscores", MODE_PRIVATE);

        tv_score.setText(actual_score + "");
    }

    public void onClickResetScore(View v) {
        actual_score = 0;
        tv_score.setText(actual_score + "");
    }

    public void onClickPlus(View v) {
        actual_score++;
        tv_score.setText(actual_score + "");
    }

    public void onClickLess(View v) {
        if (actual_score >= 0) {
            actual_score--;
            tv_score.setText(actual_score + "");
        }
    }

    public void onClickResetHighscores(View v) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        updateHighscores();
    }

    public void onClickNewHighscore(View v) {
        Intent i = new Intent(getApplicationContext(), NewHighscore.class);
        i.putExtra("SCORE", actual_score);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateHighscores();
    }

    private void updateHighscores() {
        highscores_name[0] = preferences.getString("NAME1", "name");
        highscores_name[1] = preferences.getString("NAME2", "name");
        highscores_name[2] = preferences.getString("NAME3", "name");

        highscores[0] = preferences.getInt("HIGHSCORE1", 0);
        highscores[1] = preferences.getInt("HIGHSCORE2", 0);
        highscores[2] = preferences.getInt("HIGHSCORE3", 0);

        for (int i = 0; i < 3; i++)
            tv_highscores[i].setText(highscores_name[i] + ": " + highscores[i]);
    }
}