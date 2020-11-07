package com.example.tp_integrador.entidad.clases;

public class Usuario {
    private String NameUser;
    private String KeyUser;
    private String Nombre;
    private String Apellido;
    private String Email;
    private int Tipo_Cuenta;
    private boolean Estado;

    public int getTipo_Cuenta() {return Tipo_Cuenta; }

    public void setTipo_Cuenta(int tipo_Cuenta) { Tipo_Cuenta = tipo_Cuenta;}

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

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public Usuario(){
        NameUser="Default";
        KeyUser="123456";
        Nombre="Default";
        Apellido="Default";
        Email="default@gmail.com";
        Tipo_Cuenta=0;
        Estado=true;
    }

    public Usuario(String nameUser, String keyUser, String nombre, String apellido, String email, String ubicacion, boolean estado, int tc) {
        NameUser = nameUser;
        KeyUser = keyUser;
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
        Estado = estado;
        Tipo_Cuenta=tc;
    }

    @Override
    public String toString() {
        return NameUser + ';' + KeyUser + ';' + Nombre + ';' + Apellido + ';' + Email + ';' + Estado +';'+ Tipo_Cuenta+'|' ;
    }
}
