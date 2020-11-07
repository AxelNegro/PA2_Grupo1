package com.example.tp_integrador.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;


public class actRegistrarse extends AppCompatActivity {
    private EditText etNombre,etApellido,etUsuario,etKey,etEmail,etConfirmkey;
    private CheckBox chkViewKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        etUsuario=(EditText) findViewById(R.id.txtUserName);
        etEmail=(EditText) findViewById(R.id.txtEmail);
        etNombre=(EditText) findViewById(R.id.txtNombreAlta);
        etApellido=(EditText) findViewById(R.id.txtApellido);
        etKey=(EditText) findViewById(R.id.txtKey);
        etConfirmkey=(EditText) findViewById(R.id.txtConfirmKey);
        chkViewKeys=(CheckBox) findViewById(R.id.chkbViewKeys);
    }

    public void registrarUsuario(View v) throws ExecutionException, InterruptedException {
        //Instancia el Dao de Usuario
        UsuarioDao userDao;
        //Instancia un Objeto de Usuario y trae los datos a guardar con el metodo obtenerDatos();
        Usuario user = obtenerDatos();

        //Envia a UsuarioDao el contexto, el objeto a guardar y un int que indica la accion que se debe ejecutar
        if(user != null){
            userDao = new UsuarioDao(this,user,1);
            if (userDao.execute().get().equals("Usuario registrado correctamente.")) limpiar();
        }
    }

    public Usuario obtenerDatos() throws ExecutionException, InterruptedException {
        Usuario user = null;
        if ( !(etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() ||
                etEmail.getText().toString().isEmpty() || etUsuario.getText().toString().isEmpty() ||
                etKey.getText().toString().isEmpty() || etConfirmkey.getText().toString().isEmpty()) ){
            //Trae los datos de los txt que carga el usuario por pantalla
            user = new Usuario();
            if(validarEmail(etEmail.getText().toString())) {
                if(validarKey(etKey.getText().toString(),etConfirmkey.getText().toString())) {
                    user.setNameUser(etUsuario.getText().toString());
                    user.setNombre(etNombre.getText().toString());
                    user.setApellido(etApellido.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setKeyUser(etKey.getText().toString());
                    user.setTipo_Cuenta(1);
                    user.setEstado(true);
                }else{
                    Toast.makeText(this,"Las claves no coinciden.",Toast.LENGTH_LONG).show();
                    etKey.setError("Las claves no coinciden.");
                    etConfirmkey.setError("Las claves no coinciden.");
                }
            }else {
                Toast.makeText(this,"Email invalido",Toast.LENGTH_LONG).show();
                etEmail.setError("verifique el email ingresado");
            }
        }
        else Toast.makeText(this,"Debe completar todos los campos.",Toast.LENGTH_LONG).show();

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

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void ShowPassword(View v) {
        if(!chkViewKeys.isChecked()){
            etKey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etConfirmkey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }else{
            etKey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            etConfirmkey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
    }
}
