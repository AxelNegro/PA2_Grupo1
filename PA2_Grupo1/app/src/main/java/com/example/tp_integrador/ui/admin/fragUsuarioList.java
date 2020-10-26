package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.dao.UsuarioDao;
import com.example.tp_integrador.entidad.adapters.UsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragUsuarioList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragUsuarioList extends Fragment {

    GridView gdUsuarios;

    public static final String TITLE = "ListadoUsuario";

    public static fragUsuarioList newInstance() {

        return new fragUsuarioList();
    }

    //Trae el listView de la vista
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_adm_usuario_list, container, false);

        gdUsuarios = (GridView) v.findViewById(R.id.gdUsuarios);

        //carga la lista de usuarios
        obtenerInfo();
        return v;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        UsuarioDao UserDao = new UsuarioDao(getContext(),4);
        UserDao.execute();
    }

    //Llena el GridView
    public void llenarGD(String Resultado){

        //Carga la lista con articulos
        List<Usuario> lstUser = obtenerTodos(Resultado);

        //Carga el adapter
        if(lstUser!=null) {
            UsuarioAdapter adapter = new UsuarioAdapter(getContext(), lstUser);
            //Setea el adapter al gridView
            gdUsuarios.setAdapter(adapter);
        }
    }

    //Carga los articulos a una lista
    public List<Usuario> obtenerTodos(String Resultado){

        List<Usuario> lstUser = new ArrayList<Usuario>();

        String res;
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

        return lstUser;
    }
}