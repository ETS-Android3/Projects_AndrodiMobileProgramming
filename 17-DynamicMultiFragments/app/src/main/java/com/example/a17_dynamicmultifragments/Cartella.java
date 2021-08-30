package com.example.a17_dynamicmultifragments;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.Random;

public class Cartella extends Fragment implements View.OnClickListener {

    private LinearLayout ll;
    private Random random = new Random();

    public Cartella() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cartella_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll = view.findViewById(R.id.root_cartella);

        for (int i = 0; i < ll.getChildCount(); i++) {
            LinearLayout child = (LinearLayout) ll.getChildAt(i);
            for (int j = 0; j < child.getChildCount(); j++) {
                Button b = (Button) child.getChildAt(j);
                b.setText((random.nextInt(89) + 1 ) + "");
                b.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        if (color == getResources().getColor(R.color.cartella_normale)) {
            view.setBackgroundColor(getResources().getColor(R.color.cartella_segnata));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.cartella_normale));
        }
    }
}
