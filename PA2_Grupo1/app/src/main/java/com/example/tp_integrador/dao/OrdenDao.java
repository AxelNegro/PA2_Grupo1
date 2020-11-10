package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.ui.admin.fragOrdenAlta;
import com.example.tp_integrador.ui.admin.fragOrdenList;
import com.example.tp_integrador.ui.admin.fragOrdenMod;

public class OrdenDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Orden orden;
    private String urlAux, data;
    private int accion;
    private fragOrdenAlta fragOrdAlt;
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
            if(accion == 1 || accion == 2) { //Alta-Modificación de nivel
            }
            if (accion == 3){
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
