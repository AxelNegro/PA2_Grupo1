package com.example.tp_integrador.ui.cliente.ListadoSenas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.SenasAdapter;

import java.util.ArrayList;

public class fragListadoSenas extends Fragment {

    private vmListadoSenas vmListadoSenas;
    private ListView listaSenas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listadosenas, container, false);

        LlenarGD(root);

        return root;
    }

    public void LlenarGD(View v){
        listaSenas = (ListView) v.findViewById(R.id.listaSenas);
        ArrayList<String> lstLvl = armarLista();

        SenasAdapter adapter = new SenasAdapter(v.getContext(),R.layout.gdlistado_senas,lstLvl);
        listaSenas.setAdapter(adapter);

    }

    public ArrayList<String> armarLista(){
        ArrayList<String> lstNivel = new ArrayList<String>();

        lstNivel.add("Azul");
        lstNivel.add("Blanco");
        lstNivel.add("Rojo");
        lstNivel.add("Negro");
        lstNivel.add("Violeta");



        return lstNivel;
    }
}