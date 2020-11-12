package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.entidad.clases.OrdenxUsuario;
import com.example.tp_integrador.ui.admin.fragOrdenAlta;
import com.example.tp_integrador.ui.admin.fragOrdenList;
import com.example.tp_integrador.ui.admin.fragOrdenMod;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA.actListadoSenasCA;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OrdenxUsuarioDAO extends AsyncTask<String, Void, String> {
    private Context context;
    private OrdenxUsuario ordxus;
    private String urlAux, data;
    private int accion;
    private actListadoSenasCA main;

    public OrdenxUsuarioDAO() {
    }

    public OrdenxUsuarioDAO(Context context, OrdenxUsuario ordxus, int accion, actListadoSenasCA main) {
        this.context = context;
        this.ordxus = ordxus;
        this.accion = accion;
        this.main = main;
        preparaVariables();
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de orden
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerOrdenxUsuario.php";
                llenarData();
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 1) { //Alta de Orden
                data = URLEncoder.encode("Usuario", "UTF-8") + "=" + URLEncoder.encode(ordxus.getUsuario().getNameUser(), "UTF-8")
                        + "&" + URLEncoder.encode("IdNivel", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ordxus.getOrden().getNivel().getIdNivel()), "UTF-8");
                Log.d("AAAAAA",data);
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
            if(accion == 1) { //Acciones que realizan escritura (Alta/Modificación)
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
            main.llenarGD(resultado);
        }
    }
}
