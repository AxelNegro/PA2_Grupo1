package com.example.tp_integrador.entidad.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.DrawableContainer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.OrdenxUsuarioDao;
import com.example.tp_integrador.entidad.clases.Sena;

import java.util.ArrayList;
import java.util.List;

public class SenasAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private Context context;
    private int layout;
    private AlertDialog dialog;
    private List<Sena> senas;

    public SenasAdapter(Context context, int layout, List<Sena> senas){
        this.context = context;
        this.layout = layout;
        this.senas = senas;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.gdlistado_senas, parent, false);
        }

        String currentName = senas.get(position).getNombreSena();
        String urlSena = senas.get(position).getImagen();

        TextView textView = (TextView) convertView.findViewById(R.id.textViewSenaMiniatura);
        textView.setText(currentName);

        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imageViewSenaMiniatura);
        Glide.with(convertView.getContext()).load(urlSena).into(imageView1);

        final Sena sen = senas.get(position);

        LinearLayout lnItem = (LinearLayout)convertView.findViewById(R.id.lnItemSena);
        lnItem.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                final View PopUp = mInflater.inflate(R.layout.popup_sena_info,null);

                ImageView imagen = (ImageView) PopUp.findViewById(R.id.imgSena2);
                TextView nombreSena =(TextView)   PopUp.findViewById(R.id.txtNombreSena);
                TextView descripcion = (TextView)  PopUp.findViewById(R.id.txtDescripcionSena);
                Button btnVolver = (Button)  PopUp.findViewById(R.id.btnVolver);

                nombreSena.setText(sen.getNombreSena());
                descripcion.setText(sen.getDescripcion());
                Glide.with(PopUp.getContext()).load(sen.getImagen()).into(imagen);

                btnVolver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialogBuilder.setView(PopUp);
                dialog = dialogBuilder.create();
                dialog.show();
            }
        });



        return convertView;
    }
}
