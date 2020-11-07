package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.OpcionAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Opcion;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.dao.UsuarioDao;

import com.example.tp_integrador.entidad.adapters.UsuarioAdapter;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class fragUsuarioList extends Fragment {

    private GridView gdUsuarios;
    private EditText etSearchUsuario;
    private List<Usuario> lstUsrs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Trae el listView de la vista
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_adm_usuarios_list, container, false);

        gdUsuarios = (GridView) v.findViewById(R.id.gdUsuarios);
        etSearchUsuario = (EditText) v.findViewById(R.id.txtSearchUserName) ;

        /**------Boton Buscar-----**/
        Button botonBuscar = (Button) v.findViewById(R.id.btnBuscar);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarUsuario(v);
            }
        });

        //Instancia la actividad main
        ((navAdmin)getActivity()).setFragmentRefreshListener(new navAdmin.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                LlenarGD(v,obtenerInfo());
            }
        } );
        LlenarGD(v,obtenerInfo());
        return v;
    }

    public void LlenarGD(View v,String res){
        //gdUsuarios = (GridView)v.findViewById(R.id.gdUsuarios);
        lstUsrs = armarLista(res);

        UsuarioAdapter adapter = new UsuarioAdapter(v.getContext(),lstUsrs);
        gdUsuarios.setAdapter(adapter);
    }

    public Usuario verificarUsuario(){
        for (Usuario reg :lstUsrs ) if(reg.getNameUser().equals(etSearchUsuario.getText().toString()))return reg;
        return null;
    }

    public void listarUsuario(View v){
        if(!etSearchUsuario.getText().toString().isEmpty()){
           Usuario x = verificarUsuario();
            if( x != null){
                LlenarGD(v,x.toString());
            }else etSearchUsuario.setError("El usuario igresado no existe.");
        }else{
            etSearchUsuario.setError("Ingrese un usuario.");
            LlenarGD(v,obtenerInfo());
        }
    }

    //Trae la informacion de la base
    public String obtenerInfo(){
        try {
            UsuarioDao userDao = new UsuarioDao(getContext(),4);
            return userDao.execute().get();
        }catch (Exception e){
            Log.d("BBDD","Error al traer datos de la bd");
            return null;
        }
    }

    //Carga los articulos a una lista
    public List<Usuario> armarLista(String Resultado){
        List<Usuario> lstUser = null;
        String[] filas, datos;
        //Crea objetos de usuario
        Usuario User= null;

        //Utiliza el metodo substring para separar los datos
        if( Resultado != null && ! Resultado.isEmpty() ) {
            User=new Usuario();
            lstUser=new ArrayList<Usuario>();
            //Con el metodo split divide
            filas = Resultado.split("\\|");

            for (int i = 0; i < filas.length; i++) {
                datos = filas[i].split(";");
                User.setNameUser(datos[0]);
                User.setKeyUser(datos[1]);
                User.setNombre(datos[2]);
                User.setApellido(datos[3]);
                User.setEmail(datos[4]);
                User.setEstado(Boolean.parseBoolean(datos[5]));
                User.setTipo_Cuenta(Integer.valueOf(datos[6]));

                lstUser.add(User);
                User= new Usuario();
            }
        }
        return lstUser;
    }
}