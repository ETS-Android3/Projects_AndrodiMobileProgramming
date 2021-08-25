package com.example.a06_listviewcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] lista = {"Giovanni", "Paolo", "Nicola", "Giuseppe", "Antonio",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo",
            "Pompeo", "Pasquale", "Andrea", "Alessandro", "Gerardo"
    };
    ListView listView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Otteniamo il riferimento al listview
        listView = findViewById(R.id.listview);

        // Instanziamo il custom adapter, passandogli il contesto, il layout di un singolo elemento ed una lista vuota
        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Contatto>());

        // associamo il custom adapter al list view
        listView.setAdapter(customAdapter);

        // Aggiungiamo gli oggetti nel custom adapter
        for (String s : lista) {
            customAdapter.add(new Contatto(s, "089 825770", getResources().getDrawable(R.drawable.faceplaceholder)));
        }

        // creiamo un onitemclicklistener
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = listView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Cliccato: " + str + " posizione: " + (i+1), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void onPictureClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Toast.makeText(getApplicationContext(),
                "Hai cliccato la foto di " + customAdapter.getItem(position).getName() +
                        "in posizione " + (position+1),
                Toast.LENGTH_SHORT).show();
    }

    public void onNameClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Toast.makeText(getApplicationContext(),
                "Hai cliccato il nome di " + customAdapter.getItem(position).getName() +
                        "in posizione " + (position+1),
                Toast.LENGTH_SHORT).show();
    }

    public void onNumberClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Toast.makeText(getApplicationContext(),
                "Hai cliccato il numero di " + customAdapter.getItem(position).getName() +
                        "in posizione " + (position+1),
                Toast.LENGTH_SHORT).show();
    }



}