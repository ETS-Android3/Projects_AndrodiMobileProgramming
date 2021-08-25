package com.example.a06_listviewcustom;

import android.graphics.drawable.Drawable;

public class Contatto {
    private String name;
    private String number;
    private Drawable picture;


    public Contatto(String name, String number, Drawable picture) {
        this.name = name;
        this.number = number;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Contatto{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", picture=" + picture +
                '}';
    }
}
