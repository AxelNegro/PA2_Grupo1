package com.example.tp_integrador.ui.cliente.Perfil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.navCliente;

import java.util.concurrent.ExecutionException;


public class fragPerfil extends Fragment {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button btnContrasenia, btnDatos, btnModificar, btnVolver,btnModificarKey;
    private EditText txtkey,txtConfirmKey;
    private String username,key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_perfil,container,false);

        //Recupero los TextView
        TextView  lblUsuario= view.findViewById(R.id.txtNombre);
        TextView  lblEmail= view.findViewById(R.id.txtEmail);

        //datos de session
        SharedPreferences prefs = getActivity().getSharedPreferences("login_data", Context.MODE_PRIVATE);
        username = prefs.getString("username", "");
        key = prefs.getString("key", "");

        //Cargo los TextView
        try {
            lblUsuario.setText(username);
            Usuario User = new Usuario();
            User.setNameUser(username);
            UsuarioDao x = new UsuarioDao(view,User,3);
            String[] split = x.execute().get().split(";");
            lblEmail.setText(split[2]);
        }catch (Exception e){
            Toast.makeText(getContext(),"ERROR: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        // Inflate the layout for this fragment
        //View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnContrasenia = (Button) view.findViewById(R.id.btnKey);
        btnContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPopUpContrasenia();
            }
        });

        btnDatos = (Button) view.findViewById(R.id.btnDatos);
        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPopUpDatos();
            }
        });

        return view;
    }


    public void crearPopUpContrasenia(){
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View PopUp = getLayoutInflater().inflate(R.layout.popup_key,null);

        //Recupero los TextView
         txtkey= PopUp.findViewById(R.id.txtContraseniaActual);
         txtConfirmKey= PopUp.findViewById(R.id.txtContraseniaNueva);

        //datos de session
        SharedPreferences prefs = getActivity().getSharedPreferences("login_data", Context.MODE_PRIVATE);
        String key = prefs.getString("key", "");
        txtkey.setText(key);

        btnVolver = (Button) PopUp.findViewById(R.id.btnVolver);
        btnModificarKey = (Button) PopUp.findViewById(R.id.btnConfirmar);

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        CheckBox chkVerKeys = (CheckBox) PopUp.findViewById(R.id.chkVerClaves);
        chkVerKeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPasswordMod(v);
            }
        });

        btnModificarKey.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    ModificarKey(view);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

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

        btnModificar = (Button) PopUp.findViewById(R.id.btnVolver);

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        btnModificar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void ShowPasswordMod(View v) {
        CheckBox c=(CheckBox)v.findViewById(R.id.chkVerClaves);
        if(!c.isChecked()){
            txtkey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            txtConfirmKey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }else{
            txtkey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            txtConfirmKey.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
    }
    private boolean validarKey(String key, String ck) {
        if(key.equals(ck)) return true;
        return false;
    }
    public void ModificarKey(View v) throws ExecutionException, InterruptedException {
        if(!(txtkey.getText().toString().isEmpty() || txtConfirmKey.getText().toString().isEmpty())) {
            if (validarKey(txtkey.getText().toString(), txtConfirmKey.getText().toString())) {
                Usuario user=new Usuario();
                user.setNameUser(username);
                UsuarioDao x = new UsuarioDao(v,user,3,this);
                setearDatos(x.execute().get().split(";"),user);

                UsuarioDao UserDao = new UsuarioDao(v.getContext(),user,2, (navCliente) getActivity());
                    if(UserDao.execute().get().equals("Usuario modificado exitosamente.")) limpiar();
                    else Toast.makeText(v.getContext(),"Error al intentar modificar la contraseña. ",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(v.getContext(), "Las claves no coinciden.", Toast.LENGTH_LONG).show();
                txtkey.setError("Las claves no coinciden.");
                txtConfirmKey.setError("Las claves no coinciden.");
            }
        }else Toast.makeText(v.getContext(),"Debe completar todos los campos.",Toast.LENGTH_LONG).show();
    }

    public void setearDatos(String[] datos,Usuario user) {
        if(datos.length > 1) {
            user.setNombre(datos[0]);
            user.setApellido(datos[1]);
            user.setEmail(datos[2]);
            user.setKeyUser(txtConfirmKey.getText().toString());
            user.setTipo_Cuenta(Integer.valueOf(datos[4]));
            user.setEstado(true);
        }else {
            limpiar();
        }
    }

    private void limpiar() {
        txtkey.setText(key);
        txtConfirmKey.setText("");
    }
}