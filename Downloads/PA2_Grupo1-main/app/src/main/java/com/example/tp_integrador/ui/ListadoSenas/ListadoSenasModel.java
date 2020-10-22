package com.example.tp_integrador.ui.ListadoSenas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListadoSenasModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListadoSenasModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}