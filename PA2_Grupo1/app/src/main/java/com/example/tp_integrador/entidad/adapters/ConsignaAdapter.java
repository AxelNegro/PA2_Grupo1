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
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.entidad.clases.Consigna;
import com.example.tp_integrador.entidad.clases.Usuario;
import com.example.tp_integrador.ui.admin.fragConsignasList;

import java.util.List;

public class ConsignaAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    Context context;
    List<Consigna> items;
    private AlertDialog dialog;
    fragConsignasList fragConsignasList;

    //Trae el contexto, la lista de articulos, e instancia un objeto de mainActivity
    public ConsignaAdapter(Context _context, List<Consigna> _items) {
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Trae el contexto, la lista de articulos, e instancia un objeto de mainActivity
    public ConsignaAdapter(Context _context, List<Consigna> _items,fragConsignasList fragConsignasList) {
        this.fragConsignasList = fragConsignasList;
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Consigna getItem(int position) {
        return items.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String res = "", est = "";

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gditem_consignas, parent, false);
        }
        final Consigna con = items.get(position);

        LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItem);

        TextView lblNivel = (TextView)convertView.findViewById(R.id.lblIdNivel);
        TextView lblConsigna = (TextView)convertView.findViewById(R.id.lblIdConsigna);
        TextView lblDesc = (TextView)convertView.findViewById(R.id.lblDescripcion);

        lblNivel.setText("Id Consigna: "+ con.getIdConsigna() );
        lblConsigna.setText("Consigna: "+ con.getDesc());;


        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                final View PopUp = mInflater.inflate(R.layout.popup_consigna_list,null);

                Button btnVolver = (Button) PopUp.findViewById(R.id.btnVolver);
                Button btnBaja = (Button) PopUp.findViewById(R.id.btnConfirmar);
                TextView descripcion = (TextView) PopUp.findViewById(R.id.txtDescripcion);
                descripcion.setText("¿Desea dar de baja la consigna: "+ con.getDesc() +"?");

                dialogBuilder.setView(PopUp);
                dialog = dialogBuilder.create();
                dialog.show();

                btnBaja.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ConsignaDao consignaDao = new ConsignaDao(7,con.getIdConsigna(),fragConsignasList);
                        consignaDao.execute();
                        dialog.dismiss();
                    }
                });

                btnVolver.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                /*AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
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
                dialogo1.show();*/
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
