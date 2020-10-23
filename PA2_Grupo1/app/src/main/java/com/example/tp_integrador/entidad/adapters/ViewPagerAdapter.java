package com.example.tp_integrador.entidad.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> lstFrag = new ArrayList<Fragment>();
    private List<String> lstFragTitle = new ArrayList<String>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment, String title){
        lstFrag.add(fragment);
        lstFragTitle.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lstFrag.get(position);
    }

    @Override
    public int getCount() {
        return lstFrag.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstFragTitle.get(position);
    }
}
