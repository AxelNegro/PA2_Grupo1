package com.example.tp_integrador.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

public class actLogin extends AppCompatActivity {

    private EditText etNameUser, etKeyUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNameUser = (EditText)this.findViewById(R.id.txtNombreAlta);
        etKeyUser = (EditText)this.findViewById(R.id.txtKey);

    }

    public void RedirecRegistrarse(View V){
        Intent Sig=new Intent(this, actRegistrarse.class);
        startActivity(Sig);
    }

    public void RedirecPrincipal(View V){
        try {
            Intent Sig;
            if(!etNameUser.getText().toString().isEmpty() && !etKeyUser.getText().toString().isEmpty()){

                Usuario User = new Usuario();
                User.setNameUser(etNameUser.getText().toString());
                UsuarioDao usrdao = new UsuarioDao(V,User,3);
                String[] datos = usrdao.execute().get().split(";");

                if(datos.length > 1){
                    if(datos[3].equals(etKeyUser.getText().toString())){
                        if(datos[4].equals("0")){
                            Sig = new Intent(this, navCliente.class);
                            SharedPreferences prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("username", etNameUser.getText().toString());
                            editor.putString("key", etKeyUser.getText().toString());
                            editor.commit();
                        }else{
                            Sig = new Intent(this, navAdmin.class);
                            SharedPreferences prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("username", etNameUser.getText().toString());
                            editor.putString("key", etKeyUser.getText().toString());
                            editor.commit();
                        }


                    }else{
                        Toast.makeText(this,"Contraseña invalida",Toast.LENGTH_SHORT).show();
                        etKeyUser.setError("Contraseña invalida");
                        return;
                    }
                }else{
                    Toast.makeText(this,"Usuario invalido",Toast.LENGTH_SHORT).show();
                    etNameUser.setError("Usuario invalido");
                    return;
                }
            }else{
                Toast.makeText(this,"Debe ingresar ambos campos",Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(Sig);
        }catch (Exception e){
             e.printStackTrace();
        }
    }
}
