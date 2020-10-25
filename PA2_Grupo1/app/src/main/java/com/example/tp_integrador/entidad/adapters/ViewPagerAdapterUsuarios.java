package com.example.tp_integrador.entidad.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tp_integrador.ui.admin.ListadoUsuarios;
import com.example.tp_integrador.ui.admin.ModBajaUsuario;

public class ViewPagerAdapterUsuarios extends FragmentStatePagerAdapter {
    private static int TAB_COUNT = 3;

    public ViewPagerAdapterUsuarios(FragmentManager fm) {
        super(fm);
    }

    //Instancia el fragment seleccionado
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ModBajaUsuario.newInstance();
            case 1:
                return ListadoUsuarios.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    //Setea el titulo del fragment segun su posicion
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ModBajaUsuario.TITLE;

            case 1:
                return ListadoUsuarios.TITLE;
        }
        return super.getPageTitle(position);
    }
}
