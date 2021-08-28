package com.example.a16_dynamicfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FragmentCitazioni extends Fragment {

    TextView tvAutore, tvCitazione;
    String[] autori, citazioni;
    private int current_index;

    public FragmentCitazioni() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_citazioni, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAutore = getActivity().findViewById(R.id.tv_autore);
        tvCitazione = getActivity().findViewById(R.id.tv_citazione);
        autori = getResources().getStringArray(R.array.Autori);
        citazioni = getResources().getStringArray(R.array.Citazioni);
    }

    public void mostraCitazione(int index) {
        System.out.println(index);
        if (index >= 0 && index < autori.length) {
            current_index = index;
            tvAutore.setText(autori[current_index]);
            tvCitazione.setText(citazioni[current_index]);
        }
    }
}
