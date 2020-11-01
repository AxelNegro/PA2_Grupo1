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
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;

public class fragConsignasAlta extends Fragment {
    TextView descripcion;
    Spinner nivel, sena;
    Button alta;
    String[] niveles, senas;

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
        nivel = (Spinner) rootView.findViewById(R.id.txtIdNivel);
        descripcion = (TextView) rootView.findViewById(R.id.txtDescOpc);
        sena = (Spinner) rootView.findViewById(R.id.txtIdSena);
        alta = (Button) rootView.findViewById(R.id.btnAlta);

        alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltaConsigna();
            }
        });

        niveles = new String[2];
        niveles[0] = "Colores";
        niveles[1] = "Animales";

        senas = new String[2];
        senas[0] = "Blanco";
        senas[1] = "Rojo";

        nivel.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,niveles));
        sena.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,senas));

        nivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                String selecteditem =  adapter.getItemAtPosition(i).toString();
                if(i==1){
                    senas[0] = "Blanco";
                    senas[1] = "Rojo";
                }
                if(i==2) {
                    senas[0] = "Perro";
                    senas[1] = "Gato";
                }
                sena.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,senas));
                //or this can be also right: selecteditem = level[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        return rootView;
    }

    private void AltaConsigna(){
        consigna = new Consigna();
        obtenerDatos();
        consigna.setEstado(true);
        Nivel nivel = new Nivel();
        nivel.setIdNivel(1);
        consigna.setNivel(nivel);

        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,1);
        consignaDao.execute();

        limpiar();
    }



    private void obtenerDatos(){
        consigna.setDesc(descripcion.getText().toString());
        consigna.setURLImagen(sena.getSelectedItem().toString());
    }

    private void limpiar() {
        nivel.setSelection(0);
        descripcion.setText("");
        sena.setSelection(1);
    }
}