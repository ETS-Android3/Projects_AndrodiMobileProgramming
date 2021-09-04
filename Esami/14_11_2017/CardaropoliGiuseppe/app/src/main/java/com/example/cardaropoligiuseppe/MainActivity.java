package com.example.cardaropoligiuseppe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int[] prezzi = new int[]{3, 5, 7};
    private TextView tv_totale;
    private RadioGroup radio_primo, radio_secondo, radio_contorno, radio_frutta;
    private int prezzo_totale = 0, primo = 0, secondo = 0, contorno = 0, frutta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio_primo = findViewById(R.id.radio_primo);
        radio_secondo = findViewById(R.id.radio_secondo);
        radio_contorno = findViewById(R.id.radio_contorno);
        radio_frutta = findViewById(R.id.radio_frutta);
        tv_totale = findViewById(R.id.tv_totale);

        tv_totale.setText("Totale: " +  prezzo_totale + "€");

        RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // 1 <= i <= 9
                switch (radioGroup.getId()) {
                    case R.id.radio_primo:
                        primo = prezzi[((i - 1) % 3)];
                        break;
                    case R.id.radio_secondo:
                        secondo = prezzi[((i - 1) % 3)];
                        break;
                    case R.id.radio_contorno:
                        contorno = prezzi[((i - 1) % 3)];
                        break;
                    case R.id.radio_frutta:
                        frutta = prezzi[((i - 1) % 3)];
                        break;
                }
                prezzo_totale = primo + secondo + contorno + frutta;
                tv_totale.setText("Totale: " +  prezzo_totale + "€");
            }
        };

        radio_primo.setOnCheckedChangeListener(checkedChangeListener);
        radio_secondo.setOnCheckedChangeListener(checkedChangeListener);
        radio_contorno.setOnCheckedChangeListener(checkedChangeListener);
        radio_frutta.setOnCheckedChangeListener(checkedChangeListener);
    }

    public void onClickVaiAllaCassa(View v) {
        Intent intent_conto = new Intent(getApplicationContext(), ContoActivity.class);
        intent_conto.putExtra("PRIMO", primo);
        intent_conto.putExtra("SECONDO", secondo);
        intent_conto.putExtra("CONTORNO", contorno);
        intent_conto.putExtra("FRUTTA", frutta);
        startActivity(intent_conto);
    }
}