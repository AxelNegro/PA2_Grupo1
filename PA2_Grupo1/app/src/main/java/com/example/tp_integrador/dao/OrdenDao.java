package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.ui.admin.fragOrdenAlta;
import com.example.tp_integrador.ui.admin.fragOrdenList;
import com.example.tp_integrador.ui.admin.fragOrdenMod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OrdenDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Orden orden;
    private String urlAux, data;
    private int accion;
    private fragOrdenMod fragOrdMod;
    private fragOrdenList fragOrdList;

    public OrdenDao() {
    }

    public OrdenDao(Context context, Orden orden, int accion) {
        this.context = context;
        this.orden = orden;
        this.accion = accion;
        preparaVariables();
    }

    public OrdenDao(Context context, Orden orden, int accion, fragOrdenMod fragOrdMod) {
        this.context = context;
        this.orden = orden;
        this.accion = accion;
        this.fragOrdMod = fragOrdMod;
        preparaVariables();
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de orden
                urlAux = "https://pagrupo1.000webhostapp.com/altaOrdenNivel.php";
                llenarData();
                break;
            case 2: // Modificación de orden
                urlAux = "https://pagrupo1.000webhostapp.com/modificarOrdenNivel.php";
                llenarData();
                break;
            case 3: // Baja de orden
                urlAux = "https://pagrupo1.000webhostapp.com/bajaOrdenNivel.php";
                llenarData();
                break;
            case 4: // Obtener una orden
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerOrdenNivel.php";
                llenarData();
                break;
            case 5: // Obtener todas las ordenes
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodosOrdenNivel.php";
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 1) { //Alta de Orden
                data = URLEncoder.encode("IdNivel", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getNivel().getIdNivel()), "UTF-8")
                        + "&" + URLEncoder.encode("IdSena", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getSena().getIdSena()), "UTF-8")
                        + "&" + URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getConsigna().getIdConsigna()), "UTF-8")
                        + "&" + URLEncoder.encode("Orden", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getOrden()), "UTF-8");
            }
            else if(accion == 2){//Modificación de Orden

            }
            else if (accion == 3){
            }
            else if(accion == 4){
                data = URLEncoder.encode("IdOrden", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getIdOrden()), "UTF-8");
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
            if(accion == 1 || accion == 2 || accion == 3 || accion == 4) { //Acciones que realizan escritura (Alta/Modificación)
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
        if(accion == 1 || accion == 2) {
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            //if(accion == 2) main.Actualizar();
        }
        else if(accion == 3){
        }
        else if(accion == 4){
            fragOrdMod.obtenerDatos(resultado);
        }
    }

}
