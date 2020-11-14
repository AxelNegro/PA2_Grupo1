package com.example.tp_integrador.entidad.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tp_integrador.R;
import com.example.tp_integrador.dao.ConsignaDao;
import com.example.tp_integrador.dao.OpcionxConsignaDao;
import com.example.tp_integrador.dao.OrdenxUsuarioDao;
import com.example.tp_integrador.dao.SenasDao;
import com.example.tp_integrador.entidad.clases.Opcion;
import com.example.tp_integrador.entidad.clases.OrdenxUsuario;
import com.example.tp_integrador.ui.cliente.CA.ListadoSenasCA.actListadoSenasCA;

import java.util.ArrayList;
import java.util.List;

public class OrdenxUsuarioAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Context context;
    private ArrayList<Opcion> opciones;
    private List<OrdenxUsuario> items;
    private actListadoSenasCA main;
    private AlertDialog dialog;
    private OrdenxUsuarioAdapter adapter;

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

        adapter= this;

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
                    if(ordxus.getOrden().getConsigna() != null){
                        OpcionxConsignaDao opcionxConsignaDao = new OpcionxConsignaDao(context,2,ordxus,adapter);
                        opcionxConsignaDao.execute();
                    }else{
                        SenasDao senasDao = new SenasDao(context,3,ordxus,adapter);
                        senasDao.execute();
                        OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context,ordxus,2,main);
                        ordxusDao.execute();
                    }
                }else{
                    if(finalOrdxusAux == null){
                        if(ordxus.getOrden().getConsigna() != null){
                            OpcionxConsignaDao opcionxConsignaDao = new OpcionxConsignaDao(context,2,ordxus,adapter);
                            opcionxConsignaDao.execute();
                        }else{
                            SenasDao senasDao = new SenasDao(context,3,ordxus,adapter);
                            senasDao.execute();
                            OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context,ordxus,2,main);
                            ordxusDao.execute();
                        }
                    }
                    else{
                        if(finalOrdxusAux.isEstado()){
                            if(ordxus.getOrden().getConsigna() != null){
                                OpcionxConsignaDao opcionxConsignaDao = new OpcionxConsignaDao(context,2,ordxus,adapter);
                                opcionxConsignaDao.execute();
                            }else{
                                SenasDao senasDao = new SenasDao(context,3,ordxus,adapter);
                                senasDao.execute();
                                OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context,ordxus,2,main);
                                ordxusDao.execute();
                            }
                        }else{
                            Toast.makeText(context,"Ejercicio no desbloqueado.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        return view;
    }

    public void mostrarPopupSena(String resultado) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        final View PopUp = mInflater.inflate(R.layout.popup_sena_info,null);

        ImageView imagen = (ImageView) PopUp.findViewById(R.id.imgSena2);
        TextView nombreSena =(TextView)   PopUp.findViewById(R.id.txtNombreSena);
        TextView descripcion = (TextView)  PopUp.findViewById(R.id.txtDescripcionSena);
        Button btnVolver = (Button)  PopUp.findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        String[] datos = resultado.split(";");

        nombreSena.setText(datos[0]);
        descripcion.setText(datos[2]);
        Glide.with(PopUp.getContext()).load(datos[1]).into(imagen);

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();


    }

    public void armarListaSena(String resultado, OrdenxUsuario ordxus){
        if(resultado.isEmpty()){
            Toast.makeText(context, "Error al cargar las opcionas", Toast.LENGTH_LONG).show();
            return;
        }
        String[] registros = resultado.split("\\|");
        String[] datos;
        opciones = new ArrayList<>();
        Opcion opc;
        for (int i=0;i<registros.length;i++){
            datos = registros[i].split(";");
            opc = new Opcion();
            opc.setDesc(datos[0]);
            opc.setRes(datos[1].equals("1"));
            opciones.add(opc);
        }

        boolean respuesta = false;

        for (Opcion o:opciones) {
            if(o.isRes()){
                ConsignaDao consignaDao = new ConsignaDao(6, ordxus,this);
                consignaDao.execute();
                respuesta = true;
            }
        }

        if(!respuesta) {
            Toast.makeText(context, "La consigna seleccionada no posee respuesta correcta.", Toast.LENGTH_LONG).show();
            OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context, ordxus,2,main);
            ordxusDao.execute();
        }
    }

    public void mostrarPopupEjercicio(String resultado, final OrdenxUsuario ordxus) {
        if(resultado.isEmpty()){
            return;
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        final View PopUp = mInflater.inflate(R.layout.popup_consigna,null);

        final RadioGroup group =  PopUp.findViewById(R.id.rgOpciones);
        ImageView imagen = PopUp.findViewById(R.id.imgSena);
        Button btnConfirmar = PopUp.findViewById(R.id.btnConfirmar);
        Button btnVolver = PopUp.findViewById(R.id.btnVolver);
        RadioButton n1 = PopUp.findViewById(R.id.rd1);
        RadioButton n2 = PopUp.findViewById(R.id.rd2);
        RadioButton n3 = PopUp.findViewById(R.id.rd3);
        RadioButton n4 = PopUp.findViewById(R.id.rd4);


        String[] datos = resultado.split(";");

        Glide.with(PopUp.getContext()).load(datos[1]).into(imagen);

        if(opciones != null) {
            switch (opciones.size()){
                case 1:
                    if (opciones.get(0) != null) {
                        n1.setText(opciones.get(0).getDesc());
                        n2.setVisibility(View.GONE);
                        n3.setVisibility(View.GONE);
                        n4.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    if (opciones.get(0) != null&&opciones.get(1) != null) {
                        n1.setText(opciones.get(0).getDesc());
                        n2.setText(opciones.get(1).getDesc());
                        n3.setVisibility(View.GONE);
                        n4.setVisibility(View.GONE);
                    }
                    break;
                case 3:
                    if (opciones.get(0) != null&&opciones.get(1) != null&&opciones.get(2) != null) {
                        n1.setText(opciones.get(0).getDesc());
                        n2.setText(opciones.get(1).getDesc());
                        n3.setText(opciones.get(2).getDesc());
                        n4.setVisibility(View.GONE);
                    }
                    break;
                case 4:
                    if (opciones.get(0) != null&&opciones.get(1) != null&&opciones.get(2) != null&&opciones.get(3) != null) {
                        n1.setText(opciones.get(0).getDesc());
                        n2.setText(opciones.get(1).getDesc());
                        n3.setText(opciones.get(2).getDesc());
                        n4.setText(opciones.get(3).getDesc());
                    }
                    break;
            }
        }
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = group.getCheckedRadioButtonId();
                if(id==-1){
                    Toast.makeText(context, "Seleccione una opción", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton seleccionado = (RadioButton)PopUp.findViewById(id);
                for (Opcion o:opciones) {
                    String descripcion = o.getDesc();
                    String respuesta = String.valueOf(seleccionado.getText());
                    if(o.isRes() && descripcion.equals(respuesta)){
                        OrdenxUsuarioDao ordxusDao = new OrdenxUsuarioDao(context, ordxus,2,main);
                        ordxusDao.execute();
                        dialog.dismiss();
                        return;
                    }
                }
                Toast.makeText(context, "Respuesta incorrecta", Toast.LENGTH_LONG).show();
            }
        });

        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}
