package com.example.tp_integrador.ui.cliente.CA;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.entidad.adapters.NivelesxUsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.NivelesxUsuario;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.ArrayList;
import java.util.List;

public class fragCA extends Fragment {

    private GridView gdNivel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ca, container, false);

        ((navCliente)getActivity()).setFragmentRefreshListener(new navCliente.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerInfo();
            }
        });

        gdNivel = (GridView)v.findViewById(R.id.gdNiveles);

        obtenerInfo();

        return v;
    }

    public void obtenerInfo(){
        Usuario user = new Usuario();

        SharedPreferences prefs = getActivity().getSharedPreferences("login_data", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");

        user.setNameUser(username);

        NivelDao nivelDao = new NivelDao(getContext(),5,user, this);
        nivelDao.execute();
    }

    public void llenarGD(String resultado){
        List<NivelesxUsuario> lstNivelesxUsuario = armarLista(resultado);

        NivelesxUsuarioAdapter adapter = new NivelesxUsuarioAdapter(getContext(),lstNivelesxUsuario, this);
        gdNivel.setAdapter(adapter);

    }

    private List<NivelesxUsuario> armarLista(String resultado){
        Nivel niv = new Nivel();
        NivelesxUsuario nivxus = new NivelesxUsuario();
        List<NivelesxUsuario> lstNivelesxUsuario = new ArrayList<NivelesxUsuario>();

        String [] filas, datos;

        if(!resultado.isEmpty()){

            filas = resultado.split("\\|");
            for(int i = 0;i<filas.length;i++){
                datos = filas[i].split(";");

                niv.setIdNivel(Integer.parseInt(datos[0]));
                niv.setNivel(datos[1]);
                if(datos[2].equals("1")){
                    nivxus.setEstado(true);
                }else{
                    nivxus.setEstado(false);
                }

                nivxus.setNivel(niv);

                lstNivelesxUsuario.add(nivxus);

                niv = new Nivel();
                nivxus = new NivelesxUsuario();
            }
        }

        return lstNivelesxUsuario;
    }
}