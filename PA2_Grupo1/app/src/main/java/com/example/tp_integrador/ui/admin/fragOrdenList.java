package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.OrdenDao;
import com.example.tp_integrador.entidad.adapters.ConsignaAdapter;
import com.example.tp_integrador.entidad.adapters.OrdenAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class fragOrdenList extends Fragment {

    private GridView gdOrden;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_orden_list, container, false);

        gdOrden = (GridView)v.findViewById(R.id.gdOrden);

        ((navAdmin)getActivity()).setFragmentRefreshListener(new navAdmin.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerInfo();
            }
        });

        obtenerInfo();

        return v;
    }

    public void obtenerInfo(){
        OrdenDao artDao = new OrdenDao(getContext(),5,this);
        artDao.execute();
    }

    public void llenarGD(String resultado){
        List<Orden> lstOrden = new ArrayList<>();
        if(!resultado.isEmpty())
            lstOrden = armarLista(resultado);

        OrdenAdapter adapter = new OrdenAdapter(getContext(),lstOrden,this);
        gdOrden.setAdapter(adapter);
    }

    //Carga los articulos a una lista
    public List<Orden> armarLista(String Resultado){
        List<Orden> lstOrden = new ArrayList<Orden>();

        String[] filas, datos;

        Nivel niv = new Nivel();
        Sena sen = new Sena();
        Consigna con = new Consigna();
        Orden ord = new Orden();

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {
                datos = filas[i].split(";");

                niv.setIdNivel(Integer.parseInt(datos[1]));

                if(!datos[2].isEmpty()){
                    sen.setIdSena(Integer.parseInt(datos[2]));
                }else{
                    sen = null;
                }

                if(!datos[3].isEmpty()){
                    con.setIdConsigna(Integer.parseInt(datos[3]));
                }else{
                    con = null;
                }

                ord.setIdOrden(Integer.parseInt(datos[0]));
                ord.setNivel(niv);
                ord.setSena(sen);
                ord.setConsigna(con);
                ord.setOrden(Integer.parseInt(datos[4]));
                if(datos[5].equals("1")){
                    ord.setEstado(true);
                }
                else{
                    ord.setEstado(false);
                }

                lstOrden.add(ord);

                niv = new Nivel();
                sen = new Sena();
                con = new Consigna();
                ord = new Orden();
            }
        }
        else{
            lstOrden = null;
        }

        return lstOrden;
    }

    public void mostrarBaja(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_LONG).show();
        obtenerInfo();
    }
}