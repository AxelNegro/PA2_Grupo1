package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.entidad.clases.Sena;
import com.example.tp_integrador.ui.cliente.ListadoSenas.fragListadoSenas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SenasDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Sena sena;
    private String urlAux, data;
    private int accion;
    private fragListadoSenas fragList;

    public SenasDao(Context context, int accion, fragListadoSenas fragList)
    {
        this.context = context;
        this.accion = accion;
        this.fragList = fragList;
        preparaVariables();
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de Sena
                //urlAux = "https://pagrupo1.000webhostapp.com/altaConsigna.php";
                //llenarData();
                break;
            case 2: // Modificación de Sena
                //urlAux = "https://pagrupo1.000webhostapp.com/modificarConsigna.php";
                //llenarData();
                break;
            case 3: // Obtener una Sena
                //urlAux = "https://pagrupo1.000webhostapp.com/obtenerConsigna.php";
                //llenarData();
                break;
            case 4: // Obtener todos las Senas
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodasSenas.php";
                break;
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

    //se ejecuta primero antes de execute()
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if(conn.conectar(urlAux)){
            if(accion == 1 || accion == 2 || accion == 3) { //Acciones que realizan escritura (Alta/Modificación)
                if (conn.mandarInfo(data)) {
                    resultado = obtenerInfo(conn);
                } else {
                    Log.d("BBDD", "Hubo un error al mandar la información a la base de datos.");
                }
            }
            else if(accion == 4){ //Acciones que realizan lectura (Obtener uno/Todos)
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
        if(accion == 1 || accion == 2) {
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            //if(accion == 2) main.Actualizar();
        }
        else if(accion == 3){
            String[] datos = resultado.split(";");
        }
        else if(accion == 4){
            fragList.LlenarGD(resultado);
        }
    }
}
