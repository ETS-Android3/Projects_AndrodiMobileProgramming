package com.example.cardaropoligiuseppe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tv_num_da_trovare, tv_tempo_impiegato;
    private LinearLayout tabella, ll_nuova_partita;
    private ArrayList<Button> bottoni = new ArrayList<>();
    private Random random = new Random();
    private int daIndovinare = 1;
    private int numeri[] = new int[]{1,2,3,4,5,6,7,8,9};
    private long tempo_di_inizio;
    private long penalità = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_num_da_trovare = findViewById(R.id.tv_num_da_trovare);
        tv_tempo_impiegato = findViewById(R.id.tv_tempo_impiegato);
        tabella = findViewById(R.id.tabella);
        ll_nuova_partita = findViewById(R.id.ll_nuova_partita);

        tv_num_da_trovare.setText(""+daIndovinare);

        for (int i = 0; i < tabella.getChildCount(); i++) {
            LinearLayout riga = (LinearLayout) tabella.getChildAt(i);
            for (int j = 0; j < tabella.getChildCount(); j++) {
                Button button = (Button) riga.getChildAt(j);
                button.setOnClickListener(this::onClick);
                bottoni.add(button);
            }
        }
        generaNumeri();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tempo_di_inizio = System.currentTimeMillis();
    }

    private void onClick(View view) {
        Button b = (Button) view;
        int num = Integer.parseInt(b.getText().toString());
        if (num == daIndovinare) {
            daIndovinare++;
            generaNumeri();
            if (daIndovinare == 10) {
                tv_num_da_trovare.setText("Partita finita");
                ll_nuova_partita.setVisibility(View.VISIBLE);
                float tempo_impiegato = ((System.currentTimeMillis()) - tempo_di_inizio + penalità) / 1000;
                tv_tempo_impiegato.setText(String.format("Tempo: %7.2f sec", tempo_impiegato));
            }
            else tv_num_da_trovare.setText(Integer.toString(daIndovinare));
        } else {
            penalità += 1000;
        }
    }

    private void generaNumeri() {
        for (int i = 0; i < numeri.length; i++) {
            int pos_random = random.nextInt(numeri.length);
            int tmp = numeri[i];
            numeri[i] = numeri[pos_random];
            numeri[pos_random] = tmp;
        }

        for (int i = 0; i < bottoni.size(); i++) {
            bottoni.get(i).setText(Integer.toString(numeri[i]));
        }
    }

    public void onClickNuovaPartita(View v) {
        ll_nuova_partita.setVisibility(View.INVISIBLE);
        daIndovinare = 1;
        generaNumeri();
        this.onResume();
    }

}