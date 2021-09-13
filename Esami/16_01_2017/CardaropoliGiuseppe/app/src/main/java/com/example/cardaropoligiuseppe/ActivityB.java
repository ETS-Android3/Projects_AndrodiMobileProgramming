package com.example.cardaropoligiuseppe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {

    private EditText edit_numero;
    private String numero = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        if (savedInstanceState != null) {
            numero = savedInstanceState.getString("NUMERO");
        }

        edit_numero = findViewById(R.id.edit_numero);
        edit_numero.setText(numero);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("NUMERO", numero);
    }

    public void onClickRitorna(View v) {
        Intent data = new Intent();
        numero = edit_numero.getText().toString();
        data.putExtra("NUMERO", numero);
        setResult(RESULT_OK, data);
        onBackPressed();
    }
}
