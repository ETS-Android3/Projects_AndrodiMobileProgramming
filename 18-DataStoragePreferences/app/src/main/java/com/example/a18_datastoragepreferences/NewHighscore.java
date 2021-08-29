package com.example.a18_datastoragepreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class NewHighscore extends Activity {

    private EditText editText;
    private int score;
    private int[] highscores = new int[3];
    private String[] highscores_name = new String[3];
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_highscore);

        editText = findViewById(R.id.edit_name);

        Intent i = getIntent();
        score = i.getIntExtra("SCORE", 0);

        preferences = getSharedPreferences("file_highscores", MODE_PRIVATE);

        highscores[0] = preferences.getInt("HIGHSCORE1", 0);
        highscores[1] = preferences.getInt("HIGHSCORE2", 0);
        highscores[2] = preferences.getInt("HIGHSCORE3", 0);
        highscores_name[0] = preferences.getString("NAME1", "name");
        highscores_name[1] = preferences.getString("NAME2", "name");
        highscores_name[2] = preferences.getString("NAME3", "name");

    }

    public void onClickInsertNewScore(View v) {
        SharedPreferences.Editor editor = preferences.edit();

        if (score >= highscores[0]) {
            editor.putString("NAME1", editText.getText().toString());
            editor.putInt("HIGHSCORE1", score);
            editor.putString("NAME2", highscores_name[0]);
            editor.putInt("HIGHSCORE2", highscores[0]);
            editor.putString("NAME3", highscores_name[1]);
            editor.putInt("HIGHSCORE3", highscores[1]);
        }
        else if (highscores[1] <= score) {
            editor.putString("NAME2", editText.getText().toString());
            editor.putInt("HIGHSCORE2", score);
            editor.putString("NAME3", highscores_name[1]);
            editor.putInt("HIGHSCORE3", highscores[1]);
        }
        else if (highscores[2] <= score) {
            editor.putString("NAME3", editText.getText().toString());
            editor.putInt("HIGHSCORE3", score);
        }

        editor.apply();
        onBackPressed();
    }
}
