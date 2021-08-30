package com.example.a17_dynamicmultifragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private final int MAX_CARTELLE = 12;

    private LinearLayout ll_cartelle;
    private EditText edit_num_cartelle;
    private FragmentTransaction ft;
    private int num_cartelle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_cartelle = findViewById(R.id.list_cartelle);
        edit_num_cartelle = findViewById(R.id.edit_num_cartelle);
    }

    public void onClickInviaNumCartelle(View v) {
        num_cartelle = Integer.parseInt(edit_num_cartelle.getText().toString());

        num_cartelle = Math.min(num_cartelle, MAX_CARTELLE);

        for (int i = 1; i <= num_cartelle; i++) {
            FrameLayout frame = new FrameLayout(this);
            frame.setId(i);
            ll_cartelle.addView(frame);

            ft = getFragmentManager().beginTransaction();
            ft.add(i, new Cartella(), null);
            ft.commit();
        }
    }
}