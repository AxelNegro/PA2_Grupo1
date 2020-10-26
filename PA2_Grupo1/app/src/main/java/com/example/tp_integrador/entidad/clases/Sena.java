package com.example.tp_integrador.entidad.clases;

import java.sql.Blob;

public class Sena {

    private int idSena;
    private Blob imagen;
    private String descripcion;

    public int getIdSena() {
        return idSena;
    }

    public void setIdSena(int idSena) {
        this.idSena = idSena;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sena(int idSena, Blob imagen, String descripcion) {
        this.idSena = idSena;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Sena() {
    }
}
