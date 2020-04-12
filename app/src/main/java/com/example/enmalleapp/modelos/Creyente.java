package com.example.enmalleapp.modelos;

import android.widget.ImageView;

public class Creyente {
    private String idCreyente;
    private String nombreApellido;
    private String fechaNacimiento;
    private int imageDelete;



    public Creyente(String idCreyente, String nombreApellido, String fecha, int imageDelete) {
        this.idCreyente = idCreyente;
        this.nombreApellido = nombreApellido;
        this.fechaNacimiento = fecha;
        this.imageDelete = imageDelete;
    }

    public Creyente() {
    }

    public String getidCreyente() {
        return idCreyente;
    }

    public void setidCreyente(String idCreyente) {this.idCreyente = idCreyente;}

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}


    public int getImageDelete() {
        return imageDelete;
    }

    public void setImageDelete(int imageDelete) {
        this.imageDelete = imageDelete;
    }
}
