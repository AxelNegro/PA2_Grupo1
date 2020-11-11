package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class fragConsignasAlta extends Fragment {
    private TextView txtImagen, txtDescripcion;
    private Button alta;

    private Consigna consigna;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_adm_consignas_alta, container, false);
        txtDescripcion = (TextView) rootView.findViewById(R.id.txtDesc);
        txtImagen = (TextView) rootView.findViewById(R.id.txtImagen);
        alta = (Button) rootView.findViewById(R.id.btnAlta);

        alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltaConsigna();
            }
        });

        return rootView;
    }

    private void AltaConsigna(){
        consigna = new Consigna();
        obtenerDatos();
        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,1);
        consignaDao.execute();

        limpiar();
    }

    private void obtenerDatos(){
        consigna.setDesc(txtDescripcion.getText().toString());
        consigna.setURLImagen(txtImagen.getText().toString());
    }

    private void limpiar() {
        txtDescripcion.setText("");
        txtImagen.setText("");
    }
}