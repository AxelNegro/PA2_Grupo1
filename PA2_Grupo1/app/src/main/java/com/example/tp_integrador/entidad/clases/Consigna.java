package com.example.tp_integrador.entidad.clases;

public class Consigna {
    private Nivel nivel;
    private int idConsigna;
    private String desc;
    private boolean estado;

    public Consigna() {
    }

    public Consigna(Nivel nivel, int idConsigna, String desc, boolean estado) {
        this.nivel = nivel;
        this.idConsigna = idConsigna;
        this.desc = desc;
        this.estado = estado;
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

    @Override
    public String toString() {
        return "Consigna{" +
                "nivel=" + nivel.toString() +
                ", idConsigna=" + idConsigna +
                ", desc='" + desc + '\'' +
                ", estado=" + estado +
                '}';
    }
}
