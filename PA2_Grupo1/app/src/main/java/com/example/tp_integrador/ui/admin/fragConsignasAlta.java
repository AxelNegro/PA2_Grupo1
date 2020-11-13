package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.NivelDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class fragConsignasAlta extends Fragment {
    private EditText txtImagen, txtDescripcion;
    private Button alta;

    private Consigna consigna;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_adm_consignas_alta, container, false);
        txtDescripcion = (EditText) rootView.findViewById(R.id.txtDesc);
        txtImagen = (EditText) rootView.findViewById(R.id.txtImagen);
        alta = (Button) rootView.findViewById(R.id.btnAlta);

        alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltaConsigna();
            }
        });

        validarInputs();

        return rootView;
    }

    private void AltaConsigna(){
        consigna = new Consigna();
        obtenerDatos();
        ConsignaDao consignaDao = new ConsignaDao(getContext(),consigna,1,(navAdmin)getActivity(),this);
        consignaDao.execute();
    }

    private void obtenerDatos(){
        consigna.setDesc(txtDescripcion.getText().toString());
        consigna.setURLImagen("https://pagrupo1.000webhostapp.com/GaleriaDeImagenes/" + txtImagen.getText().toString());
    }

    public void limpiar() {
        txtDescripcion.setText("");
        txtImagen.setText("");
    }

    private void validarInputs() {

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