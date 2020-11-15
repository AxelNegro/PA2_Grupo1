package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tp_integrador.entidad.adapters.OrdenxUsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Sena;
import com.example.tp_integrador.ui.admin.fragOrdenAlta;
import com.example.tp_integrador.ui.admin.fragOrdenMod;
import com.example.tp_integrador.ui.cliente.ListadoSenas.fragListadoSenas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SenasDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Sena sena;
    private Nivel nivel;
    private String urlAux, data;
    private int accion;
    private fragListadoSenas fragList;
    private fragOrdenAlta fragOrdAlta;
    private fragOrdenMod fragOrdMod;
    private com.example.tp_integrador.entidad.clases.OrdenxUsuario OrdenxUsuario;
    private OrdenxUsuarioAdapter adapter;

    //Listado de señas
    public SenasDao(Context context, int accion, fragListadoSenas fragList)
    {
        this.context = context;
        this.accion = accion;
        this.fragList = fragList;
        preparaVariables();
    }

    public SenasDao(Context context, int accion, fragOrdenAlta fragOrdAlta)
    {
        this.context = context;
        this.accion = accion;
        this.fragOrdAlta = fragOrdAlta;
        preparaVariables();
    }

    public SenasDao(Context context, int accion, fragOrdenMod fragOrdMod)
    {
        this.context = context;
        this.accion = accion;
        this.fragOrdMod = fragOrdMod;
        preparaVariables();
    }

    public SenasDao(Context context, int accion, com.example.tp_integrador.entidad.clases.OrdenxUsuario OrdenxUsuario, OrdenxUsuarioAdapter adapter)
    {
        this.context = context;
        this.accion = accion;
        this.OrdenxUsuario = OrdenxUsuario;
        this.adapter = adapter;
        preparaVariables();
    }

    public SenasDao(Context context, int accion) {
    }

    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de Sena
                urlAux = "https://pagrupo1.000webhostapp.com/altaSena.php";
                //llenarData();
                break;
            case 2: // Modificación de Sena
                urlAux = "https://pagrupo1.000webhostapp.com/modificarSena.php";
                //llenarData();
                break;
            case 3: // Obtener una Sena
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerSena.php";
                llenarData();
                break;
            case 4: // Obtener todos las Senas
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodasSenas.php";
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 3){//Obtener una seña
                int idSena = OrdenxUsuario.getOrden().getSena().getIdSena();

                data = URLEncoder.encode("IdSena", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idSena), "UTF-8");
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
            if(accion != 4) { //Acciones que realizan escritura (Alta/Modificación)
                if (conn.mandarInfo(data)) {
                    resultado = obtenerInfo(conn);
                } else {
                    Log.d("BBDD", "Hubo un error al mandar la información a la base de datos.");
                }
            }
            else if(accion == 4){ //Acciones que realizan lectura (Obtener uno)
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
            adapter.mostrarPopupSena(resultado);
        }
        else if(accion == 4){
            if(fragList != null)
                fragList.LlenarGD(resultado);
            else if(fragOrdAlta != null)
                fragOrdAlta.llenarDDL(resultado,2);
            else if(fragOrdMod != null)
                fragOrdMod.llenarDDL(resultado,2);
        }
    }
}
