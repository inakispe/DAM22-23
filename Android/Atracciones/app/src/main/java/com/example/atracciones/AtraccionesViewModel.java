package com.example.atracciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.atracciones.API.AtraccionesRepository;
import com.example.atracciones.Model.AtraccionesResponse;

import java.util.List;

public class AtraccionesViewModel extends ViewModel {
    private LiveData<List<AtraccionesResponse>> listaData;
    private LiveData<AtraccionesResponse> detalleData;
    private AtraccionesRepository repository = AtraccionesRepository.getInstance();

    private String campoPrueba;

    public void setCampoPrueba(String campoPrueba) {
        repository.setPrueba(campoPrueba);
    }

    public String getCampoPrueba() {
        return repository.getPrueba();
    }

    public void getAtracciones() {
        repository.getAtracciones();
    }

    public void getDetalle(String id) {
        repository.getDetalle(id);
    }

    public LiveData<List<AtraccionesResponse>> getListaData() {
        if (listaData == null) {
            listaData = repository.getAtraccionesLiveData();
        }
        return listaData;
    }

    public LiveData<AtraccionesResponse> getDetalleData() {
        if (detalleData == null) {
            detalleData = repository.getDetalleLiveData();
        }
        return detalleData;
    }
}