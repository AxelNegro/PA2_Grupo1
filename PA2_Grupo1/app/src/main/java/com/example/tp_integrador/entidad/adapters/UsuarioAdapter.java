package com.example.tp_integrador.entidad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.Main_bml_usuario;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    Context context;
    List<Usuario> items;
     Main_bml_usuario main;

    //Trae el contexto, la lista de articulos, e instancia un objeto de mainActivity
    public UsuarioAdapter(Context _context, List<Usuario> _items, Main_bml_usuario main) {
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.main=main;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Usuario getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Trae el template para usar en la lista(seria el formato con el que se verian los items)
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_template_usuarios, parent, false);
        }

        //Trae el articulo
        final Usuario User = items.get(position);

        //Carga los textView con los datos
        TextView lblId =  (TextView)convertView.findViewById(R.id.lblId);
        TextView lblNombre = (TextView)convertView.findViewById(R.id.lblNombre);
        TextView lblStock = (TextView)convertView.findViewById(R.id.lblStock);
        TextView lblCategoria = (TextView)convertView.findViewById(R.id.lblCategoria);

       // lblId.setText(String.valueOf(User.getId()));
       // lblNombre.setText(User.getNombre());
      //  lblStock.setText(String.valueOf(User.getStock()));
       // lblCategoria.setText(String.valueOf(User.getCat().getDescripcion()));

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }


}
