package com.example.cardaropoligiuseppe;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Contatto {

    private String nome;
    private String cognome;
    private String numero;
    private Drawable img;

    public Contatto(String nome, String cognome, String numero, Drawable img) {
        this.nome = nome;
        this.cognome = cognome;
        this.numero = numero;
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
