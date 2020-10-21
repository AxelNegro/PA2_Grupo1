package com.example.tp_integrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class registrarse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);
    }
    public void RedirecLogin(View V){
        Intent Sig=new Intent(this, login.class);
        startActivity(Sig);
    }
}
