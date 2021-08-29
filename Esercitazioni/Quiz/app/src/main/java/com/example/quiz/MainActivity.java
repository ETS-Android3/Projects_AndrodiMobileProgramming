package com.example.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE = 28;
    private TextView num_quesito, tv_quesito, tv_risp_valide, tv_risp_non_valide, tv_risp_totali;
    private ArrayList<Quesito> quesiti = new ArrayList<>();
    private Quesito corrente;
    private String[] testi;
    private boolean suggerimento_usato = false;
    private int[] risposte;
    private int quesito_corrente = 0;
    private int risp_valide = 0, risp_non_valide = 0, risp_totali = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            quesito_corrente = savedInstanceState.getInt("QUESTITO_CORRENTE");
            risp_valide = savedInstanceState.getInt("QUESTITI_VALIDI");
            risp_non_valide = savedInstanceState.getInt("QUESTITI_NON_VALIDI");
            risp_totali = savedInstanceState.getInt("QUESTITO_TOTALI");
        }

        num_quesito = findViewById(R.id.tv_num_quesito);
        tv_quesito = findViewById(R.id.tv_quesito);
        tv_risp_valide = findViewById(R.id.tv_risposte_corrette_valide);
        tv_risp_non_valide = findViewById(R.id.tv_risposte_corrette_non_valide);
        tv_risp_totali = findViewById(R.id.tv_risp_totali);

        testi = getResources().getStringArray(R.array.Quesiti);
        risposte = getResources().getIntArray(R.array.Risposte);

        for (int i = 0; i < testi.length; i++)
            quesiti.add(new Quesito(testi[i], 1 == risposte[i]));

        corrente = quesiti.get(quesito_corrente);

        tv_quesito.setText(corrente.getTesto());
        num_quesito.setText("Quesito n° " + (quesito_corrente+1));

        aggiornaContatori();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("QUESTITO_CORRENTE", quesito_corrente);
        outState.putInt("QUESTITI_VALIDI", risp_valide);
        outState.putInt("QUESTITI_NON_VALIDI", risp_non_valide);
        outState.putInt("QUESTITO_TOTALI", risp_totali);
    }

    public void onClickNext(View v) {
        quesito_corrente = ++quesito_corrente % quesiti.size();
        corrente = quesiti.get(quesito_corrente);
        tv_quesito.setText(corrente.getTesto());
        num_quesito.setText("Quesito n° " + ((quesito_corrente % quesiti.size())+1));
    }

    public void onClickBack(View v) {
        quesito_corrente = (((--quesito_corrente % quesiti.size()) + quesiti.size()) % quesiti.size());
        corrente = quesiti.get(quesito_corrente);
        tv_quesito.setText(corrente.getTesto());
        num_quesito.setText("Quesito n° " + ((quesito_corrente % testi.length)+1));
    }

    public void onClickTrue(View v) {
        if (!corrente.isContata()) {
            risp_totali++;
            if (corrente.getRisposta()) {
                if (suggerimento_usato) {
                    risp_non_valide++;
                    suggerimento_usato = false;
                } else {
                    risp_valide++;
                }
                corrente.setContata(true);
            }
            onClickNext(null);
            aggiornaContatori();
        }
    }

    public void onClickFalse(View v) {
        if (!corrente.isContata()) {
            risp_totali++;
            if (!corrente.getRisposta()) {
                if (suggerimento_usato) {
                    risp_non_valide++;
                    suggerimento_usato = false;
                } else {
                    risp_valide++;
                }
                corrente.setContata(true);
            }
            onClickNext(null);
            aggiornaContatori();
        }
    }

    public void onClickSuggerimento(View v) {
        Intent i = new Intent(getApplicationContext(), ActivitySuggerimento.class);
        i.putExtra("TESTO_QUESITO", corrente.getTesto());
        i.putExtra("RISPOSTA", corrente.getRisposta());
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            suggerimento_usato = data.getBooleanExtra("RISPOSTA_MOSTRATA", false);
        }
    }

    private void aggiornaContatori() {
        tv_risp_valide.setText("Risposte corrette valide: " + risp_valide);
        tv_risp_non_valide.setText("Risposte corrette non valide: " + risp_non_valide);
        tv_risp_totali.setText("Risposte totali: " + risp_totali);
    }
}