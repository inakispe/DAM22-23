package com.example.newpokedex;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newpokedex.APIS.PokeRepository;
import com.example.newpokedex.Datos.HabilidadesPokemon;
import com.example.newpokedex.Datos.ListaResponse;

public class PokemonViewModel extends AndroidViewModel {
    private PokeRepository pokemonRepository;
    private LiveData<HabilidadesPokemon> pokemonDetalleLiveData;
    private LiveData<ListaResponse> pokemonListaLiveData;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        pokemonRepository = new PokeRepository();
        pokemonDetalleLiveData = pokemonRepository.getHabilidadesLiveData();
        pokemonListaLiveData = pokemonRepository.getResultadosLiveData();
    }

    public void llamadaDetalle(String url) {
        pokemonRepository.llamadaDetalle(url);
    }

    public void llamadaLista(int limit) {
        pokemonRepository.llamadaLista(limit);
    }

    public LiveData<HabilidadesPokemon> getPokemonDetalleLiveData() {
        return pokemonDetalleLiveData;
    }

    public LiveData<ListaResponse> getPokemonListaLiveData() {
        return pokemonListaLiveData;
    }
}