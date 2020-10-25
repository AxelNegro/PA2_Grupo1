package com.example.tp_integrador.entidad.Dao;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Conexion {
    private HttpURLConnection httpURLConnection;
    private OutputStream outputStream;
    private BufferedWriter bufferedWriter;

    public Conexion() {
    }

    public boolean conectar(String urlAux){
        boolean Conexion = false;

        try {
            URL url = new URL(urlAux);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            outputStream = httpURLConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            Conexion = true;
        }catch (Exception e){
            e.printStackTrace();
            Conexion = false;
        }

        return Conexion;
    }

    public boolean mandarInfo(String data){
        boolean Resultado = false;
        try {
            bufferedWriter.write(data);
            bufferedWriter.flush();
            Resultado = true;
        }catch (Exception e){
            e.printStackTrace();
            Resultado = false;
        }

        return Resultado;
    }

    public boolean cerrar_1(){
        boolean Resultado = false;
        try {
            bufferedWriter.close();
            outputStream.close();

            Resultado = true;
        }catch (Exception e){
            e.printStackTrace();
            Resultado = false;
        }

        return Resultado;
    }

    public InputStream obtenerInfo(){
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
        }catch(Exception e){
            e.printStackTrace();
            inputStream = null;
        }
        return inputStream;
    }

    public void cerrar_2(){
        httpURLConnection.disconnect();
    }
}
