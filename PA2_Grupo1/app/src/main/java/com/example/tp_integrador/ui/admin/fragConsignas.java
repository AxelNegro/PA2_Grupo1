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


public class fragConsignas extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private fragConsignasAlta fragConAlta;
    private fragConsignasMod fragConMod;
    private fragConsignasList fragConList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_consignas, container, false);

        viewPager = v.findViewById(R.id.vpConsignas);
        tabLayout = v.findViewById(R.id.tbConsignas);

        fragConAlta = new fragConsignasAlta();
        fragConMod = new fragConsignasMod();
        fragConList = new fragConsignasList();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),0);
        vpAdapter.addFragment(fragConAlta,"Alta");
        vpAdapter.addFragment(fragConMod,"Modificación");
        vpAdapter.addFragment(fragConList,"Listado");
        viewPager.setAdapter(vpAdapter);

        return v;
    }
}