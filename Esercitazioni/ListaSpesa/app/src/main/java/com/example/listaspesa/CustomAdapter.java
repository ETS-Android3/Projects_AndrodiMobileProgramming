package com.example.listaspesa;

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
        if(v == null) {
            v = inflater.inflate(R.layout.list_element, null);
        }

        Prodotto p = getItem(position);

        ImageView imageView = v.findViewById(R.id.p_img);
        TextView tv_nome = v.findViewById(R.id.p_name);
        TextView tv_description = v.findViewById(R.id.p_description);
        TextView tv_price = v.findViewById(R.id.p_price);

        imageView.setImageDrawable(p.getImmagine());
        tv_nome.setText(p.getNome());
        tv_description.setText(p.getDescrizione());
        tv_price.setText(Integer.toString(p.getPrezzo()) + "€");

        imageView.setTag(position);
        tv_nome.setTag(position);
        tv_price.setTag(position);
        tv_description.setTag(position);

        return v;
    }
}
