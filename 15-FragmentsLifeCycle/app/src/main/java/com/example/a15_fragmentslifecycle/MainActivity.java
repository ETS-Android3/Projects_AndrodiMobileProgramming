package com.example.a15_fragmentslifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    FragmentManager fm;
    FragmentTransaction ft;
    FrameLayout frame;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager(); // otteniamo il riferimento al fragment managar
        frame = findViewById(R.id.frame_layout);
    }

    public void onClickInserisciA(View v) {
        if (fm.findFragmentByTag("tagFrammentoA") == null) {
            ft = fm.beginTransaction(); // iniziamo una nuova tranazione per
            // aggiungere
            ft.add(
                    R.id.frame_layout,  // in questo contenitore
                    new FragmentA(),    // questo frammento
                    "tagFrammentoA"  // e gli assegniamo questo tag
            );
            ft.commit(); // effettuiamo il commit della transazione
        }
    }

    public void onClickRimuoviA(View v) {
        fragment = fm.findFragmentByTag("tagFrammentoA");
        if (fragment != null) {
            ft = fm.beginTransaction(); // iniziamo una nuova transazione per
            ft.remove(fragment);        // rimuovere il frammento passato in input
            ft.commit();
        }
    }

    // I seguenti due listener servono per inserire e rimuovere il frammento B
    public void onClickInserisciB(View v) {
        if (fm.findFragmentByTag("tagFrammentoB") == null) {
            ft = fm.beginTransaction();
            ft.add(R.id.frame_layout, new FragmentB(), "tagFrammentoB");
            ft.commit();
        }
    }

    public void onClickRimuoviB(View v) {
        fragment = fm.findFragmentByTag("tagFrammentoB");
        if (fragment != null) {
            ft = fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
    }

    public void onClickSostituisciConA(View v) {
        ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, new FragmentA(), "tagFrammentoA");
        ft.commit();
    }

    public void onClickSostituisciConB(View v) {
        ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, new FragmentB(), "tagFrammentoB");
        ft.commit();
    }

    public void onClickAttachA(View v) {
        fragment = fm.findFragmentByTag("tagFrammentoA");
        if (fragment != null) {
            ft = fm.beginTransaction();
            ft.attach(fragment);
            ft.commit();
        }
    }


    public void onClickDetachA(View v) {
        fragment = fm.findFragmentByTag("tagFrammentoA");
        if (fragment != null) {
            ft = fm.beginTransaction();
            ft.detach(fragment);
            ft.commit();
        }
    }


}