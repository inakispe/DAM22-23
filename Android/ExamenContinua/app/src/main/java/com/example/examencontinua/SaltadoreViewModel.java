package com.example.examencontinua;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.examencontinua.API.SaltadoresRepository.SaltadoresRepository;
import com.example.examencontinua.Datos.SaltadoresResponse;

import java.util.List;

public class SaltadoreViewModel extends ViewModel {
    private LiveData<List<SaltadoresResponse>> listaSaltadoresData;

    private SaltadoresRepository repository= SaltadoresRepository.getInstance();

    public void getSaltadores(){
        repository.getSaltadores();
    }
    public LiveData<List<SaltadoresResponse>> getListaSaltadoresData(){
        if (listaSaltadoresData == null) {
            listaSaltadoresData = repository.getSaltadoresLiveData();
        }
        return listaSaltadoresData;
    }
}
