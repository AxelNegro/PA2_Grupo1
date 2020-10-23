package com.example.tp_integrador.entidad.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.clases.Opcion;

import java.util.List;

public class OpcionAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    Context context;
    List<Opcion> items;
    public OpcionAdapter(Context _context, List<Opcion> _items) {
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Opcion getItem(int position) {
        return items.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String res = "", est = "";

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gditem_opciones, parent, false);
        }
        final Opcion opc = items.get(position);

        LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItem);

        TextView lblIdConsigna = (TextView)convertView.findViewById(R.id.lblIdConsigna);
        TextView lblIdOpcion = (TextView)convertView.findViewById(R.id.lblIdOpcion);
        TextView lblDesc = (TextView)convertView.findViewById(R.id.lblDesc);
        TextView lblResultado = (TextView)convertView.findViewById(R.id.lblResultado);
        TextView lblEstado = (TextView)convertView.findViewById(R.id.lblEstado);

        lblIdConsigna.setText("Id de la consigna: "+ String.valueOf(opc.getConsigna().getIdConsigna()) + ".");
        lblIdOpcion.setText("Id de la opción: "+ String.valueOf(opc.getIdOpcion()) + ".");
        lblDesc.setText("Descripción: " + opc.getDesc() + ".");



        if (opc.isRes()){
            res = "Si";
        }else{
            res = "No";
        }

        if(opc.isEstado()){
            est = "Activo";
        }else{
            est = "Inactivo";
        }

        lblResultado.setText("¿Es correcta la respuesta?: " + res + ".");
        lblEstado.setText("Estado: " + est + ".");


        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Desea eliminar la opción con ID " + String.valueOf(opc.getIdOpcion()) + "?");
                dialogo1.setCancelable(false);

                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        dialogo1.cancel();
                    }
                });
                dialogo1.show();
            }
        });


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
