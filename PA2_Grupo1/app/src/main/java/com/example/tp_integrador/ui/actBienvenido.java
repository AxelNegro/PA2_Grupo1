package com.example.tp_integrador.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tp_integrador.R;

public class actBienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }

    public void Redireccionar(View V){
        Intent Sig=new Intent(this, actRegistrarse.class);
        startActivity(Sig);
    }
}