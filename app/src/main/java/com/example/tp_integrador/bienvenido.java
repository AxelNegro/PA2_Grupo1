package com.example.tp_integrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenido);
    }

    public void Redireccionar(View V){
        Intent Sig=new Intent(this, registrarse.class);
        startActivity(Sig);
    }
}