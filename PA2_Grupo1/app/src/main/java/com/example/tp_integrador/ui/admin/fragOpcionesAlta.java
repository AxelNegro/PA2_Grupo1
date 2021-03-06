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
import com.example.tp_integrador.dao.OpcionDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Opcion;

public class fragOpcionesAlta extends Fragment {
    //private Spinner spinnerIdConsigna;
    private EditText txtIdConsigna, txtDescripcion;
    private CheckBox ckbCorrecta;
    private Button alta;

    private Opcion opcion;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_opciones_alta, container, false);

        //spinnerIdConsigna = (Spinner) v.findViewById(R.id.spinnerTraerIdConsigna);
        txtIdConsigna = (EditText) v.findViewById(R.id.txtIdConsigna);
        txtDescripcion = (EditText) v.findViewById(R.id.txtDescOpc);
        ckbCorrecta = (CheckBox) v.findViewById(R.id.cbResultado);
        alta = (Button) v.findViewById(R.id.btnAlta);

        alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AltaOpcion();
            }
        });

        //ConsignaDao consignaDao = new ConsignaDao(getContext(),7,this);
        //consignaDao.execute();

        validarInputs();

        return v;
    }

    /*
    public void llenarDDL(String resultado, int Accion){
        List<String> lst = new ArrayList<String>();

        String [] filas, datos;

        if(!resultado.isEmpty()){
            filas = resultado.split("\\|");
            for(int i = 0;i<filas.length;i++){
                datos = filas[i].split(";");

                lst.add(datos[0] + "-" + datos[2]);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, lst);

        if(Accion == 1) {
            spinnerIdConsigna.setAdapter(adapter);
        }
    }
    */

    private void AltaOpcion(){
        opcion = new Opcion();
        obtenerDatos();

        if(opcion != null ) {
            OpcionDao opcionDao = new OpcionDao(getContext(), opcion, 1, (navAdmin) getActivity(), this);
            opcionDao.execute();
        }else{
            Toast.makeText(getContext(),"Complete los datos correctamente.", Toast.LENGTH_LONG).show();
        }
    }

    private void obtenerDatos(){
        Consigna con = new Consigna();
        //String [] filas = spinnerIdConsigna.getSelectedItem().toString().split("-");
        //int id = Integer.parseInt(filas[0]);

        try {
            int id = Integer.parseInt(txtIdConsigna.getText().toString());
            con.setIdConsigna(id);
            opcion.setConsigna(con);
            opcion.setDesc(txtDescripcion.getText().toString());
            if (ckbCorrecta.isChecked() == true) {
                opcion.setRes(true);
            } else {
                opcion.setRes(false);
            }
        }catch(Exception e){
            opcion = null;
        }
    }

    public void limpiar() {
        txtIdConsigna.setText("");
        txtDescripcion.setText("");
        ckbCorrecta.setChecked(false);
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
    }

}