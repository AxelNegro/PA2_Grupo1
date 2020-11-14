package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.OpcionDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Opcion;

public class fragOpcionesMod extends Fragment {

    EditText txtIdOpcionMod;
    EditText txtIdConsignaMod;
    EditText txtDescOpc;
    CheckBox cbResultado;
    Button btnBuscar, btnModificar;
    Opcion opcion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_opciones_mod, container, false);

        btnBuscar = (Button) v.findViewById(R.id.btnBuscar);
        btnModificar = (Button) v.findViewById(R.id.btnAlta);

        txtIdOpcionMod = (EditText) v.findViewById(R.id.txtIdOpcionMod);
        txtIdConsignaMod = (EditText) v.findViewById(R.id.txtIdConsignaMod);
        txtDescOpc = (EditText) v.findViewById(R.id.txtDescOpc);
        cbResultado = (CheckBox) v.findViewById(R.id.cbResultado);


        validarInputs();

        opcion = new Opcion();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarOpcion();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarOpcion();
            }
        });



        return v;
    }

    private void modificarOpcion(){
        if(!(txtIdOpcionMod.getText().toString().isEmpty()||txtIdConsignaMod.getText().toString().isEmpty()||txtDescOpc.toString().isEmpty())) {
            try{
                Consigna con = new Consigna();
                int idCon = Integer.parseInt(txtIdConsignaMod.getText().toString());
                con.setIdConsigna(idCon);
                opcion.setConsigna(con);

                int idOpc = Integer.parseInt(txtIdOpcionMod.getText().toString());
                opcion.setIdOpcion(idOpc);

                opcion.setDesc(txtDescOpc.getText().toString());

                if(cbResultado.isChecked() == true)
                {
                    opcion.setRes(true);
                }else
                    {
                        opcion.setRes(false);
                    }

                OpcionDao opcDao = new OpcionDao(getContext(), opcion, 2,(navAdmin)getActivity(),this);
                opcDao.execute();
            }
            catch(Exception e){
                Toast.makeText(getActivity(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getActivity(),"Complete los datos.",Toast.LENGTH_LONG).show();
        }
    }

    private void buscarOpcion(){
        if(!txtIdOpcionMod.getText().toString().isEmpty()){
            try {
                int idOpc = Integer.parseInt(txtIdOpcionMod.getText().toString());
                opcion.setIdOpcion(idOpc);

                OpcionDao opcDao = new OpcionDao(getContext(),opcion,3, (navAdmin)getActivity(), this);
                opcDao.execute();

            }
            catch(Exception e){
                Toast.makeText(getActivity(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getActivity(),"Complete los datos.",Toast.LENGTH_LONG).show();
        }

    }

    public void obtenerDatos(String resultado){
        resultado = resultado.substring(0,resultado.length() - 1);

        String[] datos = resultado.split(";");

        txtIdConsignaMod.setText(datos[0]);
        txtDescOpc.setText(datos[1]);
        if(Integer.parseInt(datos[2]) == 1)
        {
            cbResultado.setChecked(true);
        }else {
            cbResultado.setChecked(false);
        }

    }

    public void limpiar(){
        txtIdOpcionMod.setText("");
        txtIdConsignaMod.setText("");
        txtDescOpc.setText("");
        cbResultado.setChecked(false);
    }

    private void validarInputs() {

        txtIdOpcionMod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = s.toString().replaceAll(";", "");
                result = result.replaceAll("\\|", "");
                if (!s.toString().equals(result)) {
                    txtIdOpcionMod.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    txtIdOpcionMod.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        txtIdConsignaMod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = s.toString().replaceAll(";", "");
                result = result.replaceAll("\\|", "");
                if (!s.toString().equals(result)) {
                    txtIdConsignaMod.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    txtIdConsignaMod.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        txtDescOpc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = s.toString().replaceAll(";", "");
                result = result.replaceAll("\\|", "");
                if (!s.toString().equals(result)) {
                    txtDescOpc.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    txtDescOpc.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

    }
}