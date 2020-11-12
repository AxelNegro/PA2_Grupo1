package com.example.tp_integrador.entidad.clases;

public class NivelesxUsuario {
    private Nivel Nivel;
    private Usuario Usuario;
    private boolean Estado;

    public NivelesxUsuario() {
    }

    public NivelesxUsuario(Nivel Nivel, Usuario usuario, boolean estado) {
        this.Nivel = Nivel;
        Usuario = usuario;
        Estado = estado;
    }

    public Nivel getNivel() {
        return Nivel;
    }

    public void setNivel(Nivel Nivel) {
        this.Nivel = Nivel;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "NivelesxUsuario{" +
                "IdNivel=" + Nivel.toString() +
                ", Username=" + Usuario.toString() +
                ", Estado=" + Estado +
                '}';
    }
}
