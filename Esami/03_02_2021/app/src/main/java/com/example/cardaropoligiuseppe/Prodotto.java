package com.example.cardaropoligiuseppe;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.Locale;

public class Prodotto {

    private Tipo tipo;
    private String nome;
    private int quantita;
    private Drawable img;

    public Prodotto(Tipo tipo, int quantita, Drawable img) {
        this.tipo = tipo;
        this.quantita = quantita;
        this.img = img;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
        this.nome = tipo.toString().substring(0,1) + tipo.toString().substring(1).toLowerCase(Locale.ROOT);
    }

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
