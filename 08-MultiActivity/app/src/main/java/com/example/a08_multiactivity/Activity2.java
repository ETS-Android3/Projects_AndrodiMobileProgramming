package com.example.a08_multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.d("LIFECYCLE", "onCreate() - Activity2");
    }

    public void lanciaActivity(View v) {
        // Creiamo l'intent per lanciare la prossima activity
        System.out.println(getApplicationContext().getClass().getName());
        Intent i = new Intent(getApplicationContext(), Activity3.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        Log.d("LIFECYCLE", "onStart() - Activity2");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("LIFECYCLE", "onResume() - Activity2");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LIFECYCLE", "onPause() - Activity2");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LIFECYCLE", "onStop() - Activity2");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("LIFECYCLE", "onRestart() - Activity2");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE", "onDestroy() - Activity2");
        super.onDestroy();
    }
}