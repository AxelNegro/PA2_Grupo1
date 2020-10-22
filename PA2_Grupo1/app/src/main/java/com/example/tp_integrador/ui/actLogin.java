package com.example.tp_integrador.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;

public class actLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void RedirecRegistrarse(View V){
        Intent Sig=new Intent(this, actRegistrarse.class);
        startActivity(Sig);
    }

    public void RedirecPrincipal(View V){
        Intent Sig=new Intent(this, navPrincipal.class);
        startActivity(Sig);
    }
}
