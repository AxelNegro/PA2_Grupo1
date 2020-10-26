package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragUsuarioMyB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragUsuarioMyB extends Fragment {

    private EditText txtsearchId, txtNombre, txtStock;
    private Spinner spCategorias;
    Usuario User;

    public static final String TITLE = "ModificacionUsuario";

    public static fragUsuarioMyB newInstance() {

        return new fragUsuarioMyB();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_adm_usuario_mb,container,false);

        return view;
    }

    private void CargarDatos(EditText txtsearchId, View view) {
        User=new Usuario();
        UsuarioDao x = new UsuarioDao(getContext(),User,3,this);
        x.execute();
    }
    
    public void limpiar(){
        txtsearchId.setText("");
        txtNombre.setText("");
        txtStock.setText("");
        spCategorias.setSelection(0);
    }

    public void setearDatos(String[] datos) {
        if(datos.length != 0) {
            txtNombre.setText(datos[0]);
            txtStock.setText(datos[1]);
            spCategorias.setSelection(Integer.parseInt(datos[2]) - 1);
        }
        else{
            Toast.makeText(getContext(),"No existe un articulo con el ID ingresado.",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }
}