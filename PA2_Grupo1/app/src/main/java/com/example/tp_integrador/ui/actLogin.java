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
                User.setKeyUser(etKeyUser.getText().toString());
                UsuarioDao usrdao = new UsuarioDao(V,User,7);
                String resultado = usrdao.execute().get();

                if(resultado.contains(";")&&resultado.contains("|")) {
                    resultado = resultado.substring(0,resultado.length() - 1);
                    String[] datos = resultado.split(";");

                    if (datos.length > 1) {
                        if (datos[1].equals("0")) {
                            SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.putString("username", etNameUser.getText().toString());
                            editor.putString("key", etKeyUser.getText().toString());
                            editor.putString("email", datos[0]);
                            editor.putString("tc", datos[1]);
                            editor.commit();

                            Sig = new Intent(this, navCliente.class);
                        } else {

                            SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.putString("username", etNameUser.getText().toString());
                            editor.putString("key", etKeyUser.getText().toString());
                            editor.putString("email", datos[0]);
                            editor.putString("tc", datos[1]);
                            editor.commit();

                            Sig = new Intent(this, navAdmin.class);
                        }
                    }else{
                        Toast.makeText(this, "Hubo un error al obtener al usuario.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
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
