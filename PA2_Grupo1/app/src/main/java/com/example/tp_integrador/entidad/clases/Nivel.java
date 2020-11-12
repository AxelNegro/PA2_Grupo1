package com.example.tp_integrador.entidad.clases;

public class Nivel {
    private int idNivel;
    private String nivel;
    private boolean Estado;

    public Nivel() {
    }

    public Nivel(int idNivel, String nivel, boolean Estado) {
        this.idNivel = idNivel;
        this.nivel = nivel;
        this.Estado = Estado;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "Nivel{" +
                "idNivel=" + idNivel +
                ", nivel='" + nivel + '\'' +
                ", Estado=" + Estado +
                '}';
    }
}
