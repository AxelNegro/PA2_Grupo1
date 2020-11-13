package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Opcion;
import com.example.tp_integrador.ui.admin.fragConsignasAlta;
import com.example.tp_integrador.ui.admin.fragConsignasList;
import com.example.tp_integrador.ui.admin.fragOpcionesAlta;
import com.example.tp_integrador.ui.admin.fragOpcionesList;
import com.example.tp_integrador.ui.admin.fragOpcionesMod;
import com.example.tp_integrador.ui.admin.navAdmin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OpcionDao extends AsyncTask<String, Void, String> {
    private Context context;
    private Opcion opcion;
    private String urlAux, data;
    private int accion, idConsigna;
    private fragOpcionesAlta alta;
    private fragOpcionesList list;
    private fragOpcionesMod mod;
    private navAdmin main;

    //Utiliza constructores para seleccionar la accion a ejecutar dependiendo de los parametros que reciba

    //Alta de opcion
    public OpcionDao(Context context, Opcion opcion, int accion, navAdmin main, fragOpcionesAlta alta) {
        this.context = context;
        this.opcion = opcion;
        this.accion = accion;
        this.alta = alta;
        this.main = main;
        preparaVariables();
    }

    //Listado de consignas - fragConsignasList - accion 4
    public OpcionDao(Context context, int accion, fragOpcionesList list) {
        this.context = context;
        this.accion = accion;
        this.list = list;
        preparaVariables();
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de opcion
                urlAux = "https://pagrupo1.000webhostapp.com/altaOpcion.php";
                llenarData();
                break;
            case 2: // Modificación de opcion
                //urlAux = "https://pagrupo1.000webhostapp.com/modificarConsigna.php";
                //llenarData();
                //break;
            case 3: // Obtener una opcion
                //urlAux = "https://pagrupo1.000webhostapp.com/obtenerConsigna.php";
                //llenarData();
                //break;
            case 4: // Obtener todos las opciones x consigna
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodasOpciones.php";
                break;
        }
    }

    public void llenarData(){
        try {

            if(accion == 1 ) { //Alta
                if(opcion.isRes()) {
                    data = URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(opcion.getDesc(), "UTF-8")
                            + "&" + URLEncoder.encode("Resultado", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")
                            + "&" + URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(opcion.getConsigna().getIdConsigna()), "UTF-8");
                }else{
                    data = URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(opcion.getDesc(), "UTF-8")
                            + "&" + URLEncoder.encode("Resultado", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")
                            + "&" + URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(opcion.getConsigna().getIdConsigna()), "UTF-8");
                }
            }
            /*
            if(accion == 2) { //Modificación de opciones

                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8")
                        + "&" + URLEncoder.encode("URL_Imagen", "UTF-8") + "=" + URLEncoder.encode(consigna.getURLImagen(), "UTF-8")
                        + "&" + URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(consigna.getDesc(), "UTF-8");
            }

            else if (accion == 3){//Busqueda de opcion
                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8");
            }

            else if (accion == 5){
                if(consigna.isEstado()) {
                    data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8")
                            + "&" + URLEncoder.encode("Estado", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
                }else{
                    data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8")
                            + "&" + URLEncoder.encode("Estado", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                }
            }
             */

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


    @Override
    //se ejecuta primero antes de execute()
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if(conn.conectar(urlAux)){
            if(accion == 1 || accion == 2 || accion == 3 || accion == 5) { //Acciones que realizan escritura (Alta/Modificación)
                if (conn.mandarInfo(data)) {
                    resultado = obtenerInfo(conn);
                } else {
                    Log.d("BBDD", "Hubo un error al mandar la información a la base de datos.");
                }
            }
            else{ //Acciones que realizan lectura (Obtener uno/Todos)
                resultado = obtenerInfo(conn);
            }
        }
        else{
            Log.d("BBDD","Hubo un error al conectarse con la base de datos.");
        }

        return resultado;
    }

    //resultado despues del doInBackground()
    protected void onPostExecute(String resultado){
        if(accion == 1) {
            if(resultado.equals("1")){
                Toast.makeText(context, "Consigna creada exitosamente", Toast.LENGTH_LONG).show();
                alta.limpiar();
                main.Actualizar();
            }
            else
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
        }
        else if(accion == 2){
            if(resultado.equals("1")){
                Toast.makeText(context, "Consigna modificada exitosamente", Toast.LENGTH_LONG).show();
                //mod.limpiar();
                main.Actualizar();
            }
            else
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
        }
        else if(accion == 3){
            //mod.obtenerDatos(resultado);
        }
        else if(accion == 4){
            list.LlenarGD(resultado);
        }
        else if(accion == 5){
            //list.mostrarBaja(resultado);
        }
    }
}
