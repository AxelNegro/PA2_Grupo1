package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.Dao.UsuarioDao;
import com.example.tp_integrador.entidad.adapters.UsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListadoUsuarios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoUsuarios extends Fragment {

    ListView listUsuarios;

    public static final String TITLE = "ListadoUsuario";

    public static ListadoUsuarios newInstance() {

        return new ListadoUsuarios();
    }

    //Trae el listView de la vista
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_listado_usuarios, container, false);

        listUsuarios = (ListView) v.findViewById(R.id.listUsuarios);

        //Instancia la actividad main
        ((fragUsuarioMod)getActivity()).setFragmentRefreshListener(new fragUsuarioMod.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                //carga la lista de usuarios
                obtenerInfo();
            }
        });
        //carga la lista de usuarios
        obtenerInfo();
        return v;
    }

    //Trae la informacion de la base
    public void obtenerInfo(){
        UsuarioDao UserDao = new UsuarioDao(getContext(),4,this);
        UserDao.execute();
    }

    //Llena el listView
    public void llenarGD(String Resultado){

        fragUsuarioMod main = ((fragUsuarioMod)getActivity());

        //Carga la lista con articulos
        List<Usuario> lstUser = obtenerTodos(Resultado);

        //Carga el adapter
        if(lstUser!=null) {
            UsuarioAdapter adapter = new UsuarioAdapter(getContext(), lstUser, main);
            //Setea el adapter al gridView
            listUsuarios.setAdapter(adapter);
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