package com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.OrdenDao;
import com.example.tp_integrador.dao.OrdenxUsuarioDAO;
import com.example.tp_integrador.entidad.adapters.OrdenAdapter;
import com.example.tp_integrador.entidad.adapters.OrdenxUsuarioAdapter;
import com.example.tp_integrador.entidad.adapters.SenasAdapter;
import com.example.tp_integrador.entidad.adapters.SenasCaAdapter;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.NivelesxUsuario;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.entidad.clases.OrdenxUsuario;
import com.example.tp_integrador.entidad.clases.Sena;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.actRegistrarse;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.ArrayList;

public class actListadoSenasCA extends AppCompatActivity {

    private GridView gdEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadosenas_ca);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSenas);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Ejercicios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        gdEjercicios  = (GridView)findViewById(R.id.gdEjercicios);

        obtenerInfo();
    }

    public void obtenerInfo(){
        OrdenxUsuario ordxus = new OrdenxUsuario();
        Orden ord = new Orden();
        Nivel nivel = new Nivel();
        Usuario usuario = new Usuario();

        SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");

        prefs = getSharedPreferences("nivel", Context.MODE_PRIVATE);
        String idnivel = prefs.getString("idNivel","");

        try {
            nivel.setIdNivel(Integer.parseInt(idnivel));
            usuario.setNameUser(username);

            ord.setNivel(nivel);

            ordxus.setOrden(ord);
            ordxus.setUsuario(usuario);

            OrdenxUsuarioDAO ordxusDao = new OrdenxUsuarioDAO(this, ordxus, 1, this);
            ordxusDao.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void llenarGD(String resultado){
        ArrayList<OrdenxUsuario> lstOrdxUs = new ArrayList<OrdenxUsuario>();
        if(!resultado.isEmpty()) {
            lstOrdxUs = armarLista(resultado);
        }

        OrdenxUsuarioAdapter adapter = new OrdenxUsuarioAdapter(this,lstOrdxUs, this);
        gdEjercicios.setAdapter(adapter);
    }

    public ArrayList<OrdenxUsuario> armarLista(String resultado){
        ArrayList<OrdenxUsuario> lstOrdxUs = new ArrayList<OrdenxUsuario>();

        Sena sena = new Sena();
        Consigna con = new Consigna();
        Orden ord = new Orden();
        OrdenxUsuario ordxus = new OrdenxUsuario();

        String [] filas, datos;

        if(!resultado.isEmpty()){

            filas = resultado.split("\\|");
            for(int i = 0;i<filas.length;i++){
                datos = filas[i].split(";");

                if(!datos[2].isEmpty()){
                    sena.setIdSena(Integer.parseInt(datos[2]));
                    sena.setNombreSena(datos[3]);
                }else{
                    sena = null;
                }

                if(!datos[1].isEmpty()){
                    con.setIdConsigna(Integer.parseInt(datos[1]));
                }else{
                    con = null;
                }

                ord.setIdOrden(Integer.parseInt(datos[0]));

                if(datos[4].equals("1")){
                    ordxus.setEstado(true);
                }else{
                    ordxus.setEstado(false);
                }

                ord.setConsigna(con);
                ord.setSena(sena);

                ordxus.setOrden(ord);


                lstOrdxUs.add(ordxus);

                sena = new Sena();
                con = new Consigna();
                ord = new Orden();
                ordxus = new OrdenxUsuario();
            }
        }

        return lstOrdxUs;
    }



}
