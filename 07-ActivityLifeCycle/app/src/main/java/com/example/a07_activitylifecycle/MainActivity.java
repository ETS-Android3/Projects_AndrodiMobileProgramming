package com.example.a07_activitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView;
    TextView tv_contatore;
    EditText editText;
    String str = "";
    int counter = 0;
    ArrayList<String> array = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Controlliamo se il bundle contiene qualche dato salvato
        if (savedInstanceState != null) {
            // preleviamo ciò che abbiamo salvato nel Bundle
            str = savedInstanceState.getString("INPUT");
            counter = savedInstanceState.getInt("COUNTER");
            array = savedInstanceState.getStringArrayList("LOGLIST");
        }

        // Otteniamo i riferimenti ai widget
        listView = findViewById(R.id.list_log);
        editText = findViewById(R.id.editext);
        tv_contatore = findViewById(R.id.tv_contatore);

        // Settiamo il contenuto dell'editext e del textview ai valori dell'ultima esecuzione
        editText.setText(str);
        tv_contatore.setText("Counter: " + counter);

        // creiamo un arrayadapter per il listview di log
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, // layout di un list element fornito da android
                android.R.id.text1, // il corrispettivo id
                array
        );

        //settiamo l'adapter
        listView.setAdapter(arrayAdapter);

        addString("onCreate()");
    }

    // Questo metodo verrà invocato da ogni metodo del ciclo di vita di un activity
    private void addString(String s) {
        long time = SystemClock.elapsedRealtime();
        int secs = (int)time/1000;
        int ms = (int)(time - secs*1000);
        int mm = secs/60;
        secs = secs -(mm*60);
        int hh = (int)mm/60;
        mm = mm - hh*60;
        int gg = (int)hh/24;
        hh = hh - gg*24;
        array.add(gg+"gg:"+hh+"hh:"+mm+"mm:"+secs+"ss:"+ms+" - "+counter+": "+s);
        counter++;
        tv_contatore.setText("Counter: "+counter);
        arrayAdapter.notifyDataSetChanged(); // bisogna notificare l'adapter che è avvenuto un cambiamento
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // prima che l'app venga distrutta salviamo i dati desiderati nel Bundle
        outState.putInt("COUNTER", counter);
        outState.putString("INPUT", editText.getText().toString());
        outState.putStringArrayList("LOGLIST", array);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addString("onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        addString("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addString("onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        addString("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        addString("onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addString("onRestart()");
    }

    public void onAzzeraButtonClick(View v) {
        array.clear();
        counter = 0;
        tv_contatore.setText("Counter: " + counter);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onDestroyButtonClick(View v) {
        finish(); //chiudiamo l'app
    }
}