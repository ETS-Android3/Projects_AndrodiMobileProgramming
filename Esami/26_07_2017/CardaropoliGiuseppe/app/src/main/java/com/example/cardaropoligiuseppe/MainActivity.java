package com.example.cardaropoligiuseppe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CheckBox> checkboxes = new ArrayList<>();
    private TextView tv_conto;
    private float conto = 0.0f;
    private int checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_conto = findViewById(R.id.tv_conto);
        tv_conto.setText(conto + "€");

        LinearLayout ll_checkboxes = findViewById(R.id.checkboxes);
        for (int i = 0; i < ll_checkboxes.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) ll_checkboxes.getChildAt(i);
            checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
            checkboxes.add(checkBox);
        }
    }

    private void onCheckedChanged(CompoundButton compoundButton, boolean spuntato) {
        CheckBox checkbox = (CheckBox) compoundButton;
        if (spuntato) {
            checked++;
            float prezzo = Float.parseFloat(checkbox.getTag().toString());
            conto += prezzo;
            tv_conto.setText(String.format("%7.2f€", conto));
        } else {
            checked--;
            float prezzo = Float.parseFloat(checkbox.getTag().toString());
            conto -= prezzo;
            tv_conto.setText(String.format("%7.2f€", conto));
        }
    }

    public void onClickScontrino(View v) {
        //if (checked > 0) {
            String[] nomi = new String[checked];
            float[] prezzi = new float[checked];

            int i = 0;
            for (CheckBox c : checkboxes) {
                if (c.isChecked()) {
                    nomi[i] = c.getText().toString();
                    prezzi[i] = Float.parseFloat(c.getTag().toString());
                    i++;
                }
            }

            Intent intent = new Intent(getApplicationContext(), Scontrino.class);
            intent.putExtra("PREZZI", prezzi);
            intent.putExtra("NOMI", nomi);
            intent.putExtra("CONTO", conto);
            startActivity(intent);
        }

    //}

}