package com.example.cardaropoligiuseppe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Prodotto> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Prodotto> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element, null);
        }

        Prodotto p = getItem(position);

        ImageView img = v.findViewById(R.id.img_prodotto);
        TextView tv_nome = v.findViewById(R.id.nome_prodotto);
        TextView tv_quantità = v.findViewById(R.id.quantità_prodotto);

        img.setImageDrawable(p.getImg());
        tv_nome.setText(p.getNome());
        tv_quantità.setText(Integer.toString(p.getQuantita()));

        return v;
    }
}
