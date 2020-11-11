package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.dao.OrdenDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class fragOrdenAlta extends Fragment {

    private Spinner ddlNivel, ddlTipo, ddlSena;
    private TextView txtId, txtOrden;
    private Button btnAlta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_orden_alta, container, false);

        ddlNivel = (Spinner)v.findViewById(R.id.ddlNivel);
        ddlTipo = (Spinner)v.findViewById(R.id.ddlTipo);
        ddlSena = (Spinner)v.findViewById(R.id.ddlSena);

        txtId = (TextView)v.findViewById(R.id.txtId);
        txtOrden = (TextView)v.findViewById(R.id.txtOrden);

        btnAlta = (Button)v.findViewById(R.id.btnAlta);

        btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaOrden();
            }
        });

        String[] vTipo = {"Consigna","Seña"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, vTipo);
        ddlTipo.setAdapter(adapter);

        asignarOnSelected();

        NivelDao nivelDao = new NivelDao(getContext(),4,this);
        nivelDao.execute();

        SenasDao senasDao = new SenasDao(getContext(),4,this);
        senasDao.execute();

        return v;
    }

    public void llenarDDL(String resultado, int Accion){
        List<String> lst = new ArrayList<String>();

        String [] filas, datos;

        if(!resultado.isEmpty()){
            filas = resultado.split("\\|");
            for(int i = 0;i<filas.length;i++){
                datos = filas[i].split(";");
                lst.add(datos[0] + " - " + datos[1]);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, lst);

        if(Accion == 1) {
            ddlNivel.setAdapter(adapter);
        }else{
            ddlSena.setAdapter(adapter);
        }
    }

    private void asignarOnSelected(){
        ddlTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Consigna")){
                    ddlSena.setVisibility(View.GONE);
                    txtId.setVisibility(View.VISIBLE);
                }else{
                    txtId.setVisibility(View.GONE);
                    ddlSena.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void altaOrden(){
        Orden ord = obtenerDatos();

        if(ord!=null){
            OrdenDao ordDao = new OrdenDao(getContext(),ord,1);
            ordDao.execute();
        }
    }

    private Orden obtenerDatos(){
        Nivel niv = new Nivel();
        Sena sena = new Sena();
        Consigna con = new Consigna();
        Orden ord = new Orden();
        if(!(ddlNivel.getSelectedItem().toString().isEmpty()||ddlTipo.getSelectedItem().toString().isEmpty()
            ||(ddlSena.getSelectedItem().toString().isEmpty()&&txtId.getText().toString().isEmpty())||
                txtOrden.getText().toString().isEmpty())){
            try {
                niv.setIdNivel(((int) ddlNivel.getSelectedItemId())+1);

                if (ddlTipo.getSelectedItem().toString().equals("Consigna")) {
                    con.setIdConsigna(Integer.parseInt(txtId.getText().toString()));
                } else {
                    sena.setIdSena(((int) ddlSena.getSelectedItemId())+1);
                }

                ord.setNivel(niv);
                ord.setSena(sena);
                ord.setConsigna(con);
                ord.setOrden(Integer.parseInt(txtOrden.getText().toString()));

            }catch(Exception e){
                ord = null;
                Toast.makeText(getActivity(),"Complete los datos correctamente.", Toast.LENGTH_LONG).show();
            }
        }else{
            ord = null;
            Toast.makeText(getActivity(),"Complete los datos.", Toast.LENGTH_LONG).show();
        }
        return ord;
    }
}