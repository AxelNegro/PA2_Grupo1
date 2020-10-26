package com.example.tp_integrador.entidad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_integrador.R;

import java.util.ArrayList;

public class SenasCaAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<String> senas;
    public SenasCaAdapter(Context context, int layout, ArrayList<String> senas){
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

        v = layoutInflater.inflate(R.layout.gdlistado_senas_ca, null);
        String currentName = senas.get(position);

        TextView textView = (TextView) v.findViewById(R.id.textViewSenaCAMiniatura);
        textView.setText(currentName);

        return v;
    }
}
