package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.entidad.clases.Consigna;

public class fragConsignasMod extends Fragment {
    private EditText txtIdConsigna, txtDescripcion, txtImagen;
    private Button btnBuscar, btnModificar;
    private Consigna consigna;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_adm_consignas_mod, container, false);

        txtIdConsigna = (EditText) rootView.findViewById(R.id.txtIdConsigna);
        txtDescripcion = (EditText) rootView.findViewById(R.id.txtDesc);
        txtImagen = (EditText) rootView.findViewById(R.id.txtImagen);
        btnBuscar = (Button) rootView.findViewById(R.id.btnBuscar);
        btnModificar = (Button) rootView.findViewById(R.id.btnAlta);

        validarInputs();

        consigna = new Consigna();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarConsigna();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarConsigna();
            }
        });

        return rootView;
    }

    private void modificarConsigna(){
        if(!(txtIdConsigna.getText().toString().isEmpty()||txtDescripcion.getText().toString().isEmpty()||txtImagen.toString().isEmpty())) {
            try{
                consigna.setIdConsigna(Integer.valueOf(txtIdConsigna.getText().toString()));
                consigna.setDesc(txtDescripcion.getText().toString());
                consigna.setURLImagen(txtImagen.getText().toString());

                ConsignaDao consDao = new ConsignaDao(getContext(), consigna, 2, (navAdmin)getActivity(),this);
                consDao.execute();
            }
            catch(Exception e){
                Toast.makeText(getActivity(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getActivity(),"Complete los datos.",Toast.LENGTH_LONG).show();
        }
    }

    private void buscarConsigna(){
        if(!txtIdConsigna.getText().toString().isEmpty()){
            try {
                consigna.setIdConsigna(Integer.valueOf(txtIdConsigna.getText().toString()));

                ConsignaDao consDao = new ConsignaDao(getContext(), consigna, 3, this);
                consDao.execute();
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

        txtDescripcion.setText(datos[2]);
        txtImagen.setText(datos[1]);

    }

    public void limpiar(){
        txtIdConsigna.setText("");
        txtDescripcion.setText("");
        txtImagen.setText("");
    }

    private void validarInputs() {

        txtIdConsigna.addTextChangedListener(new TextWatcher() {
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
                    txtIdConsigna.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    txtIdConsigna.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        txtDescripcion.addTextChangedListener(new TextWatcher() {
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
                    txtDescripcion.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    txtDescripcion.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        txtImagen.addTextChangedListener(new TextWatcher() {
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
                    txtImagen.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    txtImagen.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });
    }

}