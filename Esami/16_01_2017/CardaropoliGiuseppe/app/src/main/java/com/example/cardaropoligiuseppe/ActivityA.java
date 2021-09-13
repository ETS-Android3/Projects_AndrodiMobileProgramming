package com.example.cardaropoligiuseppe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityA extends AppCompatActivity {

    private EditText edit_stringa;
    private String stringa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        if (savedInstanceState != null) {
            stringa = savedInstanceState.getString("STRINGA");
        }

        edit_stringa = findViewById(R.id.edit_stringa);
        edit_stringa.setText(stringa);
    }

    public void onClickRitorna(View v) {
        stringa = edit_stringa.getText().toString();

        if (stringa.contains("0") || stringa.contains("1") || stringa.contains("2") ||
            stringa.contains("3") || stringa.contains("4") || stringa.contains("5") ||
            stringa.contains("6") || stringa.contains("7") || stringa.contains("8") ||
            stringa.contains("9")) {
            Toast.makeText(getApplicationContext(), "La stringa non deve contenere numeri", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent data = new Intent();
            data.putExtra("STRINGA", stringa);
            setResult(RESULT_OK, data);
            onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("STRINGA", stringa);
    }
}
