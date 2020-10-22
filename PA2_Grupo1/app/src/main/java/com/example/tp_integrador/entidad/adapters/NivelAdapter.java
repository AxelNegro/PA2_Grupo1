package com.example.tp_integrador.entidad.adapters;

import android.content.Context;
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

        LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItem);

        switch (position){
            case 0:
                lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_lvl_superado));
                break;
            case 1:
                lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_lvl_desbloqueado));
                break;
            default:
                lnItem.setBackground(context.getResources().getDrawable(R.drawable.cb_lvl_bloqueado));
                break;
        }

        TextView txtNivel =  (TextView)convertView.findViewById(R.id.lblNivel);
        TextView txtDesc = (TextView)convertView.findViewById(R.id.lblDescripcion);
        txtNivel.setText("Nivel " + String.valueOf(lvl.getIdNivel()));
        txtDesc.setText(lvl.getNivel());

        lnItem.setOnClickListener(new AdapterView.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,String.valueOf(lvl.getIdNivel()),Toast.LENGTH_SHORT).show();
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
