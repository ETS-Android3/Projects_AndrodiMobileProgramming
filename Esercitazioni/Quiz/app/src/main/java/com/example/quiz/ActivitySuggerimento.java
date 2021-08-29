package com.example.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ActivitySuggerimento extends Activity {

    private TextView tv_quesito, tv_suggerimento;
    private Intent i;
    private boolean usato = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggerimento);

        tv_quesito = findViewById(R.id.tv_quesito);
        tv_suggerimento = findViewById(R.id.tv_suggerimento);

        i = getIntent();
        tv_quesito.setText(i.getStringExtra("TESTO_QUESITO"));
    }

    public void onClickRitorna(View v) {
        onBackPressed();
    }

    public void onClickMostraSuggerimento(View v) {
        tv_suggerimento.setText("La risposta è " + i.getBooleanExtra("RISPOSTA", false));
        usato = true;
        setReturnIntent();
    }

    private void setReturnIntent() {
        Intent data = new Intent();
        data.putExtra("RISPOSTA_MOSTRATA", usato);
        setResult(RESULT_OK, data);
    }
}
