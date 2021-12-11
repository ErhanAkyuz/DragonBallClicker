package com.example.dragonballclicker;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

//Classe permettant de d'éfinir les amérilorations

public class Upgrade implements Serializable {

    private long DBs;
    private int nbUp;
    private long price;
    private String name;

    private Drawable image;
    public Upgrade(long price, Drawable image, String name, int nbUp, long DBs) {
        this.price = price;
        this.image = image;
        this.name = name;
        this.nbUp = nbUp;
        this.DBs = DBs;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getNbUp() {
        return nbUp;
    }

    public void setNbUp(int nbUp) {
        this.nbUp = nbUp;
    }

    public long getDBs() {
        return DBs;
    }

    public void setDBs(long DBs) {
        this.DBs = DBs;
    }

}
