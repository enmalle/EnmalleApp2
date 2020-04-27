package com.example.enmalleapp.modelos;

public class CreyentePre {
    private String idCreyentePre;
    private String nombresPre;
    private String apellidosPre;
    private String edadPre;
    private int imageDeletePre;

    public CreyentePre(String idCreyentePre, String nombresPre, String apellidosPre, String edadPre, int imageDeletePre) {
        this.idCreyentePre = idCreyentePre;
        this.nombresPre = nombresPre;
        this.apellidosPre = apellidosPre;
        this.edadPre = edadPre;
        this.imageDeletePre = imageDeletePre;
    }

    public CreyentePre() {

    }

    public String getIdCreyentePre() {
        return idCreyentePre;
    }

    public void setIdCreyentePre(String idCreyentePre) {
        this.idCreyentePre = idCreyentePre;
    }

    public String getNombresPre() {
        return nombresPre;
    }

    public void setNombresPre(String nombresPre) {
        this.nombresPre = nombresPre;
    }

    public String getApellidosPre() {
        return apellidosPre;
    }

    public void setApellidosPre(String apellidosPre) {
        this.apellidosPre = apellidosPre;
    }

    public String getEdadPre() {
        return edadPre;
    }

    public void setEdadPre(String edadPre) {
        this.edadPre = edadPre;
    }

    public int getImageDeletePre() {
        return imageDeletePre;
    }

    public void setImageDeletePre(int imageDeletePre) {
        this.imageDeletePre = imageDeletePre;
    }
}
