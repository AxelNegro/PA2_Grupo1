package com.example.tp_integrador.ui.CA;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.NivelAdapter;
import com.example.tp_integrador.entidad.clases.Nivel;

import java.util.ArrayList;
import java.util.List;

public class fragCA extends Fragment {

    private vmCA vmCa;
    private GridView gdNivel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Parking Control");
        //((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Parqueos");

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ca, container, false);

        LlenarGD(v);

        return v;
    }

    public void LlenarGD(View v){
        gdNivel = (GridView)v.findViewById(R.id.gdNiveles);
        List<Nivel> lstLvl = armarLista();

        NivelAdapter adapter = new NivelAdapter(v.getContext(),lstLvl);
        gdNivel.setAdapter(adapter);

    }

    public List<Nivel> armarLista(){
        List<Nivel> lstNivel = new ArrayList<Nivel>();

        Nivel lvl = new Nivel(1,"Colores");
        lstNivel.add(lvl);
        lvl = new Nivel(2,"Animales");
        lstNivel.add(lvl);
        lvl = new Nivel(3,"Comidas");
        lstNivel.add(lvl);
        lvl = new Nivel(4,"Nombres");
        lstNivel.add(lvl);
        lvl = new Nivel(5,"Familia");
        lstNivel.add(lvl);
        lvl = new Nivel(6,"Educación");
        lstNivel.add(lvl);
        lvl = new Nivel(7,"Lugares");
        lstNivel.add(lvl);
        lvl = new Nivel(8,"Sexualidad");
        lstNivel.add(lvl);
        lvl = new Nivel(9,"Tiempo");
        lstNivel.add(lvl);
        lvl = new Nivel(10,"Ubicación");
        lstNivel.add(lvl);

        return lstNivel;
    }
}