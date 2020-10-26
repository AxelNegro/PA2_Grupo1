package com.example.tp_integrador.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.ViewPagerAdapterUsuarios;
import com.google.android.material.tabs.TabLayout;

public class fragUsuarioMod extends AppCompatActivity{

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ViewPagerAdapterUsuarios mViewPagerAdapter;
    private TabLayout mTabLayout;

    private ListView lvUsuarios;

    public fragUsuarioMod.FragmentRefreshListener getFragmentRefreshListener() {

        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(fragUsuarioMod.FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private fragUsuarioMod.FragmentRefreshListener fragmentRefreshListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_adm_usuarios);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setViewPager();

    }

    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapterUsuarios(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //Actualiza los fragments
    public interface FragmentRefreshListener{
        void onRefresh();
    }

    public void Actualizar(){

        getFragmentRefreshListener().onRefresh();
    }
}