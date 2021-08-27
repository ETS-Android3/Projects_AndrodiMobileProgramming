package com.example.a11_threadno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageView imgView;
    TextView tvContatore;
    Bitmap bmap;
    private int contatore = 0;
    private int index = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imgView);
        tvContatore = findViewById(R.id.tvcontatore);
    }

    public void onClickCaricaImmagine(View v) {
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
        imgView.setImageBitmap(bmap);
    }

    // questa funzione non potrà essere invocata durante il finto caricamento dell'immagine
    // poichè abbiamo un solo thread.
    public void onClickContatore(View v) {
        contatore++;
        tvContatore.setText(contatore + "");
    }
}