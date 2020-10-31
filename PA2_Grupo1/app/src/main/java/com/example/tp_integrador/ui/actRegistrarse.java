package com.example.tp_integrador.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;

public class actRegistrarse extends AppCompatActivity {
    private EditText etNombre,etApellido,etUsuario,etKey,etEmail,etConfirmkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        etUsuario=(EditText) findViewById(R.id.txtUserName);
        etEmail=(EditText) findViewById(R.id.txtEmail);
        etNombre=(EditText) findViewById(R.id.txtNombre);
        etApellido=(EditText) findViewById(R.id.txtApellido);
        etKey=(EditText) findViewById(R.id.txtKey);
        etConfirmkey=(EditText) findViewById(R.id.txtConfirmKey);
    }

    public void registrarUsuario(View v){
        //Instancia el Dao de Usuario
        UsuarioDao userDao;
        //Instancia un Objeto de Usuario y trae los datos a guardar con el metodo obtenerDatos();
        Usuario user = obtenerDatos();

        //Envia a UsuarioDao el contexto, el objeto a guardar y un int que indica la accion que se debe ejecutar
        if(user != null){
            userDao = new UsuarioDao(this,user,1);
            userDao.execute();
            limpiar();
        }
        else{
            Toast.makeText(this,"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
        }
    }

    public Usuario obtenerDatos() {
        //Trae los datos de los txt que carga el usuario por pantalla
        Usuario user = new Usuario();

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String email = etEmail.getText().toString();
        String usuario = etUsuario.getText().toString();
        String key = etKey.getText().toString();
        String ck = etConfirmkey.getText().toString();

        if (!(nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || usuario.isEmpty() || key.isEmpty() || ck.isEmpty())){
            if(validarKey(key,ck)) {
                user.setNombre(nombre);
                user.setApellido(apellido);
                user.setEmail(email);
                user.setNameUser(usuario);
                user.setKeyUser(key);
                user.setTipo_Cuenta(1);
                user.setEstado(true);
            }else {
                Toast.makeText(this,"Las claves no coinciden.",Toast.LENGTH_LONG).show();
                user = null;
            }
        }
        else{
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
        etUsuario.setText("");
        etKey.setText("");
        etConfirmkey.setText("");
    }

    public void RedirecLogin(View V){
        Intent Sig=new Intent(this, actLogin.class);
        startActivity(Sig);
    }
}
