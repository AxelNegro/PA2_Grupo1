package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.OpcionAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Opcion;
import com.example.tp_integrador.entidad.Dao.UsuarioDao;
import com.example.tp_integrador.entidad.Dao.UsuarioDao;

import com.example.tp_integrador.entidad.adapters.UsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class fragUsuarioList extends Fragment {

    private GridView gdUsuarios;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Trae el listView de la vista
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_adm_usuarios_list, container, false);

        LlenarGD(v);

        return v;
    }

    public void LlenarGD(View v){
        gdUsuarios = (GridView)v.findViewById(R.id.gdUsuarios);
        List<Usuario> lstUsrs = armarLista();

        UsuarioAdapter adapter = new UsuarioAdapter(v.getContext(),lstUsrs);
        gdUsuarios.setAdapter(adapter);
    }

    //Carga los articulos a una lista
    public List<Usuario> armarLista(){

        List<Usuario> lstUser = new ArrayList<Usuario>();

        /*String res;
        String[] filas, datos;

        //Crea objetos de Articulo y Categoria
        Usuario User = new Usuario();

        //Utiliza el metodo substring para separar los datos
        if(Resultado!=null && !Resultado.isEmpty()) {
            res = Resultado.substring(0, Resultado.length() - 1);

            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {

                datos = filas[i].split(";");

                User.setEmail(datos[1]);
                User.setNombre(datos[1]);
                User.setNameUser(datos[1]);

                lstUser.add(User);

                User= new Usuario();
            }
        }
        else{
            lstUser = null;
        }

        return lstUser;*/

        Usuario user = new Usuario();
        lstUser.add(user);

        user = new Usuario();
        lstUser.add(user);

        user = new Usuario();
        lstUser.add(user);

        user= new Usuario();
        lstUser.add(user);

        user = new Usuario();
        lstUser.add(user);

        user= new Usuario();
        lstUser.add(user);

        user = new Usuario();
        lstUser.add(user);

        user = new Usuario();
        lstUser.add(user);

        return lstUser;
    }
}