package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.OpcionDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.adapters.OpcionAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Opcion;
import com.example.tp_integrador.entidad.clases.Sena;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.ArrayList;
import java.util.List;

public class fragOpcionesList extends Fragment {

    private GridView gdOpcion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_adm_opciones_list, container, false);

        gdOpcion = (GridView)v.findViewById(R.id.gdOpciones);

        //Instancia la actividad main
        ((navAdmin)getActivity()).setFragmentRefreshListener(new navAdmin.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerInfo();
            }
        });
        obtenerInfo();

        return v;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        OpcionDao opcionDao = new OpcionDao(getContext(),4,this);
        opcionDao.execute();
    }

    public void LlenarGD(String resultado){

        List<Opcion> lstOpc = armarLista(resultado);

        OpcionAdapter adapter = new OpcionAdapter(getContext(),lstOpc);
        gdOpcion.setAdapter(adapter);
    }

    //Carga los articulos a una lista
    public List<Opcion> armarLista(String Resultado){

        List<Opcion> lstOpcion = new ArrayList<Opcion>();

        String res;
        String[] filas, datos;

        //Crea objetos de Opcion
        Opcion con = new Opcion();

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {
            res = Resultado.substring(0, Resultado.length() - 1);

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {

                datos = filas[i].split(";");

                con.setIdOpcion(Integer.parseInt(datos[0]));
                con.setDesc(datos[1]);
                if(datos[2].equals("1")){
                    con.setRes(true);
                }else{
                    con.setRes(false);
                }
                if(datos[3].equals("1")){
                    con.setEstado(true);
                }else{
                    con.setEstado(false);
                }



                lstOpcion.add(con);

                con= new Opcion();
            }
        }
        else{
            lstOpcion = null;
        }

        return lstOpcion;
    }
}