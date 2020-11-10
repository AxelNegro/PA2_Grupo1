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

public class fragConsignasMod extends Fragment {
    private TextView descripcion;
    private Spinner spnNivel, spnConsigna, spnSena;
    private Button modificar;
    private List<Nivel> niveles;
    private List<Sena> senas;
    private List<Consigna> consignas;
    private fragConsignasMod fragConsignasMod;

    private Consigna consigna;
    private Sena sena;

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
        fragConsignasMod = this;

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarConsigna();
            }
        });

        //Llena el spinner de niveles
        NivelDao nivelDao = new NivelDao(getContext(),5,this);
        nivelDao.execute();

        spnNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Llena el spinner de señas con el nivel seleccionado
                SenasDao senasDao = new SenasDao(getContext(),6,fragConsignasMod, niveles.get(i));
                senasDao.execute();

                //llena el spinner de consignas con el nivel seleccionado
                ConsignaDao consignaDao = new ConsignaDao(getContext(),6,niveles.get(i).getIdNivel(),fragConsignasMod);
                consignaDao.execute();

                descripcion.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }

    private void modificarConsigna(){
        obtenerDatos();

        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,2,sena.getIdSena(), this);
        consignaDao.execute();

    }



    private void obtenerDatos(){
        Nivel nivel = new Nivel();
        consigna.setNivel(nivel);
        consigna.setDesc(descripcion.getText().toString());
        consigna.setURLImagen(sena.getImagen());
    }

    public void limpiar() {
        descripcion.setText("");
    }

    public void llenarSpinnerNivel(String resultado) {
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

        spnSena.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sena = senas.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                sena.setDescripcion(datos[3]);
                sena.setImagen(datos[2]);

                senas.add(sena);
            }
        }

    }

    public void llenarSpinnerConsignas(String Resultado) {
        armarListaConsignas(Resultado);

        int i=0;
        String[] res = new String[consignas.size()];
        for (Consigna c : consignas) {
            res[i] = c.getDesc();;
            i++;
        }

        spnConsigna.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,res));

        spnConsigna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                descripcion.setText(consignas.get(i).getDesc());
                consigna = consignas.get(i);
                int j=0;
                for (Sena s: senas) {
                    if (s.getImagen().equals(consignas.get(i).getURLImagen())){
                        spnSena.setSelection(j);
                    }
                    j++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void armarListaConsignas(String Resultado){
        consignas= new ArrayList<>();

        String[] filas, datos;

        Consigna consigna;
        Nivel nivel;

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {
                consigna = new Consigna();
                nivel = new Nivel();


                datos = filas[i].split(";");

                consigna.setNivel(nivel);
                consigna.setIdConsigna(Integer.parseInt(datos[0]));
                consigna.setURLImagen(datos[1]);
                consigna.setDesc(datos[2]);

                consignas.add(consigna);
            }
        }


    }
}