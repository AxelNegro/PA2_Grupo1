package com.example.tp_integrador.ui.cliente.CA;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class vmCA extends ViewModel {

    private MutableLiveData<String> mText;

    public vmCA() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}