package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class fragOpcionesAlta extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_opciones_alta, container, false);

        return v;
    }

}