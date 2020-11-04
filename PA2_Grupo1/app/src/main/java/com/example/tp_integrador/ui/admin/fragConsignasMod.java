package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;

public class fragConsignasMod extends Fragment {
    private TextView descripcion;
    private Spinner spnNivel, spnConsigna, spnSena;
    private Button modificar;
    private String[] niveles, consignas, senas;

    private Consigna consigna;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_adm_consignas_mod, container, false);

        spnNivel = (Spinner) rootView.findViewById(R.id.spnNivel);
        spnConsigna = (Spinner) rootView.findViewById(R.id.spnConsigna);
        descripcion = (TextView) rootView.findViewById(R.id.txtDescripcion);
        spnSena = (Spinner) rootView.findViewById(R.id.spnIdSena);
        modificar = (Button) rootView.findViewById(R.id.btnEditar);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarConsigna();
            }
        });

        niveles = new String[2];
        niveles[0] = "Colores";
        niveles[1] = "Animales";

        consignas = new String[2];
        consignas[0] = "Blanco";
        consignas[1] = "Rojo";

        senas = new String[2];
        senas[0] = "Perro";
        senas[1] = "Gato";

        spnNivel.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,niveles));
        spnConsigna.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,consignas));
        spnSena.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,senas));

        return rootView;
    }

    private void modificarConsigna(){
        consigna = new Consigna();
        obtenerDatos();

        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,2);
        consignaDao.execute();

        limpiar();

    }



    private void obtenerDatos(){
        consigna.setDesc(spnConsigna.getSelectedItem().toString());
        consigna.setURLImagen(spnSena.getSelectedItem().toString());
    }

    private void limpiar() {
        spnNivel.setSelection(0);
        descripcion.setText("");
        spnConsigna.setSelection(1);
    }
}