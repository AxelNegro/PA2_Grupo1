package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.OpcionAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Opcion;

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

        LlenarGD(v);

        return v;
    }

    public void LlenarGD(View v){
        gdOpcion = (GridView)v.findViewById(R.id.gdOpciones);
        List<Opcion> lstOpc = armarLista();

        OpcionAdapter adapter = new OpcionAdapter(v.getContext(),lstOpc);
        gdOpcion.setAdapter(adapter);
    }

    public List<Opcion> armarLista(){
        List<Opcion> lstOpc = new ArrayList<Opcion>();

        Consigna cons = new Consigna(new Nivel(),1,"","",true);
        Opcion opc = new Opcion(cons,1,"Perro",true,true);
        lstOpc.add(opc);

        opc = new Opcion(cons,2,"Gato",false,true);
        lstOpc.add(opc);

        opc = new Opcion(cons,3,"Aguila",false,true);
        lstOpc.add(opc);

        opc = new Opcion(cons,4,"Pez",false,true);
        lstOpc.add(opc);

        cons = new Consigna(new Nivel(),2,"","",true);
        opc = new Opcion(cons,5,"Rojo",false,true);
        lstOpc.add(opc);

        opc = new Opcion(cons,6,"Azul",true,true);
        lstOpc.add(opc);

        opc = new Opcion(cons,7,"Amarillo",false,true);
        lstOpc.add(opc);

        opc = new Opcion(cons,8,"Verde",false,true);
        lstOpc.add(opc);

        return lstOpc;
    }
}