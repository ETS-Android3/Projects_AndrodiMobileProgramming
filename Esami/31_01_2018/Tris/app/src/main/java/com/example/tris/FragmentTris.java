package com.example.tris;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class FragmentTris extends Fragment {

    private String seme;
    private boolean vittora = false;
    private MainActivity padre;
    private Button[][] tabella = new Button[3][3];

    public FragmentTris() { }

    public interface cliccaTabella {
        void onClickTabella(Button b, int i, int j);
    }

    public void setSeme(String seme) {
        this.seme = seme;
    }

    public void modificaCasella(int i, int j, String valore) {
        tabella[i][j].setText(valore);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        padre = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tris_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout ll = view.findViewById(R.id.tabella);

        for (int i = 0; i < ll.getChildCount(); i++) {
            LinearLayout ll_child = (LinearLayout) ll.getChildAt(i);
            for (int j = 0; j < ll_child.getChildCount(); j++) {
                tabella[i][j] = (Button) ll_child.getChildAt(j);
                tabella[i][j].setTag(new int[]{i, j});

                tabella[i][j].setOnClickListener(this::onClickCasella);
            }
        }
    }

    public void onClickCasella(View v) {
        vittora = checkVittoria();
        Button b = (Button) v;
        if (!vittora) {
            if (b.getText().equals("")) {
                int[] posizioni = (int[]) b.getTag();
                padre.onClickTabella(b, posizioni[0], posizioni[1]);
            }
        }
    }

    public boolean checkVittoria() {
        if ((tabella[0][0].getText().equals(seme) && tabella[0][1].getText().equals(seme) && tabella[0][2].getText().equals(seme))          // riga 1
                || (tabella[1][0].getText().equals(seme) && tabella[1][1].getText().equals(seme) && tabella[1][2].getText().equals(seme))   // riga 2
                || (tabella[2][0].getText().equals(seme) && tabella[2][1].getText().equals(seme) && tabella[2][2].getText().equals(seme))   // riga 3
                || (tabella[0][0].getText().equals(seme) && tabella[1][0].getText().equals(seme) && tabella[2][0].getText().equals(seme))   // colonna 1
                || (tabella[0][1].getText().equals(seme) && tabella[1][1].getText().equals(seme) && tabella[2][1].getText().equals(seme))   // colonna 2
                || (tabella[0][2].getText().equals(seme) && tabella[1][2].getText().equals(seme) && tabella[2][2].getText().equals(seme))   // colonna 3
                || (tabella[0][0].getText().equals(seme) && tabella[1][1].getText().equals(seme) && tabella[2][2].getText().equals(seme))   // diagonale 1
                || (tabella[0][2].getText().equals(seme) && tabella[1][1].getText().equals(seme) && tabella[2][0].getText().equals(seme))   // diagonale 1
        ) {
            vittora = true;
            return vittora;
        }
        else {
            vittora = false;
            return vittora;
        }
    }
}
