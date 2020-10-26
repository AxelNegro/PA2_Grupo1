package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.Dao.UsuarioDao;
import com.example.tp_integrador.entidad.clases.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModBajaUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModBajaUsuario extends Fragment {

    private EditText txtsearchId, txtNombre, txtStock;
    private Spinner spCategorias;
    Usuario User;

    public static final String TITLE = "ModificacionUsuario";

    public static ModBajaUsuario newInstance() {

        return new ModBajaUsuario();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_mod_baja_usuario,container,false);

        /**------TextViews-----**/
        //txtsearchId = (EditText)view.findViewById(R.id.txtid);
       // txtNombre = (EditText)view.findViewById(R.id.TxtNombreMod);
       // txtStock = (EditText)view.findViewById(R.id.TxtStockMod);

        /**------Spinner-----**/
       // CategoriaDAO x = new CategoriaDAO(view);
      //  x.execute();
       // spCategorias = (Spinner) view.findViewById(R.id.ddlCategoriaMod);

        /**------Boton Buscar-----**/
      //  Button botonBuscar = (Button) view.findViewById(R.id.btnBuscar);
       // botonBuscar.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
        //        CargarDatos(txtsearchId,view);
          //  }
      //  });

        /**------Boton Modificar-----**/
       // Button botonModificar = (Button) view.findViewById(R.id.btnAgregar);
       // botonModificar.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         ModificarArticulo();
       //     }
       // });

        return view;
    }

    private void CargarDatos(EditText txtsearchId, View view) {
        User=new Usuario();
        //User.setId(Integer.parseInt(txtsearchId.getText().toString()));
        UsuarioDao x = new UsuarioDao(getContext(),User,3,this);
        x.execute();
    }

    private void ModificarUsuario() {
        UsuarioDao artDao;
        Usuario User = obtenerDatos();

        if(User != null){
            /*if(art.getId() != Integer.parseInt(txtsearchId.getText().toString())){
                Toast.makeText(getContext(),"Se modificó el id del artículo",Toast.LENGTH_SHORT).show();
                return;
            }
            artDao = new ArticuloDAO(getContext(),art,2, (MainActivity)getActivity());
            artDao.execute();
            limpiar();
        }
        else{
            Toast.makeText(getContext(),"Complete los datos correctamente.",Toast.LENGTH_LONG).show();
        */}
    }

    public Usuario obtenerDatos() {

        /*Categoria cat = new Categoria();

        String id = txtsearchId.getText().toString();
        String nombre = txtNombre.getText().toString();
        String stock = txtStock.getText().toString();

        Long catAux = spCategorias.getSelectedItemId();
        int categoria = catAux.intValue() + 1;

        if (!(id.isEmpty() || nombre.isEmpty() || nombre.contains(";") || stock.isEmpty() || categoria == 0)){
            if(Integer.parseInt(stock) >= 0) {
                cat.setId(categoria);
                cat.setDescripcion(spCategorias.getSelectedItem().toString());
                art.setNombre(nombre);
                art.setStock(Integer.parseInt(stock));
                art.setCat(cat);
            }else {
                Toast.makeText(getContext(),"Stock invalido",Toast.LENGTH_LONG).show();
                art=null;
            }
        }
        else{
            art = null;
        }
            */
        return new Usuario();
    }

    public void limpiar(){
        txtsearchId.setText("");
        txtNombre.setText("");
        txtStock.setText("");
        spCategorias.setSelection(0);
    }

    public void setearDatos(String[] datos) {
        if(datos.length != 0) {
            txtNombre.setText(datos[0]);
            txtStock.setText(datos[1]);
            spCategorias.setSelection(Integer.parseInt(datos[2]) - 1);
        }
        else{
            Toast.makeText(getContext(),"No existe un articulo con el ID ingresado.",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }
}