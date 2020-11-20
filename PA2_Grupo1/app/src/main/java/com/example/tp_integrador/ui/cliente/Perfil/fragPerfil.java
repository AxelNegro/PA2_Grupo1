package com.example.tp_integrador.ui.cliente.Perfil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Patterns;
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
import com.example.tp_integrador.ui.actLogin;
import com.example.tp_integrador.ui.cliente.navCliente;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;


public class fragPerfil extends Fragment {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button btnContrasenia, btnDatos, btnVolver,btnModificarKey,btnModificarDatos;
    private EditText txtKeyAct, txtKeyNueva,txtKeyNueva2,etEmail,etApellido,etNombre;
    private TextView lblUsuario, lblEmail, lblEmailH;
    private String username,key, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view_cli);
        View headerLayout = navigationView.getHeaderView(0);
        lblEmailH= headerLayout.findViewById(R.id.LblEmailUsuario);

        final View view = inflater.inflate(R.layout.fragment_perfil,container,false);

        //Recupero los TextView
        lblUsuario= view.findViewById(R.id.txtNombre);
        lblEmail= view.findViewById(R.id.txtEmail);

        //datos de session
        SharedPreferences prefs = getActivity().getSharedPreferences("login_data", Context.MODE_PRIVATE);
        username = prefs.getString("username", "");
        key = prefs.getString("key", "");
        email = prefs.getString("email","");
        //Cargo los TextView
        try {
            lblUsuario.setText(username);
            lblEmail.setText(email);
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
                try {
                    crearPopUpDatos();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }


    public void crearPopUpContrasenia(){
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View PopUp = getLayoutInflater().inflate(R.layout.popup_key,null);

        //Recupero los TextView
        txtKeyAct= PopUp.findViewById(R.id.txtContraseniaActual);
        txtKeyNueva= PopUp.findViewById(R.id.txtContraseniaNueva);
        txtKeyNueva2= PopUp.findViewById(R.id.txtContraseniaNueva2);

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

    public void crearPopUpDatos() throws ExecutionException, InterruptedException {

        dialogBuilder = new AlertDialog.Builder(getContext());
        final View PopUp = getLayoutInflater().inflate(R.layout.popup_datos,null);
        //Recupero los EditText
        etEmail= PopUp.findViewById(R.id.txtEmail);
        etNombre= PopUp.findViewById(R.id.txtNombre);
        etApellido= PopUp.findViewById(R.id.txtApellido);

        btnVolver = (Button) PopUp.findViewById(R.id.btnVolver);
        btnModificarDatos = (Button) PopUp.findViewById(R.id.btnConfirmar);

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        cargarDatos(PopUp);

        btnModificarDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    ModificarDatos(view);
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

    private void cargarDatos(View v) throws ExecutionException, InterruptedException {
        Usuario User=new Usuario();
        User.setNameUser(username);
        UsuarioDao x = new UsuarioDao(v.getContext(),User,3,(navCliente)getActivity());
        String[] z = x.execute().get().split(";");
        etNombre.setText(z[0]);
        etApellido.setText(z[1]);
        etEmail.setText(z[2]);
    }

    private void ModificarDatos(View v)throws ExecutionException, InterruptedException {
      if(!(etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() ||
              etEmail.getText().toString().isEmpty())){
        if(validarEmail(etEmail.getText().toString())) {
            Usuario user=new Usuario();
            user.setNameUser(username);
            user.setNombre(etNombre.getText().toString());
            user.setApellido(etApellido.getText().toString());
            user.setEmail(etEmail.getText().toString());
            UsuarioDao UserDao = new UsuarioDao(v.getContext(),user,6, (navCliente) getActivity());
            if(UserDao.execute().get().equals("Datos modificados exitosamente.")){
                Toast.makeText(v.getContext(),"Datos modificados exitosamente.",Toast.LENGTH_LONG).show();
                lblEmail.setText(user.getEmail());
                lblEmailH.setText(user.getEmail());

                SharedPreferences prefs = getActivity().getSharedPreferences("login_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("email", user.getEmail());

                dialog.dismiss();
            }
            else Toast.makeText(v.getContext(),"Modifique los datos.",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"Email invalido",Toast.LENGTH_LONG).show();
            etEmail.setError("Verifique el formato de email ingresado");
        }
      }else Toast.makeText(getContext(),"Debe completar todos los campos.",Toast.LENGTH_LONG).show();
    }

    public void ShowPasswordMod(View v) {
        CheckBox c=(CheckBox)v.findViewById(R.id.chkVerClaves);
        if(!c.isChecked()){
            txtKeyAct.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            txtKeyNueva.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            txtKeyNueva2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }else{
            txtKeyAct.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            txtKeyNueva.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            txtKeyNueva2.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
    }

    private boolean validarKey() {
        if(txtKeyAct.getText().toString().equals(key))
            if(txtKeyNueva.getText().toString().equals(txtKeyNueva2.getText().toString())) return true;
        return false;
    }

    public void ModificarKey(View v) throws ExecutionException, InterruptedException {
        if(!(txtKeyAct.getText().toString().isEmpty() || txtKeyNueva.getText().toString().isEmpty() || txtKeyNueva2.getText().toString().isEmpty())) {
            if (validarKey()) {
                if(!validarCaracteresProhibidos()){
                Usuario user=new Usuario();
                user.setNameUser(username);
                user.setKeyUser(txtKeyNueva2.getText().toString());
                UsuarioDao UserDao = new UsuarioDao(v.getContext(),user,5, (navCliente) getActivity());
                if(UserDao.execute().get().equals("Contrasena modificada exitosamente.")){
                    Toast.makeText(v.getContext(),"Contraseña modificada exitosamente.",Toast.LENGTH_LONG).show();
                    RedirecLogin(v);
                }
                else Toast.makeText(v.getContext(),"Modifique la contraseña.",Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(v.getContext(), "Clave actual ingresada erronea o las claves no coinciden.", Toast.LENGTH_LONG).show();
            }
        }else Toast.makeText(v.getContext(),"Debe completar todos los campos.",Toast.LENGTH_LONG).show();
    }

    public void RedirecLogin(View v){
        this.getActivity().finish();
        Intent Reg=new Intent(this.getContext(), actLogin.class);
        startActivity(Reg);
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean validarCaracteresProhibidos(){
        boolean x=false;

        if(txtKeyNueva.getText().toString().indexOf(";")!=-1 || txtKeyNueva.getText().toString().indexOf("|")!=-1){
            txtKeyNueva.setError("Caracteres ; y | no permitidos");
            x= true;
        }else{
            if(txtKeyNueva2.getText().toString().indexOf(";")!=-1 || txtKeyNueva2.getText().toString().indexOf("|")!=-1){
                txtKeyNueva2.setError("Caracteres ; y | no permitidos");
                x =true;
            }
        }
        return x;
    }
}