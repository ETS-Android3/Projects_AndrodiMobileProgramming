package com.example.a08_multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Log.d("LIFECYCLE", "onCreate() - Activity1");
    }

    public void lanciaActivity(View v) {
        // Creiamo l'intent per lanciare la prossima activity
        System.out.println(getApplicationContext().getClass().getName());
        Intent i = new Intent(getApplicationContext(), Activity2.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        Log.d("LIFECYCLE", "onStart() - Activity1");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("LIFECYCLE", "onResume() - Activity1");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LIFECYCLE", "onPause() - Activity1");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LIFECYCLE", "onStop() - Activity1");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("LIFECYCLE", "onRestart() - Activity1");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE", "onDestroy() - Activity1");
        super.onDestroy();
    }
}