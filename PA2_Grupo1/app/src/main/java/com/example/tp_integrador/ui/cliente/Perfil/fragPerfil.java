package com.example.tp_integrador.ui.cliente.Perfil;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp_integrador.R;


public class fragPerfil extends Fragment {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText contrasenia_Actual, contrasenia_nueva;
    private Button btnContrasenia, btnDatos, btnGuardar, btnVolver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnContrasenia = (Button) root.findViewById(R.id.btnKey);
        btnContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPopUpContrasenia();
            }
        });

        btnDatos = (Button) root.findViewById(R.id.btnDatos);
        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPopUpDatos();
            }
        });

        return root;
    }


    public void crearPopUpContrasenia(){
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View PopUp = getLayoutInflater().inflate(R.layout.popup_key,null);

        btnVolver = (Button) PopUp.findViewById(R.id.btnVolver);

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void crearPopUpDatos(){

        dialogBuilder = new AlertDialog.Builder(getContext());
        final View PopUp = getLayoutInflater().inflate(R.layout.popup_datos,null);

        btnVolver = (Button) PopUp.findViewById(R.id.btnVolver);

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}