package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.entidad.clases.Nivel;
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

public class NivelDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Nivel nivel;
    private String urlAux, data;
    private int accion;

    //Utiliza constructores para seleccionar la accion a ejecutar dependiendo de los parametros que reciba
    public NivelDao(Context context,Nivel nivel, int accion) {
        this.context = context;
        this.nivel = nivel;
        this.accion = accion;
        preparaVariables();
    }

    public NivelDao(Context context, int accion) {
        this.context = context;
        this.accion = accion;
        preparaVariables();
    }

    public NivelDao() {

    }

    public void UsuarioDAO(Context context, Nivel nivel, int accion){
        this.context = context;
        this.nivel = nivel;
        this.accion = accion;
        preparaVariables();
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de nivel
                //urlAux = "https://pagrupo1.000webhostapp.com/altaNivel.php";
                llenarData();
                break;
            case 2: // Modificación de nivel
                //urlAux = "https://pagrupo1.000webhostapp.com/modificarNivel.php";
                llenarData();
                break;
            case 3: // Obtener un nivel
                //urlAux = "https://pagrupo1.000webhostapp.com/obtenerNivel.php";
                llenarData();
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 1 || accion == 2) { //Alta-Modificación de nivel
            }
            if (accion == 3){
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
            if(accion == 1 || accion == 2 || accion == 3) { //Acciones que realizan escritura (Alta/Modificación)
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
        if(accion == 1 || accion == 2) {
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            //if(accion == 2) main.Actualizar();
        }
        else if(accion == 3){
            /*String[] datos = resultado.split(";");
            mod.setearDatos(datos);*/
        }
    }
}
