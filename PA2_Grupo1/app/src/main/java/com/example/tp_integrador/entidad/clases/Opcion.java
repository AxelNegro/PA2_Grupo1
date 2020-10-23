package com.example.tp_integrador.entidad.clases;

public class Opcion {
    private Consigna consigna;
    private int idOpcion;
    private String desc;
    private boolean res;
    private boolean estado;

    public Opcion() {
    }

    public Opcion(Consigna consigna, int idOpcion, String desc, boolean res, boolean estado) {
        this.consigna = consigna;
        this.idOpcion = idOpcion;
        this.desc = desc;
        this.res = res;
        this.estado = estado;
    }

    public Consigna getConsigna() {
        return consigna;
    }

    public void setConsigna(Consigna consigna) {
        this.consigna = consigna;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Opcion{" +
                "consigna=" + consigna.toString() +
                ", idOpcion=" + idOpcion +
                ", desc='" + desc + '\'' +
                ", res=" + res +
                ", estado=" + estado +
                '}';
    }
}
