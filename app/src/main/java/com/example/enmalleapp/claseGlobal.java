package com.example.enmalleapp;

import android.app.Application;

public class claseGlobal extends Application {

    private String idLider;


    //Get y Set
    public String getIdLider() {
        return idLider;
    }

    public void setIdLider(String idLider) {
        this.idLider = idLider;
    }
}
