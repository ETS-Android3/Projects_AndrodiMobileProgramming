package com.example.a06_listviewcustom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contatto> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override //Questo è il metodo invocato per la creazione della view di un elemento
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG", "Inflaiting view");
            v = inflater.inflate(R.layout.list_element, null); //inflate del layout
        }

        // otteniamo il generico contatto
        Contatto c = getItem(position);
        Log.d("DEBUG", "contact: " + c);

        // otteniamo i rifierimenti alle view del layout del contatto
        TextView tv_name = (TextView) v.findViewById(R.id.c_name);
        TextView tv_number = (TextView) v.findViewById(R.id.c_number);
        ImageView picture = (ImageView) v.findViewById(R.id.c_picture);

        // inseriamo i dati
        tv_name.setText(c.getName());
        tv_number.setText(c.getNumber());
        picture.setImageDrawable(c.getPicture());

        // salviamo la posizione
        tv_name.setTag(position);
        tv_number.setTag(position);
        picture.setTag(position);

        // restituiamo la view
        return v;
    }
}
