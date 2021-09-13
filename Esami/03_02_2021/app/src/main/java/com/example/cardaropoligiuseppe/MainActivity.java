package com.example.cardaropoligiuseppe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup scelta_prodotto;
    private EditText edit_quantità;
    private ListView lista_elementi;
    private CustomAdapter customAdapter;
    private ArrayList<Prodotto> prodotti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scelta_prodotto = findViewById(R.id.tipo_prodotto);
        edit_quantità = findViewById(R.id.edit_quantità);
        lista_elementi = findViewById(R.id.list_prodotti);

        customAdapter = new CustomAdapter(getApplicationContext(), R.layout.list_element, prodotti);
        lista_elementi.setAdapter(customAdapter);

        lista_elementi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Prodotto p = customAdapter.getItem(i);
                dialogCancellazione(p);
            }
        });
    }

    public void onClickInserisci(View v) {
        if (!edit_quantità.getText().toString().equals("") && !edit_quantità.getText().toString().equals("0")) {
            Prodotto daInserire = new Prodotto(null, Integer.parseInt(edit_quantità.getText().toString()), null);
            switch (scelta_prodotto.getCheckedRadioButtonId()) {
                case R.id.radio_computer:
                    daInserire.setTipo(Tipo.COMPUTER);
                    daInserire.setImg(getResources().getDrawable(R.drawable.computer));
                    break;
                case R.id.radio_martelli:
                    daInserire.setTipo(Tipo.MARTELLI);
                    daInserire.setImg(getResources().getDrawable(R.drawable.martello));
                    break;
                case R.id.radio_orologi:
                    daInserire.setTipo(Tipo.OROLOGI);
                    daInserire.setImg(getResources().getDrawable(R.drawable.orologio));
                    break;
                case R.id.radio_scarpe:
                    daInserire.setTipo(Tipo.SCARPE);
                    daInserire.setImg(getResources().getDrawable(R.drawable.scarpe));
                    break;
            }
            customAdapter.add(daInserire);
            customAdapter.notifyDataSetChanged();
        }
    }

    private void dialogCancellazione(Prodotto p) {
        DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    Toast.makeText(getApplicationContext(), "Hai cancellato " + p.getQuantita() + " " + p.getNome(), Toast.LENGTH_SHORT).show();
                    customAdapter.remove(p);
                    customAdapter.notifyDataSetChanged();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_SHORT).show();
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per eliminare un prodotto. Sei sicuro?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}