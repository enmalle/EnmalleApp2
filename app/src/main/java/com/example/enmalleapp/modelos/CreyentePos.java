package com.example.enmalleapp.modelos;

public class CreyentePos {
    private String idCreyentePos;
    private String nombresPos;
    private String apellidosPos;
    private String edadPos;
    private int imageDeletePos;

    public CreyentePos(String idCreyentePos, String nombresPos, String apellidosPos, String edadPos, int imageDeletePos) {
        this.idCreyentePos = idCreyentePos;
        this.nombresPos = nombresPos;
        this.apellidosPos = apellidosPos;
        this.edadPos = edadPos;
        this.imageDeletePos = imageDeletePos;
    }

    public CreyentePos() {

    }

    public String getIdCreyentePos() {
        return idCreyentePos;
    }

    public void setIdCreyentePos(String idCreyentePos) {
        this.idCreyentePos = idCreyentePos;
    }

    public String getNombresPos() {
        return nombresPos;
    }

    public void setNombresPos(String nombresPos) {
        this.nombresPos = nombresPos;
    }

    public String getApellidosPos() {
        return apellidosPos;
    }

    public void setApellidosPos(String apellidosPos) {
        this.apellidosPos = apellidosPos;
    }

    public String getEdadPos() {
        return edadPos;
    }

    public void setEdadPos(String edadPos) {
        this.edadPos = edadPos;
    }

    public int getImageDeletePos() {
        return imageDeletePos;
    }

    public void setImageDeletePos(int imageDeletePos) {
        this.imageDeletePos = imageDeletePos;
    }
}
