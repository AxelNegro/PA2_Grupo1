package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class fragUsuarios extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private fragUsuarioList fragUsrList;
    private fragUsuarioMyB fragUsrMod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_adm_usuarios, container, false);

        viewPager = v.findViewById(R.id.vpUsuarios);
        tabLayout = v.findViewById(R.id.tbUsuarios);

        fragUsrMod = new fragUsuarioMyB();
        fragUsrList = new fragUsuarioList();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getChildFragmentManager(),0);
        vpAdapter.addFragment(fragUsrMod,"Baja y Modificación");
        vpAdapter.addFragment(fragUsrList,"Listado");
        viewPager.setAdapter(vpAdapter);

        return v;
    }
}