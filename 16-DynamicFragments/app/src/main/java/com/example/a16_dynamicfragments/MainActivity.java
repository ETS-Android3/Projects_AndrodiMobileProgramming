package com.example.a16_dynamicfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public class MainActivity extends Activity implements FragmentAutori.AuthorSelectionListener {

    FragmentAutori fragmentAutori;
    FragmentCitazioni fragmentCitazioni;
    FrameLayout frameContainer, frameAutori, frameCitazioni;
    FragmentManager fm;
    FragmentTransaction ft;
    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orientation = this.getResources().getConfiguration().orientation;

        fm = getFragmentManager();

        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.main_portrait);
                frameContainer = findViewById(R.id.frame_container);

                fragmentAutori = new FragmentAutori();

                ft = fm.beginTransaction();
                ft.replace(frameContainer.getId(), fragmentAutori);
                ft.commit();

                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.main_landscape);
                frameAutori = findViewById(R.id.frame_autori);
                frameCitazioni = findViewById(R.id.frame_citazioni);

                fragmentAutori = new FragmentAutori();
                fragmentCitazioni = new FragmentCitazioni();

                ft = fm.beginTransaction();
                ft.replace(frameAutori.getId(), fragmentAutori);
                ft.replace(frameCitazioni.getId(), fragmentCitazioni);
                ft.commit();

                break;
        }
    }

    @Override
    public void onAuthorSelection(int index) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentCitazioni = new FragmentCitazioni();

            ft = fm.beginTransaction();
            ft.replace(frameContainer.getId(), fragmentCitazioni);
            ft.addToBackStack(null);
            ft.commit();
            fm.executePendingTransactions();
        }

        fragmentCitazioni.mostraCitazione(index);
    }
}