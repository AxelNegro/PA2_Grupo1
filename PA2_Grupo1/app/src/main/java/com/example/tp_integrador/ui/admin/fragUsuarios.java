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

public class fragUsuarios extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private fragUsuarioList fragUsrList;
    private fragUsuarioMod fragUsrMod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bml_usuario, container, false);

        viewPager = v.findViewById(R.id.vpUsuarios);
        tabLayout = v.findViewById(R.id.tbUsuarios);

        fragUsrMod = new fragUsuarioMod();
        fragUsrList = new fragUsuarioList();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),0);
        vpAdapter.addFragment(fragUsrMod,"Modificacion");
        vpAdapter.addFragment(fragUsrList,"Listado");
        viewPager.setAdapter(vpAdapter);

        return v;
    }
}