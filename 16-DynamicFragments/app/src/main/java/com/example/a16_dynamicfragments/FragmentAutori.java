package com.example.a16_dynamicfragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class FragmentAutori extends ListFragment {

    int current_selection = -1;
    AuthorSelectionListener listener;
    String[] autori;

    // interfaccia utilizzata per comunicare con la mainactivity
    public interface AuthorSelectionListener {
        void onAuthorSelection(int index);
    }

    public FragmentAutori() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // preleviamo la lista di autori
        autori = getResources().getStringArray(R.array.Autori);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // sfruttiamo il metodo onAttach per ottenere il riferimento all'activity ospitante
        listener = (AuthorSelectionListener) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // quando l'activity viene creata settiamo il listadapter
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, autori));
        if (current_selection != -1)
            setSelection(current_selection);
    }

    @Override // questo metodo verrà invocato quando verrà cliccato un item della lista
    public void onListItemClick(ListView l, View v, int position, long id) {
        current_selection = position;   // salviamo la posizione
        getListView().setItemChecked(current_selection, true);
        // invochiamo il metodo della main activity che si occupera di comunicare con l'altro frammento
        listener.onAuthorSelection(current_selection);
    }
}
