package com.example.a05_listviewsemplice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] lista = {"Giovanni", "Paolo", "Nicola", "Giuseppe", "Antonio",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo"
    };
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ottengo il riferimento al listview
        listView = (ListView) findViewById(R.id.listview);

        // Creo ArrayAdapter passandoghli il
        // contesto (this),
        // il layout di un elemento (R.layout.list_element),
        // l'id della view in cui inserire un singolo elemento (R.id.tv_element) e
        // la lista di elementi (lista)
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_element, R.id.tv_element, lista);

        // Associamo l'Adapter appena creato al listview
        listView.setAdapter(arrayAdapter);

        // Associamo anchje un onTimeClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Ogni volta che clicchiamo un elemento, cambiamo il colore e stampiamo nome e posizione cliccata
                LinearLayout ll = (LinearLayout) view;
                TextView t = (TextView) ll.getChildAt(0);
                t.setTextColor(Color.rgb(255, 0, 0));
                String cliccato = listView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),
                        "Hai cliccato: " + cliccato + " " + (i+1)  + "°", Toast.LENGTH_SHORT).show();
            }
        });
    }
}