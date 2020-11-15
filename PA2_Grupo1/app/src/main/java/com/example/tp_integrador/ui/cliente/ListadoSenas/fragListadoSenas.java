package com.example.tp_integrador.ui.cliente.ListadoSenas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.adapters.ConsignaAdapter;
import com.example.tp_integrador.entidad.adapters.SenasAdapter;
import com.example.tp_integrador.entidad.adapters.UsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Sena;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.ArrayList;
import java.util.List;

public class fragListadoSenas extends Fragment {

    private GridView listaSenas;
    private vmListadoSenas vmListadoSenas;
    private EditText etSearchSena;
    private List<Sena> lstSenas;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_listadosenas, container, false);

        listaSenas = (GridView) root.findViewById(R.id.listaSenas);
        etSearchSena = (EditText) root.findViewById(R.id.etSearchSena);

        /**------Boton Buscar Seña-----**/
        Button botonBuscar = (Button) root.findViewById(R.id.btnBuscarSena);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    listarSenas(v);
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });

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

    private void listarSenas(View v) {
        if(!etSearchSena.getText().toString().isEmpty()){
            List<Sena> x=LLenarList();
            if( x != null && x.size() > 0){
                LlenarGD(v,x);
            }else etSearchSena.setError("no existen señas que de acuerdo a su busqueda.");
        }else{
            etSearchSena.setError("Ingrese una palabra o parte de ella.");
            obtenerInfo();
        }
    }

    private List<Sena> LLenarList() {
        List<Sena> x = new ArrayList<>();
            for (Sena reg :lstSenas) {
                if(reg.getNombreSena().contains(etSearchSena.getText().toString())){
                    x.add(reg);
                }
            }
            return x;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        SenasDao senaDao = new SenasDao(getContext(),4,this);
        senaDao.execute();
    }

    public void LlenarGD(String resultado){
        lstSenas = armarLista(resultado);

        SenasAdapter adapter = new SenasAdapter(getContext(),R.layout.gdlistado_senas,lstSenas);
        listaSenas.setAdapter(adapter);
    }

    public void LlenarGD(View v,List<Sena> x){
            SenasAdapter adapter = new SenasAdapter(v.getContext(), x);
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