package com.example.cardaropoligiuseppe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> bottoni = new ArrayList<>();
    private EditText tv_num_input;
    private int input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout griglia = findViewById(R.id.griglia);
        tv_num_input = findViewById(R.id.edit_num_input);

        for (int i = 0; i < griglia.getChildCount(); i++) {
            LinearLayout riga = (LinearLayout) griglia.getChildAt(i);
            for (int j = 0; j < riga.getChildCount(); j++) {
                Button b = (Button) riga.getChildAt(j);
                b.setOnClickListener(this::onClickGriglia);
                bottoni.add(b);
            }
        }
    }

    private void onClickGriglia(View view) {
        Button b = (Button) view;

        if (!tv_num_input.getText().toString().equals("")) {
            input = Integer.parseInt(tv_num_input.getText().toString());

            if (1 <= input && input <= 9) {
                if (b.getText().toString().equals("")) {
                    b.setText(Integer.toString(input));
                } else {
                    aggiornaGriglia(input);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Devi inserire un numero da 1 a 9!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Devi inserire un numero da 1 a 9!", Toast.LENGTH_LONG).show();
        }
    }

    private void aggiornaGriglia(int input) {
        for (Button b : bottoni) {
            if (!b.getText().toString().equals("")) {
                int x = Integer.parseInt(b.getText().toString());
                x = (x + input) % 10;
                b.setText(Integer.toString(x));
            }
        }
    }

    public void onClickAzzera(View v) {
        for (Button b : bottoni) {
            b.setText("");
        }
    }
}