package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class fragOpciones extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private fragOpcionesAlta fragOpcAlta;
    private fragOpcionesList fragOpcList;
    private fragOpcionesMod fragOpcMod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_adm_opciones, container, false);

        viewPager = v.findViewById(R.id.vpOpciones);
        tabLayout = v.findViewById(R.id.tbOpciones);

        fragOpcAlta = new fragOpcionesAlta();
        fragOpcMod = new fragOpcionesMod();
        fragOpcList = new fragOpcionesList();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getChildFragmentManager(),0);
        vpAdapter.addFragment(fragOpcAlta,"Alta");
        vpAdapter.addFragment(fragOpcMod,"Modificación");
        vpAdapter.addFragment(fragOpcList,"Baja y Listado");
        viewPager.setAdapter(vpAdapter);

        return v;
    }
}