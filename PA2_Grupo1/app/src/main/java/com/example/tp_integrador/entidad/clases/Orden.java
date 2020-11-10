package com.example.tp_integrador.entidad.clases;

public class Orden {
    private int idOrden;
    private Nivel nivel;
    private Sena sena;
    private Consigna consigna;
    private int orden;
    private boolean estado;

    public Orden() {
    }

    public Orden(int idOrden, Nivel nivel, Sena sena, Consigna consigna, int orden, boolean estado) {
        this.idOrden = idOrden;
        this.nivel = nivel;
        this.sena = sena;
        this.consigna = consigna;
        this.orden = orden;
        this.estado = estado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Sena getSena() {
        return sena;
    }

    public void setSena(Sena sena) {
        this.sena = sena;
    }

    public Consigna getConsigna() {
        return consigna;
    }

    public void setConsigna(Consigna consigna) {
        this.consigna = consigna;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "idOrden=" + idOrden +
                ", nivel=" + nivel.toString() +
                ", sena=" + sena.toString() +
                ", consigna=" + consigna.toString() +
                ", orden=" + orden +
                ", estado=" + estado +
                '}';
    }
}
