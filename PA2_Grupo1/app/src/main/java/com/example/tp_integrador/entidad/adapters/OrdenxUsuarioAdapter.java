package com.example.tp_integrador.entidad.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.OrdenDao;
import com.example.tp_integrador.dao.OrdenxUsuarioDao;
import com.example.tp_integrador.entidad.clases.NivelesxUsuario;
import com.example.tp_integrador.entidad.clases.Orden;
import com.example.tp_integrador.entidad.clases.OrdenxUsuario;
import com.example.tp_integrador.ui.admin.fragOrdenList;
import com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA.actListadoSenasCA;

import java.util.List;

public class OrdenxUsuarioAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Context context;
    private List<OrdenxUsuario> items;
    private actListadoSenasCA main;
    private AlertDialog dialog;

    public OrdenxUsuarioAdapter(Context context, List<OrdenxUsuario> items, actListadoSenasCA main) {
        this.context = context;
        this.items = items;
        this.main = main;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public OrdenxUsuario getItem(int position) {
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
            view = mInflater.inflate(R.layout.gditem_ordenca, viewGroup, false);
        }
        final OrdenxUsuario ordxus = items.get(i);

        LinearLayout lnItem = (LinearLayout)view.findViewById(R.id.lnItem);

        TextView lblTitulo = (TextView) view.findViewById(R.id.lblTitulo);

        if(ordxus.getOrden().getSena()==null){
            lblTitulo.setText("Ejercicio ID " + String.valueOf(ordxus.getOrden().getConsigna().getIdConsigna()));
        }else{
            lblTitulo.setText("Seña ID " + String.valueOf(ordxus.getOrden().getSena().getIdSena()) + ": " + ordxus.getOrden().getSena().getNombreSena());
        }

        OrdenxUsuario ordxusAux;

        try{
            ordxusAux = items.get(i-1);
        }catch(Exception e){
            ordxusAux = null;
        }

        if(ordxus.isEstado()){
            lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_superado));
        }else{
            if(ordxusAux == null){
                lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_desbloqueado));
            }
            else{
                if(ordxusAux.isEstado()){
                    lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_desbloqueado));
                }else{
                    lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_bloqueado));
                }
            }
        }

        final OrdenxUsuario finalOrdxusAux = ordxusAux;

        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(ordxus.isEstado()) {
                    OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context,ordxus,2,main);
                    ordxusDao.execute();
                }else{
                    if(finalOrdxusAux == null){
                        OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context,ordxus,2,main);
                        ordxusDao.execute();
                    }
                    else{
                        if(finalOrdxusAux.isEstado()){
                            OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context,ordxus,2,main);
                            ordxusDao.execute();
                        }else{
                            Toast.makeText(context,"Ejercicio no desbloqueado.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        return view;
    }
}
