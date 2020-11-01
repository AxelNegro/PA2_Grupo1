package com.example.tp_integrador.entidad.clases;

import java.net.URL;

public class Consigna {
    private Nivel nivel;
    private int idConsigna;
    private String URLImagen;
    private String desc;
    private boolean estado;

    public Consigna() {
    }

    public Consigna(Nivel nivel, int idConsigna, String URLImagen, String desc, boolean estado) {
        this.nivel = nivel;
        this.idConsigna = idConsigna;
        this.desc = desc;
        this.estado = estado;
        this.URLImagen = URLImagen;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public int getIdConsigna() {
        return idConsigna;
    }

    public void setIdConsigna(int idConsigna) {
        this.idConsigna = idConsigna;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getURLImagen() {
        return URLImagen;
    }

    public void setURLImagen(String URLImagen) {
        this.URLImagen = URLImagen;
    }

    @Override
    public String toString() {
        return "Consigna{" +
                "nivel=" + nivel.getNivel() +
                ", idConsigna=" + idConsigna +
                ", URLImagen='" + URLImagen + '\'' +
                ", desc='" + desc + '\'' +
                ", estado=" + estado +
                '}';
    }
}
