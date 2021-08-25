package com.example.a1_helloworld;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num_click = 0;
    TextView contatore;
    TextView hello;
    Button button;
    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // indhiciamo il file di layout da utilizzare

        // Otteniamo riferimenti a delle View della UI
        contatore = (TextView) findViewById(R.id.contatore);
        hello = (TextView) findViewById(R.id.helloworld);
        button = (Button) findViewById(R.id.bottone);

        // Assegniamo un OnClickListener al bottone
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Invocata ad ogni click del bottone
                num_click++;
                contatore.setText("Click: " + num_click);
                hello.setTextColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            }
        });
    }
}