package com.example.cardaropoligiuseppe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contatto> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element, null);
        }

        Contatto contatto = getItem(position);

        TextView tv_name_cognome = v.findViewById(R.id.tv_nome_cognome);
        TextView tv_telefono = v.findViewById(R.id.tv_telefono);
        ImageView image = v.findViewById(R.id.img_contact);

        tv_name_cognome.setText(contatto.getNome() + " " + contatto.getCognome());
        tv_telefono.setText(contatto.getNumero());
        image.setImageDrawable(contatto.getImg());

        v.setTag(position);

        return v;
    }
}
