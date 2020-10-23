package com.example.tp_integrador.ui.cliente.Mapa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class vmMapa extends ViewModel {

    private MutableLiveData<String> mText;

    public vmMapa() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}