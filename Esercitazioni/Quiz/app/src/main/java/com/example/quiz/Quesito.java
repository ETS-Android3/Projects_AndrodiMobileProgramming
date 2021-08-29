package com.example.quiz;

public class Quesito {

    private String testo;
    private boolean risposta;
    private boolean contata;

    public Quesito(String testo, boolean risposta) {
        this.testo = testo;
        this.risposta = risposta;
        this.contata = false;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public boolean getRisposta() {
        return risposta;
    }

    public void setRisposta(boolean risposta) {
        this.risposta = risposta;
    }

    public boolean isContata() {
        return contata;
    }

    public void setContata(boolean contata) {
        this.contata = contata;
    }
}
