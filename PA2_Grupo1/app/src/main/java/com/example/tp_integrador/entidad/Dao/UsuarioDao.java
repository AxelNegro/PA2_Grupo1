package com.example.tp_integrador.entidad.Dao;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.ListadoUsuarios;
import com.example.tp_integrador.ui.admin.Main_bml_usuario;
import com.example.tp_integrador.ui.admin.ModBajaUsuario;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UsuarioDao extends AsyncTask<String, Void, String> {

    private Context context;
    private Usuario User;
    private String urlAux, data;
    private int accion;
    private ModBajaUsuario mod;
    private ListadoUsuarios list;
    private Main_bml_usuario main;

    //Utiliza constructores para seleccionar la accion a ejecutar dependiendo de los parametros que reciba
    public UsuarioDao(Context context,Usuario user, int accion, Main_bml_usuario main) {
        this.context = context;
        this.User = user;
        this.accion = accion;
        this.main = main;
        preparaVariables();
    }

    public UsuarioDao(Context context, int accion, ListadoUsuarios list) {
        this.context = context;
        this.accion = accion;
        this.list = list;
        preparaVariables();
    }

    public UsuarioDao(Context context, Usuario user, int accion, ModBajaUsuario mod) {
        this.context = context;
        this.User= user;
        this.accion = accion;
        this.mod = mod;
        preparaVariables();
    }


    public void UsuarioDAO(Context context, Usuario User, int accion){
        this.context = context;
        this.User = User;
        this.accion = accion;
        this.main = main;
        preparaVariables();
    }



    public void preparaVariables(){
        switch(accion){
            case 1: // Alta de articulo
                urlAux = "http://pagrupo1.freeoda.com/altaUsuario.php";
                llenarData();
                break;
            case 2: // Modificación de articulo
                urlAux = "http://pagrupo1.freeoda.com/modificarUsuario.php";
                llenarData();
                break;
            case 3: // Obtener un articulo
                urlAux = "http://pagrupo1.freeoda.com/obtenerUsuario.php";
                llenarData();
                break;
            case 4: // Obtener todos los articulos
                urlAux = "http://pagrupo1.freeoda.com/obtenerTodosUsuarios.php";
                break;
        }
    }

    public void llenarData(){
        try {
            if(accion == 1 || accion == 2) { //Alta-Modificación de productos
               // String id = String.valueOf(articulo.getId());
               //String nombre = articulo.getNombre();
               // String stock = String.valueOf(articulo.getStock());
               // String idCategoria = String.valueOf(articulo.getCat().getId());

                /*data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")
                        + "&" + URLEncoder.encode("nombre", "UTF-8") + "=" + URLEncoder.encode(nombre, "UTF-8")
                        + "&" + URLEncoder.encode("stock", "UTF-8") + "=" + URLEncoder.encode(stock, "UTF-8")
                        + "&" + URLEncoder.encode("idCategoria", "UTF-8") + "=" + URLEncoder.encode(idCategoria, "UTF-8");
            */}
            else if (accion == 3){
           //     String id = String.valueOf(articulo.getId());
               // data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String obtenerInfo(Conexion conn){
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
            } else {
                Log.d("BBDD", "Hubo un error al conectarse con la base de datos.");
            }
        } else {
            Log.d("BBDD", "Hubo un error al cerrar la conexión con la base de datos.");
        }

        return resultado;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    //se ejecuta primero despues de execute()
    protected String doInBackground(String... strings) {
        String resultado = "";
        Conexion conn = new Conexion();

        if(conn.conectar(urlAux)){
            if(accion == 1 || accion == 2 || accion == 3) { //Acciones que realizan escritura (Alta/Modificación)
                if (conn.mandarInfo(data)) {
                    resultado = obtenerInfo(conn);
                } else {
                    Log.d("BBDD", "Hubo un error al mandar la información a la base de datos.");
                }
            }
            else if(accion == 4){ //Acciones que realizan lectura (Obtener uno/Todos)
                resultado = obtenerInfo(conn);
            }
        }
        else{
            Log.d("BBDD","Hubo un error al conectarse con la base de datos.");
        }

        return resultado;
    }
//resultado despues del doInBackground()
    protected void onPostExecute(String resultado){
        if(accion == 1 || accion == 2) {
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
            if(accion == 2) main.Actualizar();
        }
        else if(accion == 3){
            String[] datos = resultado.split(";");
            mod.setearDatos(datos);
        }
        else if(accion == 4){
            list.llenarGD(resultado);
        }
    }
}
