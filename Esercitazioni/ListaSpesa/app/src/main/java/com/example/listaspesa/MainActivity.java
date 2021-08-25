package com.example.listaspesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] nomi = {"telefono", "lavatrice", "televiore", "tablet", "computer", "frigorifero",
            "aspirapolvere", "lavastoviglie", "asciugatrice", "cuffie", "mouse", "orologio"
    };
    Random random;
    CustomAdapter customAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();

        listView = findViewById(R.id.lista_prodotti);

        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<>());

        listView.setAdapter(customAdapter);

        for (String s : nomi) {
            customAdapter.add(new Prodotto(
                    s, "lorem ipsum erat a maronn",
                    random.nextInt(100),
                    getResources().getDrawable(R.drawable.placegolder)
            ));
        }
    }

    public void onClickPicture(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Prodotto p = customAdapter.getItem(position);
        Toast.makeText(getApplicationContext(),
                "Hai cliccato l'immagine di " + p.getNome(),
                Toast.LENGTH_SHORT).show();
    }

    public void onClickName(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Prodotto p = customAdapter.getItem(position);
        Toast.makeText(getApplicationContext(),
                "Questo è un " + p.getNome(),
                Toast.LENGTH_SHORT).show();
    }

    public void onClickDescription (View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Prodotto p = customAdapter.getItem(position);
        Toast.makeText(getApplicationContext(),
                p.getDescrizione() + " " + (position+1),
                Toast.LENGTH_SHORT).show();
    }

    public void onClickPrice(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Prodotto p = customAdapter.getItem(position);
        Toast.makeText(getApplicationContext(),
                "Questo/a " + p.getNome() + " costa " + p.getPrezzo() + "€",
                Toast.LENGTH_SHORT).show();
    }
}