package com.example.tp_integrador.entidad.clases;

public class Usuario {

    private int IdUsuario;
    private String NameUser;
    private String KeyUser;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Ubicacion;
    private boolean Estado;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String nameUser) {
        NameUser = nameUser;
    }

    public String getKeyUser() {
        return KeyUser;
    }

    public void setKeyUser(String keyUser) {
        KeyUser = keyUser;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public Usuario(){
    }

    public Usuario(int idUsuario, String nameUser, String keyUser, String nombre, String apellido, String email, String ubicacion, boolean estado) {
        IdUsuario = idUsuario;
        NameUser = nameUser;
        KeyUser = keyUser;
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
        Ubicacion = ubicacion;
        Estado = estado;
    }
}
