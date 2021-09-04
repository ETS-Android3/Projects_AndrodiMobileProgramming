package com.example.cardaropoligiuseppe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edit_nome, edit_cognome, edit_telefono;
    private ListView list_view_contatti;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_nome = findViewById(R.id.edit_nome);
        edit_cognome = findViewById(R.id.edit_cognome);
        edit_telefono = findViewById(R.id.edit_numero);

        list_view_contatti = findViewById(R.id.list_view_contatti);
        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Contatto>());
        list_view_contatti.setAdapter(customAdapter);

        if (savedInstanceState != null) {
            ArrayList<String> nomi_contatti = savedInstanceState.getStringArrayList("NOMI");
            ArrayList<String> cognomi_contatti = savedInstanceState.getStringArrayList("COGNOMI");
            ArrayList<String> telefoni_contatti = savedInstanceState.getStringArrayList("TELEFONI");

            edit_nome.setText(savedInstanceState.getString("EDIT_NOME"));
            edit_cognome.setText(savedInstanceState.getString("EDIT_COGNOME"));
            edit_telefono.setText(savedInstanceState.getString("EDIT_TELEFONO"));

            for (int i = 0; i < nomi_contatti.size(); i++) {
                customAdapter.add(new Contatto(
                        nomi_contatti.get(i),
                        cognomi_contatti.get(i),
                        telefoni_contatti.get(i),
                        getResources().getDrawable(R.drawable.placeholder)
                ));
            }
            customAdapter.notifyDataSetChanged();
        }

        list_view_contatti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(view);
            }
        });
    }

    public void onClickInserisci(View v) {
        if (!edit_nome.getText().toString().equals("") && !edit_cognome.getText().toString().equals("") && !edit_telefono.getText().toString().equals("")) {
            Contatto contatto = new Contatto(
                    edit_nome.getText().toString(),
                    edit_cognome.getText().toString(),
                    edit_telefono.getText().toString(),
                    getResources().getDrawable(R.drawable.placeholder)
            );
            customAdapter.add(contatto);
            customAdapter.notifyDataSetChanged();
        }
    }

    public void showDialog(View view) {
        DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    Toast.makeText(getApplicationContext(), "Contatto cancellato", Toast.LENGTH_SHORT).show();
                    customAdapter.remove(customAdapter.getItem((Integer) view.getTag()));
                    customAdapter.notifyDataSetChanged();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_SHORT).show();
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per eliminare un contatto. Sei sicuro?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<String> nomi_contatti = new ArrayList<>();
        ArrayList<String> cognomi_contatti = new ArrayList<>();
        ArrayList<String> telefoni_contatti = new ArrayList<>();

        for (int i = 0; i < customAdapter.getCount(); i++) {
            Contatto c = customAdapter.getItem(i);
            nomi_contatti.add(c.getNome());
            cognomi_contatti.add(c.getCognome());
            telefoni_contatti.add(c.getNumero());
        }

        customAdapter.clear();


        outState.putStringArrayList("NOMI", nomi_contatti);
        outState.putStringArrayList("COGNOMI", cognomi_contatti);
        outState.putStringArrayList("TELEFONI", telefoni_contatti);
        outState.putString("EDIT_NOME", edit_nome.getText().toString());
        outState.putString("EDIT_COGNOME", edit_cognome.getText().toString());
        outState.putString("EDIT_TELEFONO", edit_telefono.getText().toString());
    }
}