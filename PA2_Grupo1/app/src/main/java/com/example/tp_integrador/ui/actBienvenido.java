package com.example.tp_integrador.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

public class actBienvenido extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int x=0;
        //creamos el obj  SharedPreferences para guardar datos persistentes en dispositivo
        SharedPreferences prefs = getSharedPreferences("VersionApp", Context.MODE_PRIVATE);
        SharedPreferences prefs2 = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        //con Editor podemos cargar un valor a la variable
        SharedPreferences.Editor editor = prefs.edit();
        super.onCreate(savedInstanceState);
        //si la version de la app es cero implica que se inicia por primera ves luego de ser instalada
        if (prefs.getString("version", "0").equals("0")){
            setContentView(R.layout.activity_bienvenido);
            //si no es la primera ves que se inicia se derivara al nav correspondiente al usuario  guardado en sharedpreferences login_data
        } else if(!(prefs2.getString("tc", "").equals(""))){
            String tc = prefs2.getString("tc", "");
            Intent Sig;
            if (tc.equals("0")) Sig = new Intent(this, navCliente.class);
            else Sig = new Intent(this, navAdmin.class);
            startActivity(Sig);
        }else{
            Intent Sig=new Intent(this, actLogin.class);
            startActivity(Sig);
        }
        //se aumenta en 1 la version
        int z = Integer.valueOf(prefs.getString("version", "0"))+1;
        editor.putString("version",String.valueOf(z));
        //se hace el commit de las modificaciones para guardar de manera permanente en el dispositivo
        editor.commit();
    }

    //redireecciona a login
    public void Redireccionar(View V){
        Intent Sig=new Intent(this, actRegistrarse.class);
        startActivity(Sig);
    }
}