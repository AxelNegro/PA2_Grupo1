package com.example.tp_integrador.entidad.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.OrdenDao;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.ui.admin.fragOrdenList;

import java.util.List;

public class OrdenAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    Context context;
    List<Orden> items;
    private AlertDialog dialog;
    fragOrdenList fragOrdList;

    public OrdenAdapter(Context context, List<Orden> items, fragOrdenList fragOrdList) {
        this.context = context;
        this.items = items;
        this.fragOrdList = fragOrdList;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Orden getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String res = "", est = "";

        if (view == null) {
            view = mInflater.inflate(R.layout.gditem_ordenes, viewGroup, false);
        }
        final Orden ord = items.get(i);

        LinearLayout lnItem = (LinearLayout)view.findViewById(R.id.lnItem);

        TextView lblIdOrden = (TextView)view.findViewById(R.id.lblIdOrden);
        TextView lblNivel = (TextView)view.findViewById(R.id.lblNivel);
        TextView lblSenaConsig = (TextView)view.findViewById(R.id.lblSenaConsig);
        TextView lblOrden = (TextView)view.findViewById(R.id.lblOrden);
        TextView lblEstado = (TextView)view.findViewById(R.id.lblEstado);

        lblIdOrden.setText("Id del orden: " + String.valueOf(ord.getIdOrden()));
        lblNivel.setText("Id del nivel al que corresponde: " + String.valueOf(ord.getNivel().getIdNivel()));

        if(ord.getSena()==null){
            lblSenaConsig.setText("Id de la consigna usada: " + String.valueOf(ord.getConsigna().getIdConsigna()));
        }else{
            lblSenaConsig.setText("Id de la seña usada: " + String.valueOf(ord.getSena().getIdSena()));
        }

        lblOrden.setText("Está en el " + String.valueOf(ord.getOrden()) + "º lugar.");

        if(ord.isEstado()){
            lblEstado.setText("Activo");
        }else{
            lblEstado.setText("Inactivo");
        }

        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                final View PopUp = mInflater.inflate(R.layout.popup_baja_list,null);

                Button btnVolver = (Button) PopUp.findViewById(R.id.btnVolver);
                Button btnBaja = (Button) PopUp.findViewById(R.id.btnConfirmar);
                TextView descripcion = (TextView) PopUp.findViewById(R.id.txtDescripcion);

                if(ord.isEstado()) {
                    descripcion.setText("¿Desea dar de baja la orden de ID " + String.valueOf(ord.getIdOrden()) + "?");
                }else{
                    btnBaja.setText("Dar de alta");
                    descripcion.setText("¿Desea dar de alta la orden de ID " + String.valueOf(ord.getIdOrden()) + "?");
                }

                dialogBuilder.setView(PopUp);
                dialog = dialogBuilder.create();
                dialog.show();

                btnBaja.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OrdenDao ordenDao = new OrdenDao(3,ord,fragOrdList);
                        ordenDao.execute();
                        dialog.dismiss();
                    }
                });

                btnVolver.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        return view;
    }
}
