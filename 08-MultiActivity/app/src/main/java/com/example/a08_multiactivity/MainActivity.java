package com.example.a08_multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.logging.Logger;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFECYCLE", "onCreate() - MainActivity");
    }

    public void lanciaActivity(View v) {
        // Creiamo l'intent per lanciare la prossima activity
        System.out.println(getApplicationContext().getClass().getName());
        Intent i = new Intent(getApplicationContext(), Activity1.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        Log.d("LIFECYCLE", "onStart() - MainActivity");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("LIFECYCLE", "onResume() - MainActivity");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LIFECYCLE", "onPause() - MainActivity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LIFECYCLE", "onStop() - MainActivity");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("LIFECYCLE", "onRestart() - MainActivity");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE", "onDestroy() - MainActivity");
        super.onDestroy();
    }
}