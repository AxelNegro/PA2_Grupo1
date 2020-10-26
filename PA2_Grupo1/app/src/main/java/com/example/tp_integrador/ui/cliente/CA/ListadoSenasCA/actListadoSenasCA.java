package com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.SenasAdapter;
import com.example.tp_integrador.entidad.adapters.SenasCaAdapter;
import com.example.tp_integrador.ui.actRegistrarse;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.ArrayList;

public class actListadoSenasCA extends AppCompatActivity {

    private ListView listaSenasCa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadosenas_ca);

        listaSenasCa = (ListView) findViewById(R.id.listaSenasCA);
        ArrayList<String> lstLvl = armarLista();

        SenasCaAdapter adapter = new SenasCaAdapter(this,R.layout.gdlistado_senas_ca,lstLvl);
        listaSenasCa.setAdapter(adapter);
    }

    public ArrayList<String> armarLista(){
        ArrayList<String> lstNivel = new ArrayList<String>();

        lstNivel.add("Azul");
        lstNivel.add("Blanco");
        lstNivel.add("Rojo");
        lstNivel.add("Negro");
        lstNivel.add("Violeta");



        return lstNivel;
    }

    public void Volver(View V){
        Intent Sig=new Intent(this, navCliente.class);
        startActivity(Sig);
    }

}
