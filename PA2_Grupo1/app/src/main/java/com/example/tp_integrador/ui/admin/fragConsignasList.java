package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.entidad.adapters.ConsignaAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;

import java.util.ArrayList;
import java.util.List;

public class fragConsignasList extends Fragment {

    private GridView gdConsignas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adm_consignas_list, container, false);

        gdConsignas = (GridView)  view.findViewById(R.id.gdConsignas);

        //Instancia la actividad main
        ((navAdmin)getActivity()).setFragmentRefreshListener(new navAdmin.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerInfo();
            }
        });

        obtenerInfo();

        return view;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        ConsignaDao artDao = new ConsignaDao(getContext(),4,this);
        artDao.execute();
    }

    public void llenarGD(String resultado){
        List<Consigna> lstConsignas = new ArrayList<>();
        if(!resultado.isEmpty())
            lstConsignas = armarLista(resultado);

        if(lstConsignas!=null&&lstConsignas.size()>0) {
            ConsignaAdapter adapter = new ConsignaAdapter(getContext(), lstConsignas, this);
            gdConsignas.setAdapter(adapter);
        }
    }

    //Carga los articulos a una lista
    public List<Consigna> armarLista(String Resultado){
        List<Consigna> lstConsignas = new ArrayList<Consigna>();

        String[] filas, datos;

        Consigna con = new Consigna();

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {
                datos = filas[i].split(";");

                con.setIdConsigna(Integer.parseInt(datos[0]));
                con.setURLImagen(datos[1]);
                con.setDesc(datos[2]);

                if(datos[3].equals("1")){
                    con.setEstado(true);
                }
                else{
                    con.setEstado(false);
                }

                lstConsignas.add(con);

                con= new Consigna();
            }
        }
        else{
            lstConsignas = null;
        }

        return lstConsignas;
    }

    public void mostrarBaja(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_LONG).show();
        obtenerInfo();
    }
}