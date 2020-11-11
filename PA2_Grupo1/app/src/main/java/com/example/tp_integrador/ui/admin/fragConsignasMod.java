package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class fragConsignasMod extends Fragment {
    private TextView txtIdConsigna, txtDescripcion, txtImagen;
    private Button btnBuscar, btnModificar;
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

        txtIdConsigna = (TextView) rootView.findViewById(R.id.txtIdConsigna);
        txtDescripcion = (TextView) rootView.findViewById(R.id.txtDesc);
        txtImagen = (TextView) rootView.findViewById(R.id.txtImagen);
        btnBuscar = (Button) rootView.findViewById(R.id.btnBuscar);
        btnModificar = (Button) rootView.findViewById(R.id.btnModificar);

        consigna = new Consigna();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarConsigna();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarConsigna();
            }
        });

        return rootView;
    }

    private void modificarConsigna(){
        if(!(txtIdConsigna.getText().toString().isEmpty()||txtDescripcion.getText().toString().isEmpty()||txtImagen.toString().isEmpty())) {
            try{
                consigna.setIdConsigna(Integer.valueOf(txtIdConsigna.getText().toString()));
                consigna.setDesc(txtDescripcion.getText().toString());
                consigna.setURLImagen(txtImagen.getText().toString());

                ConsignaDao consDao = new ConsignaDao(getContext(), consigna, 2, (navAdmin)getActivity());
                consDao.execute();
            }
            catch(Exception e){
                Toast.makeText(getActivity(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getActivity(),"Complete los datos.",Toast.LENGTH_LONG).show();
        }
    }

    private void buscarConsigna(){
        if(!txtIdConsigna.getText().toString().isEmpty()){
            try {
                consigna.setIdConsigna(Integer.valueOf(txtIdConsigna.getText().toString()));

                ConsignaDao consDao = new ConsignaDao(getContext(), consigna, 3, this);
                consDao.execute();
            }
            catch(Exception e){
                Toast.makeText(getActivity(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getActivity(),"Complete los datos.",Toast.LENGTH_LONG).show();
        }

    }

    public void obtenerDatos(String resultado){
        resultado = resultado.substring(0,resultado.length() - 1);

        String[] datos = resultado.split(";");

        txtDescripcion.setText(datos[2]);
        txtImagen.setText(datos[1]);

    }

}