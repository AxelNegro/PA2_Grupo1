package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.fragConsignasAlta;
import com.example.tp_integrador.ui.admin.fragConsignasList;
import com.example.tp_integrador.ui.admin.fragConsignasMod;
import com.example.tp_integrador.ui.admin.fragUsuarioMyB;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.example.tp_integrador.dao.Conexion;

public class ConsignaDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Consigna consigna;
    private String urlAux, data;
    private int accion, idNivel;
    private fragConsignasList list;
    private fragConsignasMod mod;
    private fragConsignasAlta alta;
    private int idSena;
    private int idConsigna;

    //Utiliza constructores para seleccionar la accion a ejecutar dependiendo de los parametros que reciba

    //Alta de consigna
    public ConsignaDao(Context context,Consigna consigna, int accion, int idSena) {
        this.context = context;
        this.consigna = consigna;
        this.accion = accion;
        this.idSena = idSena;
        preparaVariables();
    }

    //Ultimo id
    public ConsignaDao(Context context, int accion, fragConsignasAlta alta) {
        this.context = context;
        this.accion = accion;
        this.alta = alta;
        preparaVariables();
    }

    //Modificación de consigna
    public ConsignaDao(Context context,Consigna consigna, int accion,int idSena, fragConsignasMod mod) {
        this.context = context;
        this.consigna = consigna;
        this.accion = accion;
        this.mod = mod;
        this.idSena = idSena;
        preparaVariables();
    }

    public ConsignaDao(Context context, int accion) {
        this.context = context;
        this.accion = accion;
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

    //Listado de consignas - fragConsignasMod - accion 6
    public ConsignaDao(Context context, int accion, int idNivel, fragConsignasMod mod) {
        this.context = context;
        this.accion = accion;
        this.idNivel = idNivel;
        this.mod = mod;
        preparaVariables();
    }

    //baja de consignas - fragConsignasList - accion 7
    public ConsignaDao(int accion,int idConsigna, fragConsignasList list) {
        this.accion = accion;
        this.idConsigna = idConsigna;
        this.list = list;
        preparaVariables();
    }




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
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerConsigna.php";
                llenarData();
                break;
            case 4: // Obtener todos las consignas
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodasConsignas.php";
                break;
            case 5: // Obtener consignas por nivel
            case 6: // Obtener consignas por nivel
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerConsignasxNivel.php";
                llenarData();
                break;
            case 7: // baja de consignas
                urlAux = "https://pagrupo1.000webhostapp.com/bajaConsigna.php";
                llenarData();
                break;
            case 0: // Obtener el último id de consignas
                urlAux = "https://pagrupo1.000webhostapp.com/ObtenerMaxIdConsigna.php";
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 1 ) { //Alta-Modificación de consigna

                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8")
                        + "&" +  URLEncoder.encode("IdNivel", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getNivel().getIdNivel()), "UTF-8")
                        + "&" +  URLEncoder.encode("IdSena", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idSena), "UTF-8")
                        + "&" +  URLEncoder.encode("URL_Imagen", "UTF-8") + "=" + URLEncoder.encode(consigna.getURLImagen(), "UTF-8")
                        + "&" + URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(consigna.getDesc(), "UTF-8");
            }
            if(accion == 2) { //Alta-Modificación de consigna

                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(consigna.getIdConsigna()), "UTF-8")
                        + "&" + URLEncoder.encode("URL_Imagen", "UTF-8") + "=" + URLEncoder.encode(consigna.getURLImagen(), "UTF-8")
                        + "&" + URLEncoder.encode("Descripcion", "UTF-8") + "=" + URLEncoder.encode(consigna.getDesc(), "UTF-8")
                        + "&" + URLEncoder.encode("idSena", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idSena), "UTF-8");
            }
            else if (accion == 3){
                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(consigna.getDesc(), "UTF-8");
            }
            else if(accion == 5 || accion == 6){// Listado de consignas por nivel
                data = URLEncoder.encode("IdNivel", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idNivel), "UTF-8");
            }
            else if (accion == 7){
                data = URLEncoder.encode("IdConsigna", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idConsigna), "UTF-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    //se ejecuta primero antes de execute()
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if(conn.conectar(urlAux)){
            if(accion == 1 || accion == 2 || accion == 3 || accion == 5 || accion == 6 || accion == 7) { //Acciones que realizan escritura (Alta/Modificación)
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
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            //if(accion == 2) main.Actualizar();
        }
        else if(accion == 2){
            if(!resultado.equals("Error al modificar la consigna.")){
                mod.limpiar();
            }
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
        }
        else if(accion == 3){
            String[] datos = resultado.split(";");
        }
        else if(accion == 4){
            list.llenarGD(resultado);
        }
        else if(accion == 5){
            list.llenarGD(resultado);
        }
        else if(accion == 6){
            mod.llenarSpinnerConsignas(resultado);
        }
        else if(accion == 7){
            list.mostrarBaja(resultado);
        }
        else if(accion == 0){
            alta.setLastId(Integer.parseInt(resultado));
        }
    }
}
