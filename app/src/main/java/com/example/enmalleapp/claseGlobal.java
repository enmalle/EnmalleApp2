package com.example.enmalleapp;

import android.app.Application;

public class claseGlobal extends Application {

    private String idLider;
    private String idCelula;
    private String variableControl;


    //Get y Set
    public String getIdLider() {
        return idLider;
    }

    public void setIdLider(String idLider) {
        this.idLider = idLider;
    }

    public String getIdCelula() {return idCelula;}

    public void setIdCelula(String idCelula){this.idCelula = idCelula;}

    public String getVariableControl() {return variableControl;}

    public void setVariableControl(String variableControl) {this.variableControl = variableControl;}
}
