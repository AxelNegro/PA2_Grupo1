package com.example.tp_integrador.entidad.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.tp_integrador.entidad.clases.NivelesxUsuario;
import com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA.actListadoSenasCA;

import java.util.List;

public class NivelesxUsuarioAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private Context context;
    private List<NivelesxUsuario> items;

    public NivelesxUsuarioAdapter(Context _context, List<NivelesxUsuario> _items) {
        this.context = _context;
        this.items = _items;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public NivelesxUsuario getItem(int position) {
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
        final NivelesxUsuario nivxus = items.get(position);
        NivelesxUsuario nivxusAux;

        try{
            nivxusAux = items.get(position-1);
        }catch(Exception e){
            nivxusAux = null;
        }

        final LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItem);

        if(nivxus.isEstado()){
            lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_superado));
        }else{
            if(nivxusAux == null){
                lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_desbloqueado));
            }
            else{
                if(nivxusAux.isEstado()){
                    lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_desbloqueado));
                }else{
                    lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_bloqueado));
                }
            }
        }

        TextView txtNivel =  (TextView)convertView.findViewById(R.id.lblNivel);
        TextView txtDesc = (TextView)convertView.findViewById(R.id.lblDescOpc);
        txtNivel.setText("Nivel " + String.valueOf(nivxus.getNivel().getIdNivel()));
        txtDesc.setText(nivxus.getNivel().getNivel());

        final NivelesxUsuario finalNivxusAux = nivxusAux;

        SharedPreferences prefs = context.getSharedPreferences("nivel",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(nivxus.isEstado()) {
                    editor.putString("idNivel", String.valueOf(nivxus.getNivel().getIdNivel()));
                    editor.commit();
                    Intent intent = new Intent(context, actListadoSenasCA.class);
                    context.startActivity(intent);
                }else{
                    if(finalNivxusAux == null){
                        editor.putString("idNivel", String.valueOf(nivxus.getNivel().getIdNivel()));
                        editor.commit();
                        Intent intent = new Intent(context, actListadoSenasCA.class);
                        context.startActivity(intent);
                    }
                    else{
                        if(finalNivxusAux.isEstado()){
                            editor.putString("idNivel", String.valueOf(nivxus.getNivel().getIdNivel()));
                            editor.commit();
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
