package com.example.tp_integrador.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

public class actLogin extends AppCompatActivity {

    private EditText txtNameUser, txtKeyUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtNameUser = (EditText)this.findViewById(R.id.txtNombre);
        txtKeyUser = (EditText)this.findViewById(R.id.txtKey);

    }

    public void RedirecRegistrarse(View V){
        Intent Sig=new Intent(this, actRegistrarse.class);
        startActivity(Sig);
    }

    public void RedirecPrincipal(View V){
        try {
            String userName =  txtNameUser.getText().toString();
            String userPassword = txtKeyUser.getText().toString();

            Intent Sig;
            //hardcodeado para ver vistas
            if(!userName.isEmpty() && !userPassword.isEmpty()){
                if(userName.equals("admin") && userPassword.equals("1234")){
                    Sig = new Intent(this, navAdmin.class);
                    SharedPreferences prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", userName);
                    editor.putString("key", userPassword);
                    editor.commit();
                }else{
                    Sig = new Intent(this, navCliente.class);
                    SharedPreferences prefs = getSharedPreferences("login_data",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", userName);
                    editor.putString("key", userPassword);
                    editor.commit();
                }
            }else{
                Toast.makeText(this,"Debe ingresar ambos campos",Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(Sig);
        }catch (Exception e){

        }

    }
}
