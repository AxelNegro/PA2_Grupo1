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
import com.example.tp_integrador.ui.admin.navAdmin;

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
    private fragOrdenMod fragOrdenMod;
    private fragOrdenAlta fragOrdenAlta;
    private fragOrdenList fragOrdList;
    private navAdmin main;

    public OrdenDao() {
    }

    public OrdenDao(Context context, Orden orden, int accion, navAdmin main,  fragOrdenAlta fragOrdenAlta) {
        this.context = context;
        this.orden = orden;
        this.accion = accion;
        this.main = main;
        this.fragOrdenAlta = fragOrdenAlta;
        preparaVariables();
    }

    public OrdenDao(Context context, Orden orden, int accion, navAdmin main, fragOrdenMod fragOrdenMod) {
        this.context = context;
        this.orden = orden;
        this.accion = accion;
        this.main = main;
        this.fragOrdenMod = fragOrdenMod;
        preparaVariables();
    }

    public OrdenDao(Context context, Orden orden, int accion, fragOrdenMod fragOrdMod) {
        this.context = context;
        this.orden = orden;
        this.accion = accion;
        this.fragOrdenMod = fragOrdMod;
        preparaVariables();
    }

    public OrdenDao(Context context, int accion, fragOrdenList fragOrdList) {
        this.context = context;
        this.orden = orden;
        this.accion = accion;
        this.fragOrdList = fragOrdList;
        preparaVariables();
    }

    public OrdenDao(int accion, Orden ord, fragOrdenList fragOrdList) {
        this.accion = accion;
        this.orden = ord;
        this.fragOrdList = fragOrdList;
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
                data = URLEncoder.encode("IdOrden", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getIdOrden()), "UTF-8")
                        + "&" + URLEncoder.encode("IdNivel", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getNivel().getIdNivel()), "UTF-8")
                        + "&" + URLEncoder.encode("IdSena", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getSena().getIdSena()), "UTF-8")
                        + "&" + URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getConsigna().getIdConsigna()), "UTF-8")
                        + "&" + URLEncoder.encode("Orden", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getOrden()), "UTF-8");
            }
            else if (accion == 3){
                if(orden.isEstado()) {
                    data = URLEncoder.encode("IdOrden", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getIdOrden()), "UTF-8")
                            + "&" + URLEncoder.encode("Estado", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
                }else{
                    data = URLEncoder.encode("IdOrden", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orden.getIdOrden()), "UTF-8")
                            + "&" + URLEncoder.encode("Estado", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                }
            }
            else if(accion == 4){//Obtener una orden
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
        if(accion == 1){
            if (resultado.equals("1")){
                Toast.makeText(context, "Consigna creada exitosamente", Toast.LENGTH_LONG).show();
                fragOrdenAlta.limpiar();
            }
            else if(resultado.equals("2")){
                Toast.makeText(context, "Seña creada exitosamente", Toast.LENGTH_LONG).show();
                fragOrdenAlta.limpiar();
            }
            else
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
        }
        else if(accion == 2){
            if (resultado.equals("1")){
                Toast.makeText(context, "Consigna modificada exitosamente", Toast.LENGTH_LONG).show();
                fragOrdenMod.limpiar();
                main.Actualizar();
            }
            else if(resultado.equals("2")){
                Toast.makeText(context, "Seña modificada exitosamente", Toast.LENGTH_LONG).show();
                fragOrdenMod.limpiar();
                main.Actualizar();
            }
            else
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();

        }
        else if(accion == 3){
            fragOrdList.mostrarBaja(resultado);
        }
        else if(accion == 4){
            fragOrdenMod.setearDatos(resultado);
        }
        else if(accion == 5){
            fragOrdList.llenarGD(resultado);
        }
    }

}
