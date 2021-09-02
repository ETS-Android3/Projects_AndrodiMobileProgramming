package com.example.tris;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MainActivity extends Activity implements FragmentTris.cliccaTabella {

    private FragmentTris tabella_x, tabella_o;
    private String turno = "X";
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabella_x = new FragmentTris();
        tabella_o = new FragmentTris();
        tabella_x.setSeme("X");
        tabella_o.setSeme("O");

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.tabella_X, tabella_x, "TABELLA_X");
        ft.add(R.id.tabella_O, tabella_o, "TABELLA_O");
        ft.commit();
    }


    @Override
    public void onClickTabella(Button b, int i, int j) {
        if (turno.equals("X")) {
            b.setText("X");

            tabella_o.modificaCasella(i, j, "X");
            tabella_x.modificaCasella(i, j, "X");

            if (tabella_x.checkVittoria()) {
                showDialog();
            }

            turno = "O";

        } else if (turno.equals("O")) {
            b.setText("O");

            tabella_x.modificaCasella(i, j, "O");
            tabella_o.modificaCasella(i, j, "O");

            if (tabella_o.checkVittoria()) {
                showDialog();
            }

            turno = "X";
        }
    }

    public void showDialog() {
        DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    Toast.makeText(getApplicationContext(), "Nuova Partita!", Toast.LENGTH_SHORT).show();
                    turno = "X";
                    tabella_x = new FragmentTris();
                    tabella_o = new FragmentTris();
                    tabella_x.setSeme("X");
                    tabella_o.setSeme("O");

                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.add(R.id.tabella_X, tabella_x, "TABELLA_X");
                    ft.add(R.id.tabella_O, tabella_o, "TABELLA_O");
                    ft.commit();

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_SHORT).show();
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Vuoi giocare una nuova partita?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void nuovaPartita(View v) {
        showDialog();
    }
}