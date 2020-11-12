package com.example.tp_integrador.entidad.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.tp_integrador.entidad.clases.Nivel;
import com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA.actListadoSenasCA;

import java.util.List;

public class NivelAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    Context context;
    List<Nivel> items;

    public NivelAdapter(Context _context, List<Nivel> _items) {
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Nivel getItem(int position) {
        return items.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gditem_niveles, parent, false);
            //convertView.setLayoutParams(new GridView.LayoutParams(200,200));
        }
        final Nivel lvl = items.get(position);
        Nivel lvlAux;

        try{
            lvlAux = items.get(position-1);
        }catch(Exception e){
            lvlAux = null;
        }

        final LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItem);

        if(lvl.isEstado()){
            lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_superado));
        }else{
            if(lvlAux == null){
                lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_desbloqueado));
            }
            else{
                if(lvlAux.isEstado()){
                    lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_desbloqueado));
                }else{
                    lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_bloqueado));
                }
            }
        }

        TextView txtNivel =  (TextView)convertView.findViewById(R.id.lblNivel);
        TextView txtDesc = (TextView)convertView.findViewById(R.id.lblDescOpc);
        txtNivel.setText("Nivel " + String.valueOf(lvl.getIdNivel()));
        txtDesc.setText(lvl.getNivel());

        final Nivel finalLvlAux = lvlAux;

        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(lvl.isEstado()) {
                    Intent intent = new Intent(context, actListadoSenasCA.class);
                    context.startActivity(intent);
                }else{
                    if(finalLvlAux == null){
                        Intent intent = new Intent(context, actListadoSenasCA.class);
                        context.startActivity(intent);
                    }
                    else{
                        if(finalLvlAux.isEstado()){
                            Intent intent = new Intent(context, actListadoSenasCA.class);
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context,"Nivel no desbloqueado.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
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
