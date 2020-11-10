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
    private TextView descripcion;
    private Spinner spnNivel, spnSena;
    private Button alta;
    private List<Nivel> niveles;
    private List<Sena> senas;
    private Nivel nivel;
    private fragConsignasAlta fragConsignasAlta;
    private int lastId;

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
        spnSena = (Spinner) rootView.findViewById(R.id.txtIdSena);
        alta = (Button) rootView.findViewById(R.id.btnModificar);
        fragConsignasAlta= this;

        alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltaConsigna();
            }
        });


        NivelDao nivelDao = new NivelDao(getContext(),4,this);
        nivelDao.execute();

        ConsignaDao ConsignaDao = new ConsignaDao(getContext(),0,this);
        ConsignaDao.execute();


        spnNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                SenasDao senasDao = new SenasDao(getContext(),5,fragConsignasAlta, niveles.get(i));
                senasDao.execute();
                nivel = niveles.get(i);

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

        int posicion = spnSena.getSelectedItemPosition();
        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,1,senas.get(posicion).getIdSena());
        consignaDao.execute();

        //Trae el último id de la tabla para el proximo registro a guardar
        ConsignaDao ConsignaDao = new ConsignaDao(getContext(),0,this);
        ConsignaDao.execute();

        limpiar();
    }



    private void obtenerDatos(){
        consigna.setNivel(niveles.get(spnNivel.getSelectedItemPosition()));
        consigna.setIdConsigna(lastId+1);
        consigna.setDesc(descripcion.getText().toString());
        consigna.setURLImagen(senas.get(spnSena.getSelectedItemPosition()).getImagen());
    }

    private void limpiar() {
        spnNivel.setSelection(0);
        descripcion.setText("");
        spnSena.setSelection(0);
    }

    public void llenarSpinnerNivel(String resultado){
        armarListaNivel(resultado);

        int i=0;
        String[] res = new String[niveles.size()];
        for (Nivel n:niveles) {
            res[i] = n.getNivel();
            i++;
        }

        spnNivel.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,res));

    }

    private void armarListaNivel(String Resultado) {
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

    public void llenarSpinnerSena(String resultado) {
        armarListaSenas(resultado);

        int i=0;
        String[] res = new String[senas.size()];
        for (Sena s:senas) {
            res[i] = s.getDescripcion();
            i++;
        }

        spnSena.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,res));
    }

    private void armarListaSenas(String Resultado) {
        senas= new ArrayList<>();

        String[] filas, datos;

        Sena sena;

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {
                sena = new Sena();

                datos = filas[i].split(";");

                sena.setIdSena(Integer.parseInt(datos[0]));
                sena.setDescripcion(datos[1]);
                sena.setImagen(datos[2]);

                senas.add(sena);
            }
        }

    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }
}