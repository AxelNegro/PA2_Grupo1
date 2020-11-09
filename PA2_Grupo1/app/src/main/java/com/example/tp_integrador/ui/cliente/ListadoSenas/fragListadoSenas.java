package com.example.tp_integrador.ui.cliente.ListadoSenas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.adapters.ConsignaAdapter;
import com.example.tp_integrador.entidad.adapters.SenasAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Sena;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.ArrayList;
import java.util.List;

public class fragListadoSenas extends Fragment {

    private ListView listaSenas;
    private vmListadoSenas vmListadoSenas;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listadosenas, container, false);

        listaSenas = (ListView) root.findViewById(R.id.listaSenas);

        //Instancia la actividad main
        ((navCliente)getActivity()).setFragmentRefreshListener(new navCliente.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerInfo();
            }
        });
        obtenerInfo();

        return root;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        SenasDao senaDao = new SenasDao(getContext(),4,this);
        senaDao.execute();
    }

    public void LlenarGD(String resultado){

        List<Sena> lstLvl = armarLista(resultado);

        SenasAdapter adapter = new SenasAdapter(getContext(),R.layout.gdlistado_senas,lstLvl);
        listaSenas.setAdapter(adapter);
    }

    //Carga los articulos a una lista
    public List<Sena> armarLista(String Resultado){

        List<Sena> lstSenas = new ArrayList<Sena>();

        String res;
        String[] filas, datos;

        //Crea objetos de Sena
        Sena con = new Sena();

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {
            res = Resultado.substring(0, Resultado.length() - 1);

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {

                datos = filas[i].split(";");

                con.setIdSena(Integer.parseInt(datos[0]));
                con.setNombreSena(datos[1]);
                con.setImagen(datos[2]);
                con.setDescripcion(datos[3]);



                lstSenas.add(con);

                con= new Sena();
            }
        }
        else{
            lstSenas = null;
        }

        return lstSenas;
    }
}