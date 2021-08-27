package com.example.a13_asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ImageView imgView;
    TextView tvContatore;
    Bitmap bmap;
    ProgressBar progressBar;
    private int contatore = 0;
    private int index = 1;
    private int img_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imgView);
        tvContatore = findViewById(R.id.tvcontatore);
        progressBar = findViewById(R.id.progress_bar);
    }

    public void onClickContatore(View v) {
        contatore++;
        tvContatore.setText(contatore + "");
    }

    public void onClickCaricaImmagine(View v) {
        switch (index) {
            case 1:
                img_id = R.drawable.image1;
                index++;
                break;
            case 2:
                img_id = R.drawable.image2;
                index++;
                break;
            case 3:
                img_id = R.drawable.image3;
                index = 1;
                break;
        }
        new LoadImageTask().execute(img_id);
    }

    public class LoadImageTask extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            // prima di iniziare la task rendiamo invisibile la progress bar
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... img_ids) {
            // Otteniamo la bitmap dell'immagine da caricare
            bmap = BitmapFactory.decodeResource(getResources(), img_ids[0]);

            // simuliamo un ritardo
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ad ogni iterazione notifichiamo i progessi di un 10%
                publishProgress(i * 10);
            }

            return bmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //In risposta agli aggironamenti rendiamo visibile la progress bar
            // ed aggiorniamo i progressi
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressBar.setProgress(values[0]);

            if (values[0] == 40)
                Toast.makeText(getApplicationContext(), "Siamo a metà", Toast.LENGTH_SHORT).show();
            else if (values[0] == 70)
                Toast.makeText(getApplicationContext(), "Abbiamo quasi finito", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // al termine della task aggiorniamo l'immagine e rendiamo invisibile la progresbar
            imgView.setImageBitmap(bitmap);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }
}