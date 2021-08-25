package com.example.listaspesa;

import android.graphics.drawable.Drawable;

public class Prodotto {

    private String nome;
    private String descrizione;
    private int prezzo;
    private Drawable immagine;

    public Prodotto(String nome, String descrizione, int prezzo, Drawable immagine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public Drawable getImmagine() {
        return immagine;
    }

    public void setImmagine(Drawable immagine) {
        this.immagine = immagine;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }
}
