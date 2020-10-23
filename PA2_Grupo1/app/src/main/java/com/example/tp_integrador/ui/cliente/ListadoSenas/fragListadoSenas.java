package com.example.tp_integrador.ui.cliente.ListadoSenas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;

public class fragListadoSenas extends Fragment {

    private vmListadoSenas vmListadoSenas;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listadosenas, container, false);

        return root;
    }
}