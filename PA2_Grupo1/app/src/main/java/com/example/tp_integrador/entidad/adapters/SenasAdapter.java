package com.example.tp_integrador.entidad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class SenasAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Sena> senas;
    public SenasAdapter(Context context, int layout, List<Sena> senas){
        this.context = context;
        this.layout = layout;
        this.senas = senas;
    }

    @Override
    public int getCount() {
        return this.senas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.senas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.gdlistado_senas, null);
        String currentName = senas.get(position).getDescripcion();

        TextView textView = (TextView) v.findViewById(R.id.textViewSenaMiniatura);
        textView.setText(currentName);

        return v;
    }
}
