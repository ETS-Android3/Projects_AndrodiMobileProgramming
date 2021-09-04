package com.example.cardaropoligiuseppe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContoActivity extends AppCompatActivity {

    private TextView tv_primo, tv_secondo, tv_contorno, tv_frutta, tv_totale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conto);

        tv_primo = findViewById(R.id.tv_primo);
        tv_secondo = findViewById(R.id.tv_secondo);
        tv_frutta = findViewById(R.id.tv_frutta);
        tv_contorno = findViewById(R.id.tv_contorno);
        tv_totale = findViewById(R.id.tv_totale_pasto);

        Intent conto = getIntent();

        int primo = conto.getIntExtra("PRIMO", 0);
        int secondo = conto.getIntExtra("SECONDO", 0);
        int contorno = conto.getIntExtra("CONTORNO", 0);
        int frutta = conto.getIntExtra("FRUTTA", 0);

        tv_primo.setText("Primo: " + primo + "€");
        tv_secondo.setText("Secondo: " + secondo + "€");
        tv_contorno.setText("Contorno: " + contorno + "€");
        tv_frutta.setText("Frutta: " + frutta + "€");

        tv_totale.setText("Totale pasto: " + (primo + secondo + contorno + frutta) + "€");
    }
}
