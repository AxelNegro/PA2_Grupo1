package com.example.tp_integrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void RedirecRegistrarse(View V){
        Intent Sig=new Intent(this, registrarse.class);
        startActivity(Sig);
    }
}
