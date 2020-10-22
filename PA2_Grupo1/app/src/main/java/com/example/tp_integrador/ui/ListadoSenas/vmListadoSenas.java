package com.example.tp_integrador.ui.ListadoSenas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class vmListadoSenas extends ViewModel {

    private MutableLiveData<String> mText;

    public vmListadoSenas() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}