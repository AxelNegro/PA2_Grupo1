package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class fragUsuarioMyB extends Fragment {

    private EditText etsearchusername, etNombre, etApellido,etEmail,etKey,etConfirmKey;
    private Usuario User;
    private SwitchCompat switchEstado;
    private Spinner spTipoCuenta;


    public static fragUsuarioMyB newInstance() {
        return new fragUsuarioMyB();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_adm_usuarios_mb,container,false);

        /**------EditText-----**/
        etsearchusername = (EditText)view.findViewById(R.id.txtSearchUserName);
        etNombre = (EditText)view.findViewById(R.id.txtNombre);
        etApellido= (EditText)view.findViewById(R.id.txtApellido);
        etEmail=(EditText)view.findViewById(R.id.txtEmail);
        etKey = (EditText)view.findViewById(R.id.txtKey);
        etConfirmKey = (EditText)view.findViewById(R.id.txtConfirmKey);
        switchEstado = (SwitchCompat) view.findViewById(R.id.switchEstado);

        /**------Spinner tipo cuenta-----**/
        String[] listaTC = {"Cliente", "Administrador"};
        spTipoCuenta = (Spinner) view.findViewById(R.id.ddlTipoCuenta);
        spTipoCuenta.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, listaTC));


        /**------Boton Buscar-----**/
        Button botonBuscar = (Button) view.findViewById(R.id.btnBuscarUsuario);
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    CargarDatos(etsearchusername,view);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        validarInputs();

        /**------Boton Modificar-----**/
        Button botonModificar = (Button) view.findViewById(R.id.btnModificarUsuario);
        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ModificarUsuario();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /**chk show passwords**/
        CheckBox chkViewKeys2 = (CheckBox) view.findViewById(R.id.chkbViewKeys2);
        chkViewKeys2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ShowPasswordMod(v);
            }
        });

        return view;
    }

    private void CargarDatos(EditText txtsearchNameUser, View view) throws ExecutionException, InterruptedException {
        try{
            if(!txtsearchNameUser.toString().isEmpty()){
                User=new Usuario();
                User.setNameUser(txtsearchNameUser.getText().toString());
                UsuarioDao x = new UsuarioDao(getContext(),User,3,this);
                setearDatos(x.execute().get().split(";"));
            }else{
                Toast.makeText(getContext(),"Verifique el usuario",Toast.LENGTH_LONG).show();
                etsearchusername.setError("Ingrese un usuario un usuario");
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(),"Error al cargar datos",Toast.LENGTH_LONG).show();
        }
    }

    private void ModificarUsuario() throws ExecutionException, InterruptedException {
        UsuarioDao UserDao;
        Usuario user = obtenerDatos();

        if(user != null){
            UserDao = new UsuarioDao(getContext(),user,2, (navAdmin) getActivity(),this);

            //UserDao.execute();
            if(UserDao.execute().get().equals("Usuario modificado exitosamente.")) limpiar();

            UserDao.execute();

        }
        else Toast.makeText(getContext(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
    }

    public Usuario obtenerDatos() {
        //Trae los datos de los txt que carga el usuario por pantalla
        Usuario user = null;

        if (!(etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() ||
                etEmail.getText().toString().isEmpty() || etsearchusername.getText().toString().isEmpty() ||
                etKey.getText().toString().isEmpty() || etConfirmKey.getText().toString().isEmpty())){
            user=new Usuario();
            if(validarEmail(etEmail.getText().toString())) {
                if(validarKey(etKey.getText().toString(),etConfirmKey.getText().toString())) {
                    user.setNameUser(etsearchusername.getText().toString());
                    user.setNombre(etNombre.getText().toString());
                    user.setApellido(etApellido.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setKeyUser(etKey.getText().toString());
                    user.setTipo_Cuenta(spTipoCuenta.getSelectedItemPosition());
                    if (switchEstado.isChecked())user.setEstado(true);
                    else user.setEstado(false);
                }else{
                    Toast.makeText(getContext(),"Las claves no coinciden.",Toast.LENGTH_LONG).show();
                    etKey.setError("Las claves no coinciden.");
                    etConfirmKey.setError("Las claves no coinciden.");
                }
            }else {
                Toast.makeText(getContext(),"Email invalido",Toast.LENGTH_LONG).show();
                etEmail.setError("verifique el email ingresado");
            }
        }
        else Toast.makeText(getContext(),"Debe completar todos los campos.",Toast.LENGTH_LONG).show();

        return user;
    }

    private boolean validarKey(String key, String ck) {
        if(key.equals(ck)) return true;
        return false;
    }

    public void limpiar(){
        etNombre.setText("");
        etApellido.setText("");
        etEmail.setText("");
        etsearchusername.setText("");
        etKey.setText("");
        etConfirmKey.setText("");
        switchEstado.setChecked(false);
        spTipoCuenta.setSelection(0);
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void ShowPasswordMod(View v) {
        CheckBox c=(CheckBox)v.findViewById(R.id.chkbViewKeys2);
        if(!c.isChecked()){
            etKey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etConfirmKey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }else{
            etKey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            etConfirmKey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
    }

    public void setearDatos(String[] datos) {
        if(datos.length > 1) {
            etNombre.setText(datos[0]);
            etApellido.setText(datos[1]);
            etEmail.setText(datos[2]);
            etKey.setText(datos[3]);
            etConfirmKey.setText(datos[3]);
            if(Integer.valueOf(datos[4]) == 0)spTipoCuenta.setSelection(0);
            else spTipoCuenta.setSelection(1);
            if(Integer.valueOf(datos[5]) == 1)switchEstado.setChecked(true);
            else switchEstado.setChecked(false);
        }else {
            etsearchusername.setError("El usuario ingresado no existe");
            limpiar();
        }
    }

    private void validarInputs() {

        etsearchusername.addTextChangedListener(new TextWatcher() {
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
                    etsearchusername.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    etsearchusername.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        etNombre.addTextChangedListener(new TextWatcher() {
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
                    etNombre.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    etNombre.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        etApellido.addTextChangedListener(new TextWatcher() {
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
                    etApellido.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    etApellido.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
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
                    etEmail.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    etEmail.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        etKey.addTextChangedListener(new TextWatcher() {
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
                    etKey.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    etKey.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });

        etConfirmKey.addTextChangedListener(new TextWatcher() {
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
                    etConfirmKey.setText(result); // "edit" being the EditText on which the TextWatcher was set
                    etConfirmKey.setSelection(result.length()); // to set the cursor at the end of the current text
                }
            }
        });
    }
}