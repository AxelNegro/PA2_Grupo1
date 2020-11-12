package com.example.tp_integrador.entidad.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.clases.Usuario;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    Context context;
    List<Usuario> items;
    private AlertDialog dialog;

    //Trae el contexto, la lista de usuarios, e instancia un objeto de mainActivity
    public UsuarioAdapter(Context _context, List<Usuario> _items) {
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Usuario getItem(int position) {
        return items.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String res = "", est = "";

        if (convertView == null) convertView = mInflater.inflate(R.layout.gditem_usuarios, parent, false);

        final Usuario Usr = items.get(position);

        LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItem);

        TextView lblUsuario = (TextView)convertView.findViewById(R.id.lblUsuario);
        TextView lblKey = (TextView)convertView.findViewById(R.id.lblKey);
        TextView lblNomApe = (TextView)convertView.findViewById(R.id.lblNomApe);
        TextView lblEmail = (TextView)convertView.findViewById(R.id.lblEmail);
        TextView lblTc = (TextView)convertView.findViewById(R.id.lblTipoCuenta);

        lblUsuario.setText("Usuario: "+ Usr.getNameUser() );
        lblKey.setText("Contrasenia: "+ Usr.getKeyUser());
        lblNomApe.setText("Nombre y apellido: " +Usr.getNombre() +", "+Usr.getApellido() );
        lblEmail.setText("Email: " +Usr.getEmail());
        String tc = (Usr.getTipo_Cuenta() == 0)?"Cliente":"Administrador";
        lblTc.setText("Tipo de cuenta: " +tc+".");

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
