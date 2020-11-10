package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.entidad.adapters.ConsignaAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;

import java.util.ArrayList;
import java.util.List;

public class fragConsignasList extends Fragment {

    private GridView gdConsignas;
    private Spinner spnNivel;
    private fragConsignasList fragConsignasList;
    private List<Nivel> niveles;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adm_consignas_list, container, false);

        fragConsignasList = this;
        gdConsignas = (GridView)  view.findViewById(R.id.gdConsignas);
        spnNivel = (Spinner) view.findViewById(R.id.spnIdNivel);

        NivelDao nivelDao = new NivelDao(getContext(),6,this);
        nivelDao.execute();

        //Instancia la actividad main
        ((navAdmin)getActivity()).setFragmentRefreshListener(new navAdmin.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerInfo();
            }
        });

        spnNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ConsignaDao artDao = new ConsignaDao(getContext(),5, niveles.get(i).getIdNivel(),fragConsignasList);
                artDao.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        ConsignaDao artDao = new ConsignaDao(getContext(),4,this);
        artDao.execute();
    }

    public void llenarGD(String resultado){
        List<Consigna> lstConsignas = new ArrayList<>();
        if(!resultado.isEmpty())
            lstConsignas = armarLista(resultado);

        ConsignaAdapter adapter = new ConsignaAdapter(getContext(),lstConsignas,this);
        gdConsignas.setAdapter(adapter);
    }

    //Carga los articulos a una lista
    public List<Consigna> armarLista(String Resultado){

        List<Consigna> lstConsignas = new ArrayList<Consigna>();

        String res;
        String[] filas, datos;

        //Crea objetos de Articulo y Categoria
        Consigna con = new Consigna();

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {
            res = Resultado.substring(0, Resultado.length() - 1);

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {

                datos = filas[i].split(";");

                con.setIdConsigna(Integer.parseInt(datos[0]));
                con.setURLImagen(datos[1]);
                con.setDesc(datos[2]);
                if(datos[3]=="true"){
                    con.setEstado(true);
                }
                else{
                    con.setEstado(false);
                }


                lstConsignas.add(con);

                con= new Consigna();
            }
        }
        else{
            lstConsignas = null;
        }

        return lstConsignas;
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

    public void mostrarBaja(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_LONG).show();
    }
}