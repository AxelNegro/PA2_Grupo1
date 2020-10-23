package com.example.tp_integrador.ui.cliente.Mapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;

public class fragMapa extends Fragment {

    private vmMapa vmMapa;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mapa, container, false);

        return root;
    }
}