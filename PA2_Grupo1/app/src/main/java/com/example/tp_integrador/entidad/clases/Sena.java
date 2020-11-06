package com.example.tp_integrador.entidad.clases;

import java.sql.Blob;

public class Sena {

    private int idSena;
    private String imagen;
    private String descripcion;

    public int getIdSena() {
        return idSena;
    }

    public void setIdSena(int idSena) {
        this.idSena = idSena;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sena(int idSena, String imagen, String descripcion) {
        this.idSena = idSena;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Sena() {
    }
}
