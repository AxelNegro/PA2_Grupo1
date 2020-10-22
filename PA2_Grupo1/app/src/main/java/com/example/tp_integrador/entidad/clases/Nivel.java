package com.example.tp_integrador.entidad.clases;

public class Nivel {
    private int IdNivel;
    private String Nivel;

    public Nivel() {
    }

    public Nivel(int idNivel, String nivel) {
        IdNivel = idNivel;
        Nivel = nivel;
    }

    public int getIdNivel() {
        return IdNivel;
    }

    public void setIdNivel(int idNivel) {
        IdNivel = idNivel;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    @Override
    public String toString() {
        return "Nivel{" +
                "IdNivel=" + IdNivel +
                ", Nivel='" + Nivel + '\'' +
                '}';
    }
}
