package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.regex.Pattern;

public class fragUsuarioMyB extends Fragment {

    private EditText etsearchusername, etNombre, etApellido,etEmail,etKey,etConfirmKey;
    private Usuario User;


    public static fragUsuarioMyB newInstance() {

        return new fragUsuarioMyB();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_adm_usuarios_mb,container,false);

        /**------TextViews-----**/
        etsearchusername = (EditText)view.findViewById(R.id.txtSearchUserName);
        etNombre = (EditText)view.findViewById(R.id.txtNombre);
        etApellido= (EditText)view.findViewById(R.id.txtApellido);
        etEmail=(EditText)view.findViewById(R.id.txtEmail);
        etKey = (EditText)view.findViewById(R.id.txtKey);
        etConfirmKey = (EditText)view.findViewById(R.id.txtConfirmKey);

        /**------Boton Buscar-----**/
        Button botonBuscar = (Button) view.findViewById(R.id.btnBuscarUsuario);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarDatos(etsearchusername,view);
            }
        });

        /**------Boton Modificar-----**/
        Button botonModificar = (Button) view.findViewById(R.id.btnModificarUsuario);
        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ModificarUsuario();
            }
        });

        return view;
    }

    private void CargarDatos(EditText txtsearchNameUser, View view) {
        User=new Usuario();
        User.setNameUser(txtsearchNameUser.getText().toString());
        UsuarioDao x = new UsuarioDao(getContext(),User,3,this);
        x.execute();
    }
    /*private void ModificarUsuario() {
        UsuarioDao UserDao;
        Usuario user = obtenerDatos();

        if(user != null){
            if(user.getId() != Integer.parseInt(txtsearchId.getText().toString())){
                Toast.makeText(getContext(),"Se modificó el id del artículo",Toast.LENGTH_SHORT).show();
                return;
            }
            UserDao = new UsuarioDao(getContext(),user,2, (MainActivity)getActivity());
            UserDao.execute();
            limpiar();
        }
        else{
            Toast.makeText(getContext(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
        }
    }*/

    public Usuario obtenerDatos() {
        //Trae los datos de los txt que carga el usuario por pantalla
        Usuario user = new Usuario();

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String email = etEmail.getText().toString();
        String usuario = etsearchusername.getText().toString();
        String key = etKey.getText().toString();
        String ck = etConfirmKey.getText().toString();

        if (!(nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || usuario.isEmpty() || key.isEmpty() || ck.isEmpty())){
            if(validarEmail(email)) {
                if(validarKey(key,ck)) {
                    user.setNameUser(usuario);
                    user.setNombre(nombre);
                    user.setApellido(apellido);
                    user.setEmail(email);
                    user.setKeyUser(key);
                    user.setTipo_Cuenta(1);
                    user.setEstado(true);
                }else{
                    Toast.makeText(getContext(),"Las claves no coinciden.",Toast.LENGTH_LONG).show();
                    etKey.setError("Las claves no coinciden.");
                    etConfirmKey.setError("Las claves no coinciden.");
                    user = null;
                }
            }else {
                Toast.makeText(getContext(),"Email invalido",Toast.LENGTH_LONG).show();
                etEmail.setError("verifique el email ingresado");
                user = null;
            }
        }
        else{
            Toast.makeText(getContext(),"Debe completar todos los campos.",Toast.LENGTH_LONG).show();
            user = null;
        }
        return user;
    }

    private boolean validarKey(String key, String ck) {
        if(key.equals(ck)) return true;
        return false;
    }

    public void limpiar(){
        etNombre.setText("");
        etApellido.setText("");
        etEmail.setText("");
        etsearchusername.setText("");
        etKey.setText("");
        etConfirmKey.setText("");
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    /*public void ShowPassword(View v) {
        if(!chkViewKeys.isChecked()){
            etKey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etConfirmkey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }else{
            etKey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            etConfirmkey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
    }*/

    public void setearDatos(String[] datos) {
        if(datos.length != 0) {
            etNombre.setText(datos[0]);
            etApellido.setText(datos[1]);
            etEmail.setText(datos[2]);
            etKey.setText(datos[3]);
            etConfirmKey.setText(datos[3]);
        }
        else{
            Toast.makeText(getContext(),"No existe un usuario con el USERNAME ingresado.",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }
}