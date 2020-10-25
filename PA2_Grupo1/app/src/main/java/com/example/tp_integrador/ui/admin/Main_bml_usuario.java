package com.example.tp_integrador.ui.admin;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.tp_integrador.R;
import com.example.tp_integrador.entidad.adapters.ViewPagerAdapter;
import com.example.tp_integrador.entidad.adapters.ViewPagerAdapterUsuarios;
import com.google.android.material.tabs.TabLayout;

public class Main_bml_usuario extends AppCompatActivity {
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ViewPagerAdapterUsuarios mViewPagerAdapter;
    private TabLayout mTabLayout;

    private ListView lvUsuarios;

    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_abmusuario);
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

    //Actualiza los fragments
    public interface FragmentRefreshListener{
        void onRefresh();
    }

    public void Actualizar(){
        getFragmentRefreshListener().onRefresh();
    }
}
