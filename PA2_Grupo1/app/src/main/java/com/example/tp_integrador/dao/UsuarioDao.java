package com.example.tp_integrador.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.actLogin;
import com.example.tp_integrador.ui.admin.fragUsuarioMyB;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.example.tp_integrador.dao.Conexion;
import com.example.tp_integrador.ui.admin.navAdmin;
import com.example.tp_integrador.ui.cliente.Perfil.fragPerfil;
import com.example.tp_integrador.ui.cliente.navCliente;

public class UsuarioDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Usuario user;
    private String urlAux, data;
    private int accion;
    private fragUsuarioMyB mod;
    private fragPerfil modkey;
    private navAdmin main;
    private navCliente mainc;
    private boolean f = false;

    //Utiliza constructores para seleccionar la accion a ejecutar dependiendo de los parametros que reciba
    public UsuarioDao(Context context, Usuario user, int accion) {
        this.context = context;
        this.user = user;
        this.accion = accion;
        this.main = main;
        preparaVariables();
    }

    public UsuarioDao(Context context, int accion) {
        this.context = context;
        this.accion = accion;
        preparaVariables();
    }

    public UsuarioDao(Context context, Usuario user, int accion, fragUsuarioMyB mod) {
        this.context = context;
        this.user = user;
        this.accion = accion;
        this.mod = mod;
        preparaVariables();
    }

    public UsuarioDao(Context context, Usuario user, int accion, navAdmin main, fragUsuarioMyB mod) {
        this.context = context;
        this.user = user;
        this.accion = accion;
        this.main = main;
        f = true;
        this.mod = mod;
        preparaVariables();
    }

    public UsuarioDao() {

    }

    public UsuarioDao(View v, Usuario user, int i) {
        this.context = v.getContext();
        this.user = user;
        this.accion = i;
        preparaVariables();
    }

    public UsuarioDao(View v, Usuario user, int i, fragPerfil fragPerfil) {
        this.context = v.getContext();
        this.user = user;
        this.accion = i;
        this.modkey = fragPerfil;
        preparaVariables();
    }

    public UsuarioDao(Context context, Usuario user, int i, navCliente activity) {
        this.context = context;
        this.user = user;
        this.accion = i;
        this.mainc = activity;
        preparaVariables();
    }

    public void UsuarioDAO(Context context, Usuario User, int accion) {
        this.context = context;
        this.user = User;
        this.accion = accion;
        preparaVariables();
    }


    public void preparaVariables() {
        switch (accion) {
            case 1: // Alta de usuario
                urlAux = "https://pagrupo1.000webhostapp.com/altaUsuario.php";
                llenarData();
                break;
            case 2: // Modificación de usuario
                urlAux = "https://pagrupo1.000webhostapp.com/modificarUsuario.php";
                llenarData();
                break;
            case 3: // Obtener un usuario
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerUsuario.php";
                llenarData();
                break;
            case 4: // Obtener todos los usuarios
                urlAux = "https://pagrupo1.000webhostapp.com/obtenerTodosUsuarios.php";
                break;
            case 5: // Modificación key de usuario
                urlAux = "https://pagrupo1.000webhostapp.com/modificarUsuarioKeyCliente.php";
                break;
        }
    }

    public void llenarData() {
        try {
            if (accion == 1 || accion == 2) { //Alta-Modificación de usuario
                data = URLEncoder.encode("Usuario", "UTF-8") + "=" + URLEncoder.encode(user.getNameUser(), "UTF-8")
                        + "&" + URLEncoder.encode("Contrasena", "UTF-8") + "=" + URLEncoder.encode(user.getKeyUser(), "UTF-8")
                        + "&" + URLEncoder.encode("Nombre", "UTF-8") + "=" + URLEncoder.encode(user.getNombre(), "UTF-8")
                        + "&" + URLEncoder.encode("Apellido", "UTF-8") + "=" + URLEncoder.encode(user.getApellido(), "UTF-8")
                        + "&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(user.getEmail(), "UTF-8")
                        + "&" + URLEncoder.encode("Tipo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(user.getTipo_Cuenta()), "UTF-8")
                        + "&" + URLEncoder.encode("Estado", "UTF-8") + "=" + URLEncoder.encode(user.isEstado() ? "1" : "0", "UTF-8");
            }
            if (accion == 3) data = URLEncoder.encode("Usuario", "UTF-8") + "=" + URLEncoder.encode(user.getNameUser(), "UTF-8");
       if(accion == 5){
           data = URLEncoder.encode("Usuario", "UTF-8") + "=" + URLEncoder.encode(user.getNameUser(), "UTF-8")
                   + "&" + URLEncoder.encode("Contrasena", "UTF-8") + "=" + URLEncoder.encode(user.getKeyUser(), "UTF-8");
       }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String obtenerInfo(Conexion conn) {
        String line, resultado = "";
        if (conn.cerrar_1()) {
            InputStream inputStream = conn.obtenerInfo();
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resultado = stringBuilder.toString();
                conn.cerrar_2();
            } else Log.d("BBDD", "Hubo un error al conectarse con la base de datos.");

        } else Log.d("BBDD", "Hubo un error al cerrar la conexión con la base de datos.");

        return resultado;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    //se ejecuta primero antes de execute()
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if (conn.conectar(urlAux)) {
            //Acciones que realizan escritura (Alta/Modificación)
            if (accion == 1 || accion == 2 || accion == 3) {
                if (conn.mandarInfo(data)) resultado = obtenerInfo(conn);
                else Log.d("BBDD", "Hubo un error al mandar la información a la base de datos.");
            }
            //Acciones que realizan lectura (Obtener uno/Todos)
            else if (accion == 4) resultado = obtenerInfo(conn);
            else Log.d("BBDD", "Hubo un error al conectarse con la base de datos.");
        }
        return resultado;
    }

    //resultado despues del doInBackground()
    protected void onPostExecute(String resultado) {
        if (accion == 1 || accion == 2) {
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            if (accion == 2 && f == true) main.Actualizar();
            //if(accion == 2 ) mainc.Actualizar();
            if (accion == 1) {
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            }
            if (accion == 2) {
                if (resultado.equals("1")) {
                    main.Actualizar();
                    Toast.makeText(context, "Modificado exitosamente", Toast.LENGTH_LONG).show();
                    mod.limpiar();
                } else
                    Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            }
        }
    }
}
