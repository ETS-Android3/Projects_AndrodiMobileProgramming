package com.example.a12_threadsi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.CyclicBarrier;

public class MainActivity extends Activity {

    ImageView imgView;
    TextView tvContatore;
    Bitmap bmap;
    ProgressBar progressBar;
    private int contatore = 0;
    private int index = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imgView);
        tvContatore = findViewById(R.id.tvcontatore);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void onClickCaricaImmagine(View v) {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        // Instanziamo un Thread che si occupera del caricamento dell'immagine
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simuliamo un caricamento di 5 secondi
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                switch (index) {
                    case 1:
                        bmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
                        index++;
                        break;
                    case 2:
                        bmap = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
                        index++;
                        break;
                    case 3:
                        bmap = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
                        index = 1;
                        break;
                }
                // Poichè solo il mainThread può aggiornare l'interfaccia
                // Utilizziamo il metodo post di View per creare un Threa che può aggiornare la UI
                imgView.post(new Runnable() {
                    @Override
                    public void run() {
                        imgView.setImageBitmap(bmap);
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                    }
                });
            }
        }).start();

    }
    
    public void onClickContatore(View v) {
        contatore++;
        tvContatore.setText(contatore + "");
    }
}