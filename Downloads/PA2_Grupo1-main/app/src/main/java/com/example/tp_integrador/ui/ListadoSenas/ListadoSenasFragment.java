package com.example.tp_integrador.ui.ListadoSenas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.tp_integrador.R;

public class ListadoSenasFragment extends Fragment {

    private ListadoSenasModel listadoSenasModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        listadoSenasModel =
                ViewModelProviders.of(this).get(ListadoSenasModel.class);
        View root = inflater.inflate(R.layout.fragment_listadosenas, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        listadoSenasModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}