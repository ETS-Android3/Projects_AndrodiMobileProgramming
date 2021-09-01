package com.example.a27_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private TextView textVolume, textBrano;
    private RadioGroup radioGroup;
    private int volume = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radio_group);
        textVolume = findViewById(R.id.tv_volume);
        textBrano = findViewById(R.id.tv_nome_brano);

        // Otteniamo l'audio manager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Otteniamo l'attuale volume
        volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        textVolume.setText(Integer.toString(volume));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_brano1:
                        textBrano.setText("Gallo Ciego");
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gallo_ciego);
                        break;
                    case R.id.radio_brano2:
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.milonga_de_mis_amores);
                        textBrano.setText("Milonga de mis amores");
                        break;
                }
            }
        });
    }

    public void buttonDecreaseVolume(View v) {
        if (volume > 0) {
            volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            volume--;
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
            textVolume.setText("" + volume);
        }
    }

    public void buttonIncreaseVolume(View v) {
        volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume++;
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        textVolume.setText(""+volume);
    }

    public void buttonPlay(View v) {
        if (mediaPlayer == null) {
            Toast.makeText(getApplicationContext(), "Nessun brano selezionato", Toast.LENGTH_SHORT).show();
        } else {
            mediaPlayer.start();
        }
    }

    public void buttonStop(View v) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            textBrano.setText("Nessun brano selezionato");
        }
    }

    public void buttonPause(View v) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
}