package com.example.cardaropoligiuseppe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Scontrino extends AppCompatActivity {

    private TextView tv_resoconto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_conto);

        tv_resoconto = findViewById(R.id.tv_resoconto);

        Intent intent = getIntent();
        String[] nomi = intent.getStringArrayExtra("NOMI");
        float[] prezzi = intent.getFloatArrayExtra("PREZZI");
        float conto = intent.getFloatExtra("CONTO", 0.0f);

        String resoconto = "Hai consumato:\n\n";
        for (int i = 0; i < nomi.length; i++) {
            resoconto += String.format("%d. %s \t %7.2f€\n", (i+1), nomi[i], prezzi[i]);
        }
        resoconto += String.format("\nTotale:\t%7.2f€", conto);

        tv_resoconto.setText(resoconto);
    }
}
