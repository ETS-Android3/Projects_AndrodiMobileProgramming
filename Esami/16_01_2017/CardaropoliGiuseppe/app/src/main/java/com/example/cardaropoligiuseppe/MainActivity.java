package com.example.cardaropoligiuseppe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_stringa, tv_numero;
    private final int REQUEST_CODE_A = 123;
    private final int REQUEST_CODE_B = 456;
    private String stringa = "", numero = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            stringa = savedInstanceState.getString("STRINGA");
            numero = savedInstanceState.getString("NUMERO");
        }

        tv_stringa = findViewById(R.id.tv_stringa);
        tv_numero = findViewById(R.id.tv_numero);

        tv_stringa.setText(stringa);
        tv_numero.setText(numero);
    }

    public void onClickActivityA(View v) {
        Intent activityA = new Intent(getApplicationContext(), ActivityA.class);
        startActivityForResult(activityA, REQUEST_CODE_A);
    }

    public void onClickActivityB(View v) {
        Intent activityB = new Intent(getApplicationContext(), ActivityB.class);
        startActivityForResult(activityB, REQUEST_CODE_B);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case REQUEST_CODE_A:
                    stringa = data.getStringExtra("STRINGA");
                    System.out.println(stringa);
                    tv_stringa.setText(stringa);
                    break;
                case REQUEST_CODE_B:
                    numero = data.getStringExtra("NUMERO");
                    tv_numero.setText(numero);
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("STRINGA", stringa);
        outState.putString("NUMERO", numero);
    }
}