package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tp_integrador.entidad.adapters.OrdenxUsuarioAdapter;
import com.example.tp_integrador.entidad.clases.OrdenxUsuario;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OpcionxConsignaDao extends AsyncTask<String, Void, String> {
    private Context context;
    private OrdenxUsuario ordxus;
    private String urlAux, data;
    private int accion;
    private OrdenxUsuarioAdapter adapter;

    public OpcionxConsignaDao() {
    }

    public OpcionxConsignaDao(Context context, int accion, OrdenxUsuario ordxus, OrdenxUsuarioAdapter adapter) {
        this.context = context;
        this.ordxus = ordxus;
        this.accion = accion;
        this.adapter = adapter;
        preparaVariables();
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Obtener Orden x Usuario
                //urlAux = "https://pagrupo1.000webhostapp.com/altaOpcion.php";
                //llenarData();
                break;
            case 2: // Obtener opciones por consigna
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerOpcionesxConsigna.php";
                llenarData();
                break;
        }
    }

    public void llenarData(){
        try {//Obtener opciones por consigna
            if(accion == 2){
                int idConsigna = ordxus.getOrden().getConsigna().getIdConsigna();
                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idConsigna), "UTF-8");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String obtenerInfo(Conexion conn){
        String line, resultado = "";
        if (conn.cerrar_1()) {
            InputStream inputStream = conn.obtenerInfo();
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                resultado = stringBuilder.toString();
                conn.cerrar_2();
            } else {
                Log.d("BBDD", "Hubo un error al conectarse con la base de datos.");
            }
        } else {
            Log.d("BBDD", "Hubo un error al cerrar la conexión con la base de datos.");
        }

        return resultado;
    }

    //Se ejecuta primero antes de execute()
    @Override
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if(conn.conectar(urlAux)){
            if(accion == 1 || accion == 2) { //Acciones que realizan escritura (Alta/Modificación)
                if (conn.mandarInfo(data)) {
                    resultado = obtenerInfo(conn);
                } else {
                    Log.d("BBDD", "Hubo un error al mandar la información a la base de datos.");
                }
            }
            else{ //Acciones que realizan lectura (Obtener uno)
                resultado = obtenerInfo(conn);
            }
        }
        else{
            Log.d("BBDD","Hubo un error al conectarse con la base de datos.");
        }

        return resultado;
    }

    //Resultado despues del doInBackground()
    protected void onPostExecute(String resultado){
        if(accion == 1){
        }
        else if (accion ==2){
            adapter.armarListaSena(resultado, ordxus);
        }
    }
}
