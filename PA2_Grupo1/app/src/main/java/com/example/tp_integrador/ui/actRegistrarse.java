package com.example.tp_integrador.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;

public class actRegistrarse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }
    public void RedirecLogin(View V){
        Intent Sig=new Intent(this, actLogin.class);
        startActivity(Sig);
    }
}
