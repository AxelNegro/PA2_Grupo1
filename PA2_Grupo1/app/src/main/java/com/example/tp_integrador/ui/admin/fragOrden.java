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

public class fragOrden extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private fragOrdenAlta fragOrdAlt;
    private fragOrdenMod fragOrdMod;
    private fragOrdenList fragOrdList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adm_orden, container, false);

        viewPager = v.findViewById(R.id.vpOrden);
        tabLayout = v.findViewById(R.id.tbOrden);

        fragOrdAlt = new fragOrdenAlta();
        fragOrdMod = new fragOrdenMod();
        fragOrdList = new fragOrdenList();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getChildFragmentManager(),0);
        vpAdapter.addFragment(fragOrdAlt,"Alta");
        vpAdapter.addFragment(fragOrdMod,"Modificación");
        vpAdapter.addFragment(fragOrdList,"Baja y Listado");
        viewPager.setAdapter(vpAdapter);
        return v;
    }
}