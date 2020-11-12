package com.example.tp_integrador.entidad.clases;

public class OrdenxUsuario {
    private Usuario usuario;
    private Orden orden;
    private boolean estado;

    public OrdenxUsuario() {
    }

    public OrdenxUsuario(Usuario usuario, Orden orden, boolean estado) {
        this.usuario = usuario;
        this.orden = orden;
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
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
        return "OrdenxUsuario{" +
                "usuario=" + usuario.toString() +
                ", orden=" + orden.toString() +
                ", estado=" + estado +
                '}';
    }
}
