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
import com.example.tp_integrador.entidad.adapters.ConsignaAdapter;
import com.example.tp_integrador.entidad.adapters.NivelAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;

import java.util.ArrayList;
import java.util.List;

public class fragConsignasAlta extends Fragment {
    private TextView descripcion;
    private Spinner spnNivel, sena;
    private Button alta;
    private String[] senas;
    private List<Nivel> niveles;

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
        spnNivel = (Spinner) rootView.findViewById(R.id.spnIdNivel);
        descripcion = (TextView) rootView.findViewById(R.id.txtDescOpc);
        sena = (Spinner) rootView.findViewById(R.id.txtIdSena);
        alta = (Button) rootView.findViewById(R.id.btnAlta);

        alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltaConsigna();
            }
        });

        senas = new String[2];
        senas[0] = "Blanco";
        senas[1] = "Rojo";

        sena.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,senas));

        NivelDao nivelDao = new NivelDao(getContext(),4,this);
        nivelDao.execute();

        spnNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                String selecteditem =  adapter.getItemAtPosition(i).toString();
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

        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,1);
        consignaDao.execute();

        limpiar();
    }



    private void obtenerDatos(){
        consigna.setDesc(descripcion.getText().toString());
        consigna.setURLImagen(sena.getSelectedItem().toString());
    }

    private void limpiar() {
        spnNivel.setSelection(0);
        descripcion.setText("");
        sena.setSelection(1);
    }

        public void llenarSpinnerNivel(String resultado){
            armarLista(resultado);

            int i=0;
            String[] res = new String[niveles.size()];
            for (Nivel n:niveles) {
                res[i] = n.getNivel();
                i++;
            }

            spnNivel.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,res));

    }

    private void armarLista(String Resultado) {
        niveles= new ArrayList<>();

        String[] filas, datos;

        Nivel nivel;

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {
                nivel = new Nivel();

                datos = filas[i].split(";");

                nivel.setIdNivel(Integer.parseInt(datos[0]));
                nivel.setNivel(datos[1]);

                niveles.add(nivel);
            }
        }

    }
}