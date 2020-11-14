package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tp_integrador.entidad.adapters.OrdenxUsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.OrdenxUsuario;
import com.example.tp_integrador.ui.admin.fragConsignasAlta;
import com.example.tp_integrador.ui.admin.fragConsignasList;
import com.example.tp_integrador.ui.admin.fragConsignasMod;
import com.example.tp_integrador.ui.admin.fragOpcionesAlta;
import com.example.tp_integrador.ui.admin.navAdmin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsignaDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Consigna consigna;
    private String urlAux, data;
    private int accion, idNivel;
    private fragConsignasAlta alta;
    private fragConsignasList list;
    private fragConsignasMod mod;
    private OrdenxUsuarioAdapter adapter;
    private OrdenxUsuario ordxus;
    private navAdmin main;
    private fragOpcionesAlta altaOpciones;

    //Utiliza constructores para seleccionar la accion a ejecutar dependiendo de los parametros que reciba

    //Alta de consigna
    public ConsignaDao(Context context, Consigna consigna, int accion, navAdmin main, fragConsignasAlta alta) {
        this.context = context;
        this.consigna = consigna;
        this.accion = accion;
        this.alta = alta;
        this.main = main;
        preparaVariables();
    }

    public ConsignaDao(Context context, Consigna consigna, int accion, navAdmin main, fragConsignasMod mod) {
        this.context = context;
        this.consigna = consigna;
        this.accion = accion;
        this.main = main;
        this.mod = mod;
        preparaVariables();
    }

    //Busqueda/Modificación de consigna
    public ConsignaDao(Context context, Consigna consigna, int accion, fragConsignasMod mod) {
        this.context = context;
        this.consigna = consigna;
        this.accion = accion;
        this.mod = mod;
        preparaVariables();
    }

    //Listado de consignas - fragConsignasList - accion 4
    public ConsignaDao(Context context, int accion, fragConsignasList list) {
        this.context = context;
        this.accion = accion;
        this.list = list;
        preparaVariables();
    }

    //Listado de consignas filtrados por nivel - fragConsignasList - accion 5
    public ConsignaDao(Context context, int accion, int idNivel, fragConsignasList list) {
        this.context = context;
        this.accion = accion;
        this.list = list;
        this.idNivel = idNivel;
        preparaVariables();
    }

    //Baja de consignas
    public ConsignaDao(int accion, Consigna consigna, fragConsignasList list) {
        this.accion = accion;
        this.consigna = consigna;
        this.list = list;
        preparaVariables();
    }

    //Baja de consignas
    public ConsignaDao(int accion, OrdenxUsuario ordxus, OrdenxUsuarioAdapter adapter) {
        this.accion = accion;
        this.ordxus = ordxus;
        this.adapter = adapter;
        preparaVariables();
    }

    /*
    //Listado de consignas - fragOpcionesAlta
    public ConsignaDao(Context context, int accion, fragOpcionesAlta altaOpciones) {
        this.context = context;
        this.accion = accion;
        this.altaOpciones = altaOpciones;
        preparaVariables();
    }
    */



    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de consigna
                urlAux = "https://pagrupo1.000webhostapp.com/altaConsigna.php";
                llenarData();
                break;
            case 2: // Modificación de consigna
                urlAux = "https://pagrupo1.000webhostapp.com/modificarConsigna.php";
                llenarData();
                break;
            case 3: // Obtener una consigna
            case 6:
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerConsigna.php";
                llenarData();
                break;
            case 4: // Obtener todos las consignas
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodasConsignas.php";
                break;
            case 5: // baja de consignas
                urlAux = "https://pagrupo1.000webhostapp.com/bajaConsigna.php";
                llenarData();
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 1 ) { //Alta
                data = URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(consigna.getDesc(), "UTF-8")
                        + "&" + URLEncoder.encode("URL_Imagen", "UTF-8") + "=" + URLEncoder.encode(consigna.getURLImagen(), "UTF-8");
            }
            if(accion == 2) { //Modificación de consigna

                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8")
                        + "&" + URLEncoder.encode("URL_Imagen", "UTF-8") + "=" + URLEncoder.encode(consigna.getURLImagen(), "UTF-8")
                        + "&" + URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(consigna.getDesc(), "UTF-8");
            }
            else if (accion == 3){//Busqueda de consigna
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
            else if(accion == 6){
                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ordxus.getOrden().getConsigna().getIdConsigna()), "UTF-8");
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

    @Override
    //se ejecuta primero antes de execute()
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if(conn.conectar(urlAux)){
            if(accion == 1 || accion == 2 || accion == 3 || accion == 5 || accion == 6) { //Acciones que realizan escritura (Alta/Modificación)
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
                mod.limpiar();
                main.Actualizar();
            }
            else
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
        }
        else if(accion == 3){
            mod.obtenerDatos(resultado);
        }
        else if(accion == 4){
            list.llenarGD(resultado);
        }
        else if(accion == 5){
            list.mostrarBaja(resultado);
        }
        else if(accion == 6){
            adapter.mostrarPopupEjercicio(resultado,ordxus);
        }else if(accion == 7)
        {
            //altaOpciones.llenarDDL(resultado,1);
        }
    }
}
